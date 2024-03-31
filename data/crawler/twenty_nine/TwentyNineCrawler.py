import time
import pickle
from TwentyNineScraper import TwentyNineScraper
from multiprocessing import Pool
import os


scraper = TwentyNineScraper()


def process_category(category_data):
    categoryLargeCode = category_data.get('category_code', '')  # 대분류 코드
    for category2 in category_data['category2']:
        categoryMediumCode = category2.get('category_code', '') # 중분류 코드

        # 소분류가 존재하는지 확인
        if category2['category3']:  
            pool_args = [(categoryLargeCode, categoryMediumCode, category3) for category3 in category2['category3']]

            # 소분류의 개수만큼 멀티프로세싱 풀 생성
            with Pool(len(category2['category3'])) as pool:
                pool.map(process_small_category, pool_args)

        else:   # 소분류가 없다면 중분류 번호까지만 넣고 요청
            mainCategory, subCategory = category2['main_category'], category2['sub_category']
            scraper.get_twentyninecm_products_list(categoryLargeCode, categoryMediumCode, '', mainCategory, subCategory)


# 소분류 프로세스
def process_small_category(args):
    categoryLargeCode, categoryMediumCode, small_category_data = args
    categorySmallCode = small_category_data['category_code']    # 소분류 코드
    mainCategory, subCategory = small_category_data['main_category'], small_category_data['sub_category']
    scraper.get_twentyninecm_products_list(categoryLargeCode, categoryMediumCode, categorySmallCode, mainCategory, subCategory)


if __name__ == "__main__":
    # 시작 시간 기록
    start_time = time.time()

    # 카테고리 목록 불러오기
    script_directory = os.path.dirname(os.path.abspath(__file__))
    file_path = os.path.join(script_directory, "TwentyNineCategory.pkl")
    with open(file_path, "rb") as f:
        category_data_list = pickle.load(f)

    # Pool 실행
    for category_data in category_data_list:
        process_category(category_data)

    # 종료 시간 출력
    end_time = time.time()
    execution_time = end_time - start_time
    print("Total elapsed time: {:.2f} seconds".format(execution_time))

    # 웹드라이버 종료
    scraper.close_webdriver()