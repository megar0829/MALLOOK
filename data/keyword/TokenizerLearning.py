import urllib.request
from soynlp import DoublespaceLineCorpus
from soynlp.word import WordExtractor


urllib.request.urlretrieve("https://raw.githubusercontent.com/lovit/soynlp/master/tutorials/2016-10-20.txt", filename="2016-10-20.txt")
corpus = DoublespaceLineCorpus("2016-10-20.txt")

word_extractor = WordExtractor()
word_extractor.train(corpus)
word_score_table = word_extractor.extract()

print(word_score_table)