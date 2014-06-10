/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.Random;

/**
 *
 * @author savita
 */
public class RandomString {
    
        public static String generateString() {
        int length = 10;
        Random rng = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

        char[] text = new char[length];
        for (int i = 0; i < length; i++) 
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        return new String(text);
    }

    
}
