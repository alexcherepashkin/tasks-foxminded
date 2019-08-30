package ua.alexch.task.java8api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class RacersReaderTest {

    RacersReader racersReader = new RacersReader();
    List<String> startLog = Arrays.asList("DRR2018-05-24_00:00:10.000", "SVF2018-05-24_00:00:10.000");
    List<String> endLog = Arrays.asList("SVF2018-05-24_00:00:20.000", "DRR2018-05-24_00:00:30.000");
    List<String> abbreviations = Arrays.asList("DRR_Daniel_RED BULL", "SVF_Sebastian_FERRARI", "SVQ");

    @Test
    void shouldReadEachRacerDataAndAddThemToList() {
        List<Racer> expected = Arrays.asList(new Racer("Daniel", "RED BULL", 20000L), new Racer("Sebastian", "FERRARI", 10000L));
        List<Racer> actual = racersReader.readEachRacer(startLog, endLog, abbreviations);

        assertEquals(expected, actual);
    }

    @Test
    void shouldParseFilteredAbbreviationAndCreateRacer() {
        racersReader.addRacer("SVF2018-05-24_00:00:10.000", abbreviations, 10000L);
        
        Racer expected = new Racer("Sebastian", "FERRARI", 10000L);
        Racer actual = racersReader.getRacersList().get(0);

        assertEquals(expected, actual);
    }
}
