package manager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PasswordGenerator {

    static Random random = new Random();

    private static void addToList(ArrayList<Character> characters, String charSet, int quantity){
        for(int i = 0; i < quantity; i++){
            characters.add(charSet.charAt(random.nextInt(charSet.length())));
        }
    }

    protected static String generate(int length, boolean capitals, boolean numbers, boolean special){

        int numberOfCapitals;
        int numberOfNumbers;
        int numberOfSpecial;
        int numberOfLetters = length;

        ArrayList<Character> characters = new ArrayList<Character>();

        final String letterChars = "abcdefghijklmnopqrstuvwxyz";
        final String capitalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String numberChars = "0123456789";
        final String specialChars = "!#$%&()*+,-./:;<=>?@[]^_{|}~";         //"!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";

        if(capitals){
            numberOfCapitals = random.nextInt((length/4 + 1) - 1) + 1;
            numberOfLetters -= numberOfCapitals;
            addToList(characters, capitalChars, numberOfCapitals);
        }
        if(numbers){
            numberOfNumbers = random.nextInt((length/4 + 1) - 1) + 1;
            numberOfLetters -= numberOfNumbers;
            addToList(characters, numberChars, numberOfNumbers);
        }
        if(special){
            numberOfSpecial = random.nextInt((length/4 + 1) - 1) + 1;
            numberOfLetters -= numberOfSpecial;
            addToList(characters, specialChars, numberOfSpecial);
        }
        addToList(characters, letterChars, numberOfLetters);

        Collections.shuffle(characters);

        StringBuilder builder = new StringBuilder(characters.size());
        for(Character ch: characters)
        {
            builder.append(ch);
        }

        return builder.toString();
    }
}
