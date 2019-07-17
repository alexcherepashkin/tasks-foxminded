package com.foxminded.task.anagram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TextReverserTest {

    TextTransformer test = new TextReverser();

    @Test
    void shouldThrowExceptionOnNull() {
        assertThrows(IllegalArgumentException.class, () -> test.transform(null));
    }

    @Test
    void shouldTransformSimpleText() {
        String actual = test.transform("abc");
        Assertions.assertEquals("cba", actual);
    }

    @Test
    void shouldSkipNonLetters() {
        String actual = test.transform("a1bc");
        Assertions.assertEquals("c1ba", actual);
    }


}