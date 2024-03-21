package io.ssafy.mallook.domain.chatgpt.prompt;

public class Prompt {

    public static String generateQuestionPrompt(String question) {
        String strict = "내가 주는 키워드는 반드시 들어가거나 비슷한 의미의 단어로 대치해야해." +
                "명령이야 따옴표 절대 넣지마" +
                " 물론이죠! 나 감탄사와 따옴표를 넣지마" +
                " 그냥 한 문장의 느낌을 내고 싶어." +
                "비문이 없어야 해" +
                "단순한 단어의 나열이 아닌, 완성된 문장이 나와야해.";
        return String.format("\"%s한 코디가 당신에게 어울려요\" 와 비슷한 문장으로 끝나게 답변을 받고 싶어. %s", question, strict);
    }
}
