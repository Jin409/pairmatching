package pairmatching.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PairMatchingHistoryRepository {
    private static List<PairMatchingHistory> histories;

    public PairMatchingHistoryRepository() {
        histories = new ArrayList<>();
    }

    public void save(PairMatchingHistory history) {
        histories.add(history);
    }

    public boolean doesHistoryExistsOf(Mission mission, Course course) {
        for (PairMatchingHistory history : histories) {
            if (history.isHistoryOf(mission, course)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesHistoryExistsOf(Mission mission, Course course, List<List<Crew>> pairs) {
        List<PairMatchingHistory> collectedHistory = histories.stream()
                .filter(history -> history.isHistoryOf(mission, course))
                .collect(Collectors.toList());

        for (PairMatchingHistory history : collectedHistory) {
            for (List<Crew> pair : pairs) {
                if (history.hasSamePair(pair)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<PairMatchingHistory> find(Mission mission, Course course) {
        return histories.stream()
                .filter(history -> history.isHistoryOf(mission, course))
                .collect(Collectors.toList());
    }
}
