package pairmatching.io;

import java.util.Arrays;

public enum AnswerSign {
    YES("네"),
    NO("아니오");

    private final String sign;

    AnswerSign(String sign) {
        this.sign = sign;
    }

    public static AnswerSign findBySign(String comparedSign) {
        return Arrays.stream(AnswerSign.values())
                .filter(answer -> answer.sign.equals(comparedSign))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 응답입니다. 네, 아니오로 입력해주세요"));
    }

    public boolean meansTrue() {
        return this == YES;
    }

    public boolean meansFalse() {
        return this == NO;
    }
}
