package manager.model;

import manager.database.HandleData;
import manager.database.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

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

       return successfulLogin;
    }

    private String encodePassword(String original){
        String encoded = original;

        return encoded;
    }

    private String decodePassword(String original){
        String decoded = original;

        return decoded;
    }

    public static void givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        char[] chars = {'$', '#', 'Ł'};
        //String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        String test = RandomStringUtils.random(10, 0, chars.length-1, true, true, chars);
        System.out.println(test);
    }

    public void generatePassword(int length, boolean capital, boolean numbers, boolean special){


    }

    public static String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi"
                + "jklmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }

    public static void main(String[] args) {
        //givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect();

        //System.out.println(generateRandomPassword(4));
    }
}
