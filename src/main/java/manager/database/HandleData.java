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

    public static Jdbi CreateConnection(){
        MakeUsersDir();
        String databaseURL = databaseDirectory + File.separator + "data";
        Jdbi jdbi = Jdbi.create("jdbc:h2:file:"+ databaseURL);
        jdbi.installPlugin(new SqlObjectPlugin());
        return jdbi;
    }

    public static boolean saveUser(User user){
        Jdbi jdbi = CreateConnection();
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
        Jdbi jdbi = CreateConnection();
        try(Handle handle = jdbi.open()){
            UsersDao dao = handle.attach(UsersDao.class);
            List<String> usernames= dao.getUsernames();
            if(usernames.contains(username)){
                return dao.getPassword(username).equals(password);
            }else{
                System.out.println("Nem létező felhasználó");
                return false;
            }
        }
    }
}
