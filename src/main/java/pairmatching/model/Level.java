package pairmatching.model;

import java.util.Arrays;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private final String value;

    Level(String value) {
        this.value = value;
    }

    public static Level findByValue(String comparedValue) {
        return Arrays.stream(Level.values())
                .filter(l -> l.value.equals(comparedValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레벨입니다."));
    }
}
