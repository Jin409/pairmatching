package pairmatching.io;

import java.util.Arrays;

public enum Option {
    MATCHING("1"), READ("2"), RESET("3"), QUIT("Q");

    private final String sign;

    Option(String sign) {
        this.sign = sign;
    }

    public static Option findBySign(String sign) {
        return Arrays.stream(Option.values())
                .filter(o -> o.sign.equals(sign))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기능입니다."));
    }

    public boolean isMatching() {
        return this == MATCHING;
    }
}
