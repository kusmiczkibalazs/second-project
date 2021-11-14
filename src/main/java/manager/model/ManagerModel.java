package manager.model;

import database.HandleData;
import database.User;

public class ManagerModel {

    private String currentUsersName = "";
    HandleData handleData = new HandleData();

    public ManagerModel() {
        //első használat esetén egy admin felhasználó létrehozása. Ha már létezik akkor csupán kijelzi, hogy már létezik
        boolean adminUserCreated = handleData.saveUser(new User("admin", "admin"));
    }

    public boolean login(String username, String password){
        String encodedPassword = encodePassword(password);
        boolean successfulLogin = handleData.checkUser(username, encodedPassword);

        if(successfulLogin){
            currentUsersName = username;
            return true;
        } else {
            return false;
        }
    }

    private String encodePassword(String original){
        String encoded = original;

        return encoded;
    }

    private String decodePassword(String original){
        String decoded = original;

        return decoded;
    }
}
