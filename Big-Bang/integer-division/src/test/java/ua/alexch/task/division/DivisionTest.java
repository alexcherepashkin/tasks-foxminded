package ua.alexch.task.division;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DivisionTest {

    private DivisionExecutor division = new DivisionExecutor();

    @Test
    void shouldThrowExceptionWhenDivideByZero() {

        assertThrows(ArithmeticException.class, () -> division.makeDivision(1, 0));
    }
    
    @Test
    void shouldPrintRightResultWhenNegativeNumbers() {
        String expected = "8 / -4 = -2";
        
        String actual = division.makeDivision(8, -4);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void shouldPrintDividingResultStringWhenDividendIsZero() {
        String expected = "_0|4\n" +
                          " 0|-\n" +
                          " -|0\n" +
                          " 0\n";
        
        String actual = division.makeDivision(0, 4);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void shouldPrintDividingResultStringWhenDividendLessThanDivisor() {
        String expected = "_1|4\n" +
                          " 0|-\n" +
                          " -|0\n" +
                          " 1\n";
        
        String actual = division.makeDivision(1, 4);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void shouldPrintDividingResultStringWhenNumbersEqual() {
        String expected = "_4|4\n" +
                          " 4|-\n" +
                          " -|1\n" +
                          " 0\n";
        
        String actual = division.makeDivision(4, 4);
        
        assertEquals(expected, actual);
    }
    
    @Test
    void shouldPrintDividingResultStringWhenDividendGreaterThanDivisor() {
        String expected = "_78945|4\n" +
                          " 4    |-----\n" +
                          " -    |19736\n" +
                          "_38\n" +
                          " 36\n" +
                          " --\n" +
                          " _29\n" +
                          "  28\n" +
                          "  --\n" +
                          "  _14\n" +
                          "   12\n" +
                          "   --\n" +
                          "   _25\n" +
                          "    24\n" +
                          "    --\n" +
                          "     1\n";
        
        String actual = division.makeDivision(78945, 4);
        
        assertEquals(expected, actual);
    }
}
