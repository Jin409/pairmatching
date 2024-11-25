package pairmatching.model;

import java.util.List;

public class PairMatchingHistory {
    private final List<Crew> pair;
    private final Level level;
    private final Mission mission;
    private final Course course;

    public PairMatchingHistory(List<Crew> pair, Level level, Mission mission, Course course) {
        this.pair = pair;
        this.level = level;
        this.mission = mission;
        this.course = course;
    }

    public boolean isHistoryOf(Mission mission, Course course) {
        return this.mission == mission && this.course == course;
    }

    public boolean hasSamePair(List<Crew> pair) {
        for (Crew crew : pair) {
            if (!pair.contains(crew)) {
                return false;
            }
        }
        return true;
    }

    public List<Crew> getPair() {
        return pair;
    }
}
