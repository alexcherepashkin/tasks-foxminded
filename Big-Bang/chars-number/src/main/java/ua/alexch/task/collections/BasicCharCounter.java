package ua.alexch.task.collections;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public class BasicCharCounter implements CharCounter {

    @Override
    public String count(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Null is an inappropriate argument");
        }

        Map<Character, Integer> uniqueChars = new LinkedHashMap<>();
        StringJoiner uniqueCharsResult = new StringJoiner("\n");

        for (char symbol : text.toCharArray()) {
            if (!uniqueChars.containsKey(symbol)) {
                uniqueChars.put(symbol, 1);
            } else {
                uniqueChars.put(symbol, uniqueChars.get(symbol) + 1);
            }
        }

        for (char symbol : uniqueChars.keySet()) {
            uniqueCharsResult.add("\"" + symbol + "\"" + " - " + uniqueChars.get(symbol));
        }

        return uniqueCharsResult.toString();
    }
}
