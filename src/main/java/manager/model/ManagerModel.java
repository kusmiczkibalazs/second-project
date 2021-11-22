package manager.model;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import manager.database.HandleData;
import manager.database.User;
import manager.model.exceptions.IncorrectUsernameOrPasswordException;
import manager.model.exceptions.UserAlreadyExistsException;

public class ManagerModel {

    private String currentUsersName = "";
    HandleData handleData = new HandleData();

    public ManagerModel() {
    }

    public void login(String username, String password) throws IncorrectUsernameOrPasswordException {
        String encodedPassword = encodePassword(password);
        if(!handleData.checkUser(username, encodedPassword)){
            throw new IncorrectUsernameOrPasswordException("Érvénytelen felhasználónév vagy jelszó");
        }
    }

    public void register(String username, String password) throws UserAlreadyExistsException {
        String encodedPassword = encodePassword(password);
        if(!handleData.saveUser(new User(username, encodedPassword))){
            throw new UserAlreadyExistsException("Már létezik ez a felhasználó");
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
