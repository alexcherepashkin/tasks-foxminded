package com.foxminded.anagrams;

import java.util.StringJoiner;

public class Anagram {

    private final String ONLY_SPACES_PATTERN = "^\\s*$";
    private final String DELIMITER_CHAR = " ";

    public String reverseAll(String textInput) {
        if (textInput == null) {
            throw new IllegalArgumentException("\"Null\" is an inappropriate argument.");
        }
        if (textInput.matches(ONLY_SPACES_PATTERN)) {
            return textInput;
        }
        String[] words = textInput.split(DELIMITER_CHAR);
        StringJoiner result = new StringJoiner(DELIMITER_CHAR);
        for (String word : words) {
            char[] singleWord = word.toCharArray();
            reverseAllLetters(singleWord);
            result.add(String.valueOf(singleWord));
        }
        return result.toString();
    }

    private void reverseAllLetters(char[] singleWord) {
        int i = 0;
        int j = singleWord.length - 1;
        while (i < j) {
            if (!Character.isLetter(singleWord[i])) {
                i++;
            } else if (!Character.isLetter(singleWord[j])) {
                j--;
            } else {
                char temp = singleWord[i];
                singleWord[i] = singleWord[j];
                singleWord[j] = temp;
                i++;
                j--;
            }
        }
    }
}
