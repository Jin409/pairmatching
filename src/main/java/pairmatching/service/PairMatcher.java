package pairmatching.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.model.Crew;

public class PairMatcher {
    public static List<List<String>> match(List<Crew> crews) {
        List<String> names = crews.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());

        List<String> shuffledNames = Randoms.shuffle(names);
        return getMatchedResult(shuffledNames);
    }

    private static List<List<String>> getMatchedResult(List<String> shuffledNames) {
        List<List<String>> result = new ArrayList<>();
        int count = 1;
        int index = 0;

        while (count <= shuffledNames.size() / 2) {
            List<String> pair = new ArrayList<>();
            pair.add(shuffledNames.get(index));
            pair.add(shuffledNames.get(index + 1));
            result.add(pair);
            index += 2;
            count++;
        }

        if (shuffledNames.size() % 2 == 1) {
            int lastIndex = result.size() - 1;
            result.get(lastIndex).add(shuffledNames.get(shuffledNames.size() - 1));
        }
        return result;
    }
}
