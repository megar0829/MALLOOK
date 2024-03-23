package io.ssafy.mallook.domain.grade.entity;

import java.util.Objects;

public enum Level {
    LEVEL7(1000001, null),
    LEVEL6(1000001, LEVEL7),
    LEVEL5(500001, LEVEL6),
    LEVEL4(200001, LEVEL5),
    LEVEL3(10001, LEVEL4),
    LEVEL2(2001, LEVEL3),
    LEVEL1(0, LEVEL2);

    private final int nextExp;
    private final Level nextLevel;

    Level(int nextExp, Level nextLevel) {
        this.nextExp = nextExp;
        this.nextLevel = nextLevel;
    }

    public static boolean availableLevelUp(Level level, Long exp) {
        if (Objects.isNull(level)) {
            return false;
        }
        if (Objects.isNull(level.nextLevel)) {
            return false;
        }
        return exp >= level.nextExp;
    }
    public static Level getNextGrade(Long exp) {
        if (exp >= LEVEL6.nextExp) return LEVEL6.nextLevel;
        if (exp >= LEVEL5.nextExp ) return LEVEL5.nextLevel;
        if (exp >= LEVEL4.nextExp ) return LEVEL4.nextLevel;
        if (exp >= LEVEL3.nextExp ) return LEVEL3.nextLevel;
        if (exp >= LEVEL2.nextExp ) return LEVEL2.nextLevel;
        if (exp >= LEVEL1.nextExp ) return LEVEL1.nextLevel;
        return LEVEL1; // todo: 수정
    }
}
