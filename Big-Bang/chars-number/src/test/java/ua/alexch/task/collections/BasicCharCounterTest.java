package ua.alexch.task.collections;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BasicCharCounterTest {

    BasicCharCounter counter = new BasicCharCounter();

    @Test
    void shouldThrowExceptionWhenNullArgument() {
        
        assertThrows(IllegalArgumentException.class, () -> counter.count(null));
    }

    @Test
    void shouldCountNumberOfUniqueChars() {
        String expected = "\"K\" - 1\n" + 
                          "\"i\" - 1\n" + 
                          "\"t\" - 2\n" + 
                          "\"y\" - 1\n" + 
                          "\" \" - 1\n" + 
                          "\")\" - 2";
        
        String actual = counter.count("Kitty ))");

        assertEquals(expected, actual);
    }
}
