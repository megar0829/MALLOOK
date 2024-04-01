import os
import re
from soynlp.word import WordExtractor
from pykospacing import Spacing

spacing = Spacing()

current_directory = os.path.dirname(os.path.abspath(__file__))
reviews_file_path = os.path.join(current_directory, "reviews.txt")

with open(reviews_file_path, "r", encoding="utf-8") as file:
    corpus = file.read()
    corpus = re.sub(r'[^가-힣]+', ' ', corpus)

model_save_path = os.path.join(current_directory, "word_extractor_model.pkl")

word_extractor = WordExtractor()
word_extractor.train(corpus)
word_extractor.save(model_save_path)

# 학습한 단어 점수 출력
scores = word_extractor.extract()
for word, score in scores.items():
    print(word, score)