from konlpy.tag import Kkma


def compare_word_meaning(word1, word2):
    kkma = Kkma()
    morphs1 = kkma.morphs(word1)
    morphs2 = kkma.morphs(word2)

    # 두 단어의 형태소 분석 결과를 비교하여 유사한 형태소가 있는지 확인
    for morph1 in morphs1:
        for morph2 in morphs2:
            if morph1 == morph2:
                return True
    
    return False

word1 = "레이어드로도"
word2 = "레이어드"
result = compare_word_meaning(word1, word2)
print(result)