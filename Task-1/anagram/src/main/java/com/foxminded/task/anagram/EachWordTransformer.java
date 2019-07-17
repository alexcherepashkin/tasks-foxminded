package com.foxminded.task.anagram;

import java.util.StringJoiner;

public class EachWordTransformer implements TextTransformer {

    private final TextTransformer wordTransformer;

    public EachWordTransformer(TextTransformer wordTransformer) {
        this.wordTransformer = wordTransformer;
    }

    @Override
    public String transform(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text can't be null");
        }

        if (text.trim().isEmpty()) {
            return text;
        }

        StringJoiner joiner = new StringJoiner(" ");

        for (String word : text.split(" ")) {
            joiner.add(wordTransformer.transform(word));
        }

        return joiner.toString();

    }
}
