package manager.database;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HandleData {
    private static String databaseDirectory = System.getProperty("user.home") + File.separator+ ".users_data";

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
                System.out.println("Már létezik a felhasználónév");
                return false;
            }else{
                dao.insertUser(user);
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
                return dao.getPassword(username).equals(password);
            }else{
                System.out.println("Nem létező felhasználó");
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
                StoredData storedData = new StoredData(username, appId, appUser, appPassword);
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
                StoredData storedData = new StoredData(username, appId, appUser, appPassword);
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
                return chosenRecord.get(0);
            }
        }
}
