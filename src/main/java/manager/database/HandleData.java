package manager.database;

import manager.model.Encoder;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class HandleData {
    private static String databaseDirectory = System.getProperty("user.home") + File.separator+ ".users_data";
    static Encoder encoder;

    public HandleData(){
        try {
            encoder = new Encoder();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private static void MakeUsersDir() {
        try{
            Path databaseDirectoryPath = Path.of(databaseDirectory);
            if (Files.notExists(databaseDirectoryPath)){
                Files.createDirectory(databaseDirectoryPath);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Jdbi CreateConnectionUsers(){
        MakeUsersDir();
        String databaseURL = databaseDirectory + File.separator + "user";
        Jdbi jdbi = Jdbi.create("jdbc:h2:file:"+ databaseURL);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    public static Jdbi CreateConnectionStoredData(){
        MakeUsersDir();
        String databaseURL = databaseDirectory + File.separator + "storeData";
        Jdbi jdbi = Jdbi.create("jdbc:h2:file:"+ databaseURL);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    public static boolean saveUser(User user){
        Jdbi jdbi = CreateConnectionUsers();
        try(Handle handle = jdbi.open()) {
            UsersDao dao = handle.attach(UsersDao.class);
            dao.createTable();
            List<String> usernames= dao.getUsernames();
            if(usernames.contains(user.username)){
                return false;
            }else{
                User inputUser = new User(user.username, encodePassword(user.password));
                dao.insertUser(inputUser);
                return true;
            }
        }
    }

    public static boolean checkUser(String username, String password){
        Jdbi jdbi = CreateConnectionUsers();
        try(Handle handle = jdbi.open()){
            UsersDao dao = handle.attach(UsersDao.class);
            dao.createTable();
            List<String> usernames= dao.getUsernames();
            if(usernames.contains(username)){
                return decodePassword(dao.getPassword(username)).equals(password);
                //return password.equals(decodePassword(dao.getPassword(username)));
            }else{
                return false;
            }
        }
    }

    public static List<String> getApps(String username){
        Jdbi jdbi = CreateConnectionStoredData();
        try(Handle handle = jdbi.open()){
            StoredDataDao dao = handle.attach(StoredDataDao.class);
            dao.createTable();
            List<String> apps= dao.getCurrentUserData(username);
           return apps;
        }
    }

    public static void storeProfile(String username, String appId, String appUser, String appPassword){
        Jdbi jdbi = CreateConnectionStoredData();
        try(Handle handle = jdbi.open()){
            StoredDataDao dao = handle.attach(StoredDataDao.class);
            dao.createTable();
            Integer isExist = dao.getChosenRecord(username, appId).size();
            if(isExist == 0) {
                StoredData storedData = new StoredData(username, appId, appUser, encodePassword(appPassword));
                dao.insertUser(storedData);
            }else{
                throw new IllegalArgumentException();
            }
        }
    }

    public static void updateProfile(String username, String appId, String appUser, String appPassword) {
        Jdbi jdbi = CreateConnectionStoredData();
        try (Handle handle = jdbi.open()) {
            StoredDataDao dao = handle.attach(StoredDataDao.class);
            dao.createTable();
            Integer isExist = dao.getChosenRecord(username, appId).size();
            if(isExist == 0){
                throw new IllegalArgumentException();
            }else{
                StoredData storedData = new StoredData(username, appId, appUser, encodePassword(appPassword));
                dao.updateUsernameAndPassword(storedData);
            }
        }
    }

        public static StoredData returnProfileData(String username, String appId){
            Jdbi jdbi = CreateConnectionStoredData();
            try (Handle handle = jdbi.open()) {
                StoredDataDao dao = handle.attach(StoredDataDao.class);
                dao.createTable();
                List<StoredData> chosenRecord = dao.getChosenRecord(username, appId);
                StoredData getted = chosenRecord.get(0);
                return new StoredData(getted.username, getted.appId, getted.appUser, decodePassword(getted.appPassword));
            }
        }

    private static String encodePassword(String original){
        String encoded = null;
        try {
            encoded = encoder.encrypt(original);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return encoded;
    }

    private static String decodePassword(String encoded){
        String original = null;
        try {
            original = encoder.decode(encoded);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return original;
    }
}
