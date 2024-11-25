package pairmatching.io.view;

import java.util.List;
import pairmatching.dto.MatchingResultDto;

public class OutputView {
    public static void displayMatchedResult(List<MatchingResultDto> matchingResultDtos) {
        System.out.println("페어 매칭 결과입니다.");
        for (MatchingResultDto result : matchingResultDtos) {
            System.out.println(toFormat(result.getName()));
        }
    }

    private static String toFormat(List<String> names) {
        return String.join(" : ", names);
    }
}

