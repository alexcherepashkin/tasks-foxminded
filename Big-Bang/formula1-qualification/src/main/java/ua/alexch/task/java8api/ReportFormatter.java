package ua.alexch.task.java8api;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReportFormatter {

    Comparator<Racer> racerComparator = (racer1, racer2) -> racer1.getLapTime().compareTo(racer2.getLapTime());

    public String format(List<Racer> racers, int topRacers) {

        StringBuilder report = new StringBuilder();
        AtomicInteger counter = new AtomicInteger();

        racers.stream()
//            .sorted(Comparator.comparing(Racer::getLapTime))
            .sorted(racerComparator)
            .forEach(racer -> {
                int index = counter.incrementAndGet();
                
                if ((index - 1) == topRacers) {
                    report.append(createUnderline("-", 63) + "\n");
                }
                
                report.append(String.format("%-21s | %-27s | %s%n", 
                        index + ". " + racer.getRacerName(), racer.getTeam(), formatLapTime(racer.getLapTime())));
        });

        return report.toString();
    }

    private String formatLapTime(Long lapTime) {
        
        return String.format("%1$tM:%1$tS.%1$tL", lapTime);
    }

    private String createUnderline(String symbol, int num) {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < num; i++) {
            line.append(symbol);
        }
        return line.toString();
    }
}
