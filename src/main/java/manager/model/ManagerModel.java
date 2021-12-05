package manager.model;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import manager.database.HandleData;
import manager.database.User;
import manager.model.exceptions.IncorrectUsernameOrPasswordException;
import manager.model.exceptions.PasswordTooLongException;
import manager.model.exceptions.UserAlreadyExistsException;
import manager.model.exceptions.UsernameTooLongexception;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class ManagerModel {

    HandleData handleData = new HandleData();

    public void login(String username, String password) throws IncorrectUsernameOrPasswordException {
        if(!handleData.checkUser(username, password)){
            throw new IncorrectUsernameOrPasswordException("Érvénytelen felhasználónév vagy jelszó");
        }
    }

    public void register(String username, String password) throws UserAlreadyExistsException, UsernameTooLongexception, PasswordTooLongException {
        final int maxLength = 50;
        if(username.length() > maxLength){
            throw new UsernameTooLongexception("A felhasználónév maximum " + maxLength + " karakter hosszú lehet!");
        }
        if(password.length() > maxLength){
            throw new PasswordTooLongException("A jelszó maximum " + maxLength + " karakter hosszú lehet!");
        }
        if(!handleData.saveUser(new User(username, password))){
            throw new UserAlreadyExistsException("Már létezik ez a felhasználó");
        }
    }

    public String generatePassword(int length, boolean capitals, boolean numbers, boolean special){
        return PasswordGenerator.generate(length, capitals, numbers, special);
    }

    public void copyToClipboard(String text){
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        try{
            this.wait(20);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        }
        content.putString(text);
        clipboard.setContent(content);
    }
}