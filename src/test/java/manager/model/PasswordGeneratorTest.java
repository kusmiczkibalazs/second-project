package manager.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordGeneratorTest {

    final String letterChars = "abcdefghijklmnopqrstuvwxyz";
    final String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String numberChars = "0123456789";
    final String specialChars = "!#$%&()*+,-./:;<=>?@[]^_{|}~";

    boolean capitals;
    boolean numbers;
    boolean specials;

    boolean containsCharactersFromGivenCharacterSet(String word, String charset){
        for(int i = 0; i < word.length(); i++){
            if(charset.contains(word.substring(i, i+1))){
                return true;
            }
        }
        return false;
    }

    @Test
    void generatedPasswordContainsRequiredCharacters(){
        numbers = true;
        capitals = true;
        specials = true;
        String generatedPassword = PasswordGenerator.generate(10, capitals, numbers, specials);

        assertTrue(containsCharactersFromGivenCharacterSet(generatedPassword, capitalChars));
        assertTrue(containsCharactersFromGivenCharacterSet(generatedPassword, numberChars));
        assertTrue(containsCharactersFromGivenCharacterSet(generatedPassword, specialChars));
        assertTrue(containsCharactersFromGivenCharacterSet(generatedPassword, letterChars));

        numbers = false;
        capitals = false;
        specials = true;
        String generatedPassword2 = PasswordGenerator.generate(20, capitals, numbers, specials);

        assertFalse(containsCharactersFromGivenCharacterSet(generatedPassword2, capitalChars));
        assertFalse(containsCharactersFromGivenCharacterSet(generatedPassword2, numberChars));
        assertTrue(containsCharactersFromGivenCharacterSet(generatedPassword2, specialChars));
        assertTrue(containsCharactersFromGivenCharacterSet(generatedPassword2, letterChars));
    }

    @Test
    void generatedPasswordHasTheRequiredLength(){
        numbers = true;
        capitals = true;
        specials = true;
        String generatedPassword = PasswordGenerator.generate(10, capitals, numbers, specials);

        assertEquals(10, generatedPassword.length());
    }

}