package pairmatching.dto;

import java.util.List;

public class MatchingResultDto {
    private final List<String> name;

    public MatchingResultDto(List<String> name) {
        this.name = name;
    }

    public List<String> getName() {
        return name;
    }
}
