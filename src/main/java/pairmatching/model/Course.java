package pairmatching.model;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String value;

    Course(String value) {
        this.value = value;
    }

    public static Course findByValue(String value) {
        return Arrays.stream(Course.values())
                .filter(c -> c.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과정입니다."));
    }
}
