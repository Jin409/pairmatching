package pairmatching.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PairMatchingHistoryRepository {
    private static List<PairMatchingHistory> histories;

    public PairMatchingHistoryRepository() {
        histories = new ArrayList<>();
    }

    public void save(PairMatchingHistory history) {
        histories.add(history);
    }

    public boolean doesHistoryExistsOf(Mission mission) {
        return histories.stream()
                .anyMatch(history -> history.isHistoryOf(mission));
    }
}
