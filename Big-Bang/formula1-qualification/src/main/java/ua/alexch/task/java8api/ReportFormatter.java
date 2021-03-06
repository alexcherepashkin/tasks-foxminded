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
            .sorted(racerComparator)
            .forEach(racer -> {
                int index = counter.incrementAndGet();
                report.append(String.format("%1$-21s | %2$-27s | %3$tM:%3$tS.%3$tL\n",
                        index + ". " + racer.getRacerName(), racer.getTeam(), racer.getLapTime()));
                
                if ((index) == topRacers) {
                    report.append(createDividerLine("-", 63) + "\n");
                }
        });

        return report.toString();
    }

//    Вопрос по этому методу!
//    private String formatLapTime(Long lapTime) {
//        
//        return String.format("%1$tM:%1$tS.%1$tL", lapTime);
//    }

    private String createDividerLine(String symbol, int num) {
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < num; i++) {
            line.append(symbol);
        }
        return line.toString();
    }
}
