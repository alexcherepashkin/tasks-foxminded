package com.foxminded.anagrams;

public class Swap {

    public static void reverse(String[] text) {
        for (int k = 0; k < text.length; k++) {
            char[] word = text[k].toCharArray();
            int i = 0;
            int j = word.length - 1;
            while (i < j) {
                if (!Character.isLetter(word[i])) {
                    i++;
                } else if (!Character.isLetter(word[j])) {
                    j--;
                } else {
                    char temp = word[i];
                    word[i] = word[j];
                    word[j] = temp;
                    i++;
                    j--;
                }
            }
            text[k] = String.valueOf(word);
        }
    }
}
