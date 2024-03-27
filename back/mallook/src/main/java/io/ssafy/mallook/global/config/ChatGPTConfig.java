package io.ssafy.mallook.global.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ChatGPTConfig {

    public static final Integer MAX_TOKEN = 2000;
    public static final String MODEL = "gpt-3.5-turbo";
    public static final double TOP_P = 1.0;
    public static final double TEMPERATURE = 1.0;

    @Value("${chatgpt.api-key}")
    private String token;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(token, Duration.ofSeconds(60));
    }
}
