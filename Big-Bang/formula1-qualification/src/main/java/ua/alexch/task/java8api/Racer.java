package ua.alexch.task.java8api;

public class Racer {
    private final String racerName;
    private final String team;
    private final Long lapTime;

    Racer(String racerName, String team, Long lapTime) {
        this.racerName = racerName;
        this.team = team;
        this.lapTime = lapTime;
    }

    public String getRacerName() {
        return racerName;
    }

    public String getTeam() {
        return team;
    }

    public Long getLapTime() {
        return lapTime;
    }
}
