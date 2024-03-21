package io.ssafy.mallook.domain.chatgpt.prompt;

public class Prompt {

    public static String generateQuestionPrompt(String question) {
        String order = "비문이 없었으면 좋겠어.";
        return String.format("%s한 코디가 당신에게 어울려요 같은 느낌의 문장으로 끝나게 답변을 받고 싶어. %s", question, order);
    }
}
