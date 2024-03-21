package io.ssafy.mallook.domain.chatgpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import io.ssafy.mallook.domain.chatgpt.dto.response.GptResponseDto;
import io.ssafy.mallook.domain.chatgpt.prompt.Prompt;
import io.ssafy.mallook.domain.chatgpt.dto.request.QuestionDto;
import io.ssafy.mallook.global.config.ChatGPTConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GptService {

    private final OpenAiService openAiService;
    private final ObjectMapper objectMapper;

    public ChatCompletionResult generated(List<ChatMessage> chatMessages) {
        ChatCompletionRequest build = ChatCompletionRequest.builder()
                .messages(chatMessages)
                .model(ChatGPTConfig.MODEL)
                .maxTokens(ChatGPTConfig.MAX_TOKEN)
                .temperature(ChatGPTConfig.TEMPERATURE)
                .topP(ChatGPTConfig.TOP_P)
                .build();

        return openAiService.createChatCompletion(build);
    }

    public List<ChatMessage> generatedQuestionAndAnswerMessage(QuestionDto questionDto) {
        String prompt = Prompt.generateQuestionPrompt(questionDto.content());
        ChatMessage chatMessage = new ChatMessage(ChatMessageRole.USER.value(), prompt);

        return List.of(chatMessage);
    }

    public GptResponseDto askQuestion(QuestionDto questionDto) {
        List<ChatMessage> chatMessages = generatedQuestionAndAnswerMessage(questionDto);
        ChatCompletionResult result = generated(chatMessages);

        String gptAnswer = result.getChoices().get(0).getMessage().getContent();
        return GptResponseDto.builder()
                .answer(gptAnswer)
                .build();
    }
}
