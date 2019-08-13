package ua.alexch.task.java8api;

import java.util.List;

public class ClientFormula1 {

    public static void main(String[] args) {
        RacersReader racersReader = new RacersReader();
        RacersLogReader logReader = new RacersLogReader();
        ReportFormatter formatter = new ReportFormatter();

        int topRacers = 15;
        List<Racer> racers = racersReader.readEachRacer(logReader.readLineByLine("start.log"),
                logReader.readLineByLine("end.log"), logReader.readLineByLine("abbreviations.txt"));

        String report = formatter.format(racers, topRacers);

        System.out.println(report);
    }
}
