package com.foxminded.anagrams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AnagramsTest {

    private Anagram anagram = new Anagram();

    @Test
    void shouldThrowException_WhenNullArgument() {

        assertThrows(IllegalArgumentException.class, () -> anagram.reverseAll(null));
    }

    @Test
    void shouldExpectSameString_WhenArgumentEmptyString() {
        String expected = "";

        String actual = anagram.reverseAll("");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectSameString_WhenArgumentSingleSpace() {
        String expected = " ";

        String actual = anagram.reverseAll(" ");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectSameString_WhenArgumentOnlySpaces() {
        String expected = "  ";

        String actual = anagram.reverseAll("  ");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectSameString_WhenArgumentSingleLetter() {
        String expected = "a";

        String actual = anagram.reverseAll("a");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectSpaceOnSamePlacePlusLetter_WhenArgumentSpacePlusLetter() {
        String expected = " a";

        String actual = anagram.reverseAll(" a");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectSpaceOnSamePlacePlusReversedWord_whenArgumentSpacePlusWord() {
        String expected = " zaq";

        String actual = anagram.reverseAll(" qaz");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectSameString_WhenOnlySymbolsProvided() {
        String expected = "12! 3";

        String actual = anagram.reverseAll("12! 3");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectReversedWord_WhenOnlyLettersProvided() {
        String expected = "trewq";

        String actual = anagram.reverseAll("qwert");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectReversedWordButSymbolsStayOnSamePlaces_WhenLettersAndSymbolsProvided() {
        String expected = "trew!q";

        String actual = anagram.reverseAll("qwer!t");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectReversedWordButSymbolsStayOnSamePlaces_WhenProvidedLettersWithSymbolsInFirstLastPositions() {
        String expected = "!rewq7";

        String actual = anagram.reverseAll("!qwer7");

        assertEquals(expected, actual);
    }

    @Test
    void shouldExpectSeparatelyReversedWordsButSymbolsStayOnSamePlaces_WhenProvidedMultipleWordsWithSymbols() {
        String expected = "d1cba hgf!e";

        String actual = anagram.reverseAll("a1bcd efg!h");

        assertEquals(expected, actual);
    }
}
