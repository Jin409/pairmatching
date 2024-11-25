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

        List<List<String>> result = new ArrayList<>();
        int count = 1;
        int index = 0;
        while (true) {
            if (count > shuffledNames.size() / 2) {
                break;
            }
            List<String> pair = new ArrayList<>();
            pair.add(shuffledNames.get(index));
            pair.add(shuffledNames.get(index + 1));
            result.add(pair);
            index += 2;
            count++;
        }

        if (names.size() % 2 == 1) {
            int lastIndex = result.size() - 1;
            result.get(lastIndex).add(shuffledNames.get(names.size() - 1));
        }

        return result;
    }
}
