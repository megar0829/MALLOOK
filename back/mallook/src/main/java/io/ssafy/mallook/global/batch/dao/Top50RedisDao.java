package io.ssafy.mallook.global.batch.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.ssafy.mallook.global.batch.dto.TopScriptDto;
import io.ssafy.mallook.global.batch.dto.TopStyleDto;
import io.ssafy.mallook.global.util.LocalDateTimeAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class Top50RedisDao {

    private final StringRedisTemplate redisTemplate;
    private final Gson gson;
    private static final String TOP_SCRIPTS_HEART = "top-50-scripts:";
    private static final String TOP_STYLES_HEART = "top-50-styles:";

    public void saveScripts(TopScriptDto topScriptDto, long timeout) {
        String jsonList = gson.toJson(topScriptDto);
        redisTemplate.opsForValue().set(TOP_SCRIPTS_HEART, jsonList, timeout, TimeUnit.MINUTES);
    }

    public void saveStyles(TopStyleDto topStyleDto, long timeout) {
        String jsonList = gson.toJson(topStyleDto);
        redisTemplate.opsForValue().set(TOP_STYLES_HEART, jsonList, timeout, TimeUnit.MINUTES);
    }

    public TopScriptDto getScriptsDto() {
        String jsonList = redisTemplate.opsForValue().get("top-50-scripts:");
        return gson.fromJson(jsonList, TopScriptDto.class);
    }

    public TopStyleDto getStylesDto() {
        String jsonList = redisTemplate.opsForValue().get("top-50-styles:");
        return gson.fromJson(jsonList, TopStyleDto.class);
    }
}
