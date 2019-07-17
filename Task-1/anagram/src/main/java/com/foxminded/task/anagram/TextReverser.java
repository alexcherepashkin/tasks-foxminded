package com.foxminded.task.anagram;

public class TextReverser implements TextTransformer {

    public String transform(String text) {

        if (text == null) {
            throw new IllegalArgumentException("Text can't be null");
        }

        char[] chars = text.toCharArray();
        int leftIndex = 0;
        int rightIndex = chars.length - 1;
        while (leftIndex < rightIndex) {
            if (!Character.isLetter(chars[leftIndex])) {
                leftIndex++;
            } else if (!Character.isLetter(chars[rightIndex])) {
                rightIndex--;
            } else {
                char temp = chars[leftIndex];
                chars[leftIndex] = chars[rightIndex];
                chars[rightIndex] = temp;
                leftIndex++;
                rightIndex--;
            }
        }
        return new String(chars);
    }

}
