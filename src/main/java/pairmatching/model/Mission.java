package pairmatching.model;

import static pairmatching.model.Level.LEVEL1;
import static pairmatching.model.Level.LEVEL2;
import static pairmatching.model.Level.LEVEL4;

import java.util.Arrays;

public enum Mission {

    CAR_RACING(LEVEL1, "자동차경주"),
    LOTTO(LEVEL1, "로또"),
    NUMBER_BASEBALL_GAME(LEVEL1, "숫자야구게임"),

    CART(LEVEL2, "장바구니"),
    PAYMENT(LEVEL2, "결제"),
    SUBWAY_ROUTE_MAP(LEVEL2, "지하철노선도"),

    CAPACITY_IMPROVEMENT(LEVEL4, "성능개선"),
    DEPLOYMENT(LEVEL4, "배포");

    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    private static Mission findByName(String comparedName) {
        return Arrays.stream(Mission.values())
                .filter(m -> m.name.equals(comparedName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다."));
    }

    public static Mission findByNameAndLevel(String name, Level level) {
        Mission mission = Mission.findByName(name);

        if (!mission.level.equals(level)) {
            throw new IllegalArgumentException("레벨과 미션이 일치하지 않습니다.");
        }

        return mission;
    }
}
