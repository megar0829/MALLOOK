package io.ssafy.mallook.domain.grade.entity;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public enum Level {
    LEVEL7(1000001, 4L, null),
    LEVEL6(1000001, 3L, LEVEL7),
    LEVEL5(500001, 3L, LEVEL6),
    LEVEL4(200001, 2L, LEVEL5),
    LEVEL3(10001, 2L, LEVEL4),
    LEVEL2(2001, 1L, LEVEL3),
    LEVEL1(0, 1L, LEVEL2);

    final int nextExp;
    public final Long discountRate;
    final Level nextLevel;

    Level(int nextExp, Long discountRate, Level nextLevel) {
        this.nextExp = nextExp;
        this.discountRate = discountRate;
        this.nextLevel = nextLevel;
    }
    public static List<Integer> getExpRange(Level level) {
        List<Integer> rslt = List.of(level.nextExp, level.nextLevel.nextExp-1);
        return rslt;
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
        return LEVEL1;
    }

}
