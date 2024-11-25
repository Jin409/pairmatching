package pairmatching.model;

public class PairMatchingHistory {
    private final Pair pair;
    private final Level level;
    private final Mission mission;

    public PairMatchingHistory(Pair pair, Level level, Mission mission) {
        this.pair = pair;
        this.level = level;
        this.mission = mission;
    }

    public boolean isHistoryOf(Mission mission) {
        return this.mission == mission;
    }
}
