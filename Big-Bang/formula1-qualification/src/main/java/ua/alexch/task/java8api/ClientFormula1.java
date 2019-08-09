package ua.alexch.task.java8api;

public class ClientFormula1 {

    public static void main(String[] args) {
        RacersCreator racers = new RacersCreator();
        ReportFormatter formatter = new ReportFormatter();
        int topRacers = 15;
        String report = formatter.format(racers.readData("start.log", "end.log", "abbreviations.txt"), topRacers);

        System.out.println(report);
    }
}
