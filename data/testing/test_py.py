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

# 웹 드라이버 로드
driver = webdriver.Chrome(options=options)

# 암시적 대기 설정 (10초)
driver.implicitly_wait(5)

# 웹 페이지 열기
url = 'https://www.hiver.co.kr/products/115619185'
driver.get(url)

# 버튼 클릭하여 요소 로드 대기
button = driver.find_element(By.CSS_SELECTOR, 'button.order.css-xnq7lu')
button.send_keys(Keys.ENTER)

# li_selector = 'div.bottom-modal.modal-wrap.purchaseModal.css-2aucks.modal-open > div.modal-content.css-16fsmug > div.modal-body.body-scroll.scroll-top.css-kd9sl0 > div.optionWrap > details.product-option.css-zzmtgj > ul > li'
# li_elements = driver.find_element(By.CSS_SELECTOR, li_selector)
# for li in li_elements:
#     print(li)
# print(2, 'end')

# JavaScript를 사용하여 요소 선택
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
        product.click()
        break

sizes = driver.execute_script('''
    var sizeNames = [];
    var sizeElements = document.querySelectorAll('details.product-option.css-zzmtgj:nth-child(2) p.name');
    sizeElements.forEach(function(elem) {
        sizeNames.push(elem.textContent.trim());
    });
    return sizeNames;
''')
print(colors)
print(sizes)