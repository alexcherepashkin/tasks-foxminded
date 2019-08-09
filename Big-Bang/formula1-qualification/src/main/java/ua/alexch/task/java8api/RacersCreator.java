package ua.alexch.task.java8api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RacersCreator {
    private final List<Racer> racers;
    private final Function<String, String> toAbbreviation = line -> line.substring(0, 3);
    private final Function<String, String> toDateTime = line -> line.substring(3);
    private final Function<String, String> toName = line -> { String[] array = line.split("_"); return array[1]; };
    private final Function<String, String> toTeam = line -> { String[] array = line.split("_"); return array[2]; };

    RacersCreator() {
        this.racers = new ArrayList<>();
    }

    public List<Racer> readData(String startLog, String endLog, String abbreviationsLog) {
        try {
            List<String> allStarts = Files.lines(Paths.get(new File(startLog).getAbsolutePath())).collect(Collectors.toList());
            List<String> allEnds = Files.lines(Paths.get(new File(endLog).getAbsolutePath())).collect(Collectors.toList());
            List<String> abbreviations = Files.lines(Paths.get(new File(abbreviationsLog).getAbsolutePath())).collect(Collectors.toList());

            allStarts.forEach(racerStart -> {
                allEnds.stream()
                       .filter(raserEnd -> toAbbreviation.apply(racerStart).equals(toAbbreviation.apply(raserEnd)))
                       .map(raserEnd -> computeRacerLapTime(toDateTime.apply(racerStart), toDateTime.apply(raserEnd)))
                       .forEach(lapTime -> addRacer(racerStart, abbreviations, lapTime));
            });

        } catch (IOException e) {
            System.out.println("\"" + e.getMessage() + "\"" + " - he is absent today:P");
        }
        
        return racers;
    }

    private long computeRacerLapTime(String racerStartData, String racerEndData) {
        LocalDateTime startTime = LocalDateTime.parse(racerStartData, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS"));
        LocalDateTime endTime = LocalDateTime.parse(racerEndData, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS"));
        
        return Duration.between(startTime, endTime).toMillis();
    }

    private void addRacer(String racerStart, List<String> abbreviations, Long lapTime) {
        abbreviations.stream()
            .filter(abbreviation -> toAbbreviation.apply(racerStart).equals(toAbbreviation.apply(abbreviation)))
            .forEach(abbreviation -> racers.add(new Racer(toName.apply(abbreviation), toTeam.apply(abbreviation), lapTime)));
    }
}
