from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from fake_useragent import UserAgent

# Chrome 옵션 설정
options = Options()
options.add_experimental_option("detach", True)

user_agent = UserAgent().random
options.add_argument(f'user-agent={user_agent}')
options.add_argument('headless')

# 웹 드라이버 로드
driver = webdriver.Chrome(options=options)

# 암시적 대기 설정 (10초)
driver.implicitly_wait(5)

# 웹 페이지 열기
url = 'https://www.hiver.co.kr/products/140967253'
driver.get(url)

# 버튼 클릭하여 요소 로드 대기
button = driver.find_element(By.CSS_SELECTOR, 'button.order.css-xnq7lu')
button.send_keys(Keys.ENTER)

# 색상 선택
colors = driver.execute_script('''
    var names = [];
    var elements = document.querySelectorAll('p.name');
    elements.forEach(function(elem) {
        names.push(elem.textContent.trim());
    });
    return names;
''')

# 품절이 아닌 상품 클릭
product_list = driver.find_elements(By.CSS_SELECTOR, 'div.bottom-modal.modal-wrap.purchaseModal.css-2aucks.modal-open li')
for product in product_list:
    if '품절' in product.text:
        continue
    else:
        try:
            WebDriverWait(driver, 2).until(EC.element_to_be_clickable((By.CSS_SELECTOR, 'div.bottom-modal.modal-wrap.purchaseModal.css-2aucks.modal-open li'))).click()
            break
        except:
            print("상품을 클릭할 수 없습니다.")

# 사이즈 선택
sizes = driver.execute_script('''
    var sizeNames = [];
    var sizeElements = document.querySelectorAll('details.product-option.css-zzmtgj:nth-child(2) p.name');
    sizeElements.forEach(function(elem) {
        sizeNames.push(elem.textContent.trim());
    });
    return sizeNames;
''')