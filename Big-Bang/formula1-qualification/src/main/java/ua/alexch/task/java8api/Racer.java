package ua.alexch.task.java8api;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "{" + racerName + " - " + team + " - " + lapTime + "}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        
        Racer racer = (Racer) obj;
        return Objects.equals(racerName, racer.racerName) && 
                Objects.equals(team, racer.team) &&
                Objects.equals(lapTime, racer.lapTime);
    }
}
