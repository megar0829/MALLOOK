import time
import pickle
from TwentyNineScraper import TwentyNineScraper
from multiprocessing import Pool

scraper = TwentyNineScraper()  # 스크래퍼 객체 생성


def process_category(category_data):
    categoryLargeCode = category_data.get('category_code', '')
    for category2 in category_data['category2']:
        categoryMediumCode = category2.get('category_code', '')
        if category2['category3']:  # category3가 존재하는지 확인
            pool_args = [(categoryLargeCode, categoryMediumCode, category3) for category3 in category2['category3']]
            with Pool(len(category2['category3'])) as pool:
                pool.map(process_small_category, pool_args)
        else:
            mainCategory, subCategory = category2['main_category'], category2['sub_category']
            scraper.get_twentyninecm_products_list(categoryLargeCode, categoryMediumCode, '', mainCategory, subCategory)


def process_small_category(args):
    categoryLargeCode, categoryMediumCode, small_category_data = args
    categorySmallCode = small_category_data['category_code']
    mainCategory, subCategory = small_category_data['main_category'], small_category_data['sub_category']
    scraper.get_twentyninecm_products_list(categoryLargeCode, categoryMediumCode, categorySmallCode, mainCategory, subCategory)


if __name__ == "__main__":
    # 시작 시간 기록
    start_time = time.time()

    # 카테고리 목록 불러오기
    with open("TwentyNineCategory.pkl", "rb") as f:
        category_data_list = pickle.load(f)

    # Pool 실행
    for category_data in category_data_list:
        process_category(category_data)

    end_time = time.time()
    execution_time = end_time - start_time
    print("Total elapsed time: {:.2f} seconds".format(execution_time))

    # 웹드라이버 종료
    scraper.close_webdriver()