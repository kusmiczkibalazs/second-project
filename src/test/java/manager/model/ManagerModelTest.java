package manager.model;

import manager.model.exceptions.PasswordTooLongException;
import manager.model.exceptions.UsernameTooLongexception;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManagerModelTest {

    ManagerModel model = new ManagerModel();

    @Test
    void registerShouldThrowExceptionWhenUsernameTooLong(){
        assertThrows(UsernameTooLongexception.class, () -> model.register("01234567890123456789012345678901234567890123456789a", "01234"));
    }

    @Test
    void registerShouldThrowExceptionWhenPasswordTooLong(){
        assertThrows(PasswordTooLongException.class, () -> model.register("01234", "01234567890123456789012345678901234567890123456789a"));
    }

}