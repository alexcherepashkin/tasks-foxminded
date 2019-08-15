package ua.alexch.task.java8api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class ReportFormatterTest {

    ReportFormatter formatter = new ReportFormatter();
    List<Racer> testRacers = Arrays.asList(new Racer("Daniel", "RED BULL", 20000L), new Racer("Sebastian", "FERRARI", 10000L));

    @Test
    void shouldSortRacersByTime() {
        List<Racer> expected = Arrays.asList(new Racer("Sebastian", "FERRARI", 10000L), new Racer("Daniel", "RED BULL", 20000L));
        List<Racer> actual = testRacers.stream().sorted(formatter.racerComparator).collect(Collectors.toList());

        assertEquals(expected, actual);
    }

    @Test
    void shouldFormatOutputResult() {
        String expected = "1. Sebastian          | FERRARI                     | 00:10.000\n"
                        + "---------------------------------------------------------------\n"
                        + "2. Daniel             | RED BULL                    | 00:20.000\n";
        String actual = formatter.format(testRacers, 1);

        assertEquals(expected, actual);
    }
}
