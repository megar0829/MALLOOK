import requests
from bs4 import BeautifulSoup as bs
import os
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "Blog_Crawling.settings")
import django
django.setup()

from articles.models import Blogs, BlogData

def gabia():
    page = requests.get("https://library.gabia.com/")
    soup = bs(page.text, "html.parser")
    elements = soup.select('div.esg-entry-content a.eg-grant-element-0')[:20]

    data = {}
    for index, element in enumerate(elements, 1):
        data[element.text] = element.attrs['href']
    return data

def kurly():
    page = requests.get("https://helloworld.kurly.com/")
    soup = bs(page.text, "html.parser")
    elements = soup.select('body > main > div > div > ul > li > a')[:20]
    titles = soup.select('body > main > div > div > ul > li > a > h3')
    data = {}
    base_link = 'https://helloworld.kurly.com'

    titles = [a.get_text(strip=True) for a in soup.select('body > main > div > div > ul > li > a > h3')[:20]]
    links = [base_link + a.get('href') for a in soup.select('body > main > div > div > ul > li > a')[:20]]
    
    for i in range(20):
        data[titles[i]] = links[i]
    
    return data

def geeknews():
    req = requests.get('https://news.hada.io/weekly')
    soup = bs(req.content, 'html.parser')
    base_link = 'https://news.hada.io'
    my_titles = soup.select(
        'body > main > div > div.weekly > div > a'
    )
    data = {}
    for title in my_titles:
        if title.text != '더 이전 Weekly 보기':
            data[title.text] = base_link + title.get('href')
    return data

def yozm():
    # 기본 주소
    base_url = 'https://yozm.wishket.com/magazine/list/develop/?page={}&sort=new&q='
    data = {}

    base_link = 'https://yozm.wishket.com'

    # 여러 페이지에서 데이터 수집
    for page_num in range(1, 3):
        url = base_url.format(page_num)
        response = requests.get(url)
        html = response.text
        soup = bs(html, 'html.parser')

        # 원하는 데이터 추출
        titles = [a.get_text(strip=True) for a in soup.select('div.list-cover div.item-main.text900 a.item-title')]
        links = [base_link + a.get('href') for a in soup.select('div.list-cover div.item-main.text900 a.item-title')]
        
        for i in range(len(titles)):
            data[titles[i]] = links[i]
            
    return data

def toss():
    # 기본 주소
    url = 'https://toss.tech/tech'
    response = requests.get(url)
    html = response.text
    soup = bs(html, 'html.parser')
    data = {}

    base_link = 'https://toss.tech'
    
    # 원하는 데이터 추출
    titles = [a.get_text(strip=True) for a in soup.select('a.css-15qqv3q.e3wfjt72 div span.css-ad3vzk.e3wfjt74')]
    links = [base_link + a['href'] for a in soup.select('a.css-15qqv3q.e3wfjt72')]
    
    for i in range(20):
        data[titles[i]] = links[i]

    return data


def ridi():
    # 기본 주소
    base_url = 'https://ridicorp.com/story-category/tech-blog/page/{}/'
    data = {}

    base_link = 'https://ridicorp.com'

    # 여러 페이지에서 데이터 수집
    for page_num in range(1, 3):
        url = base_url.format(page_num)
        response = requests.get(url)
        html = response.text
        soup = bs(html, 'html.parser')

        # 원하는 데이터 추출
        titles = [a.get_text(strip=True) for a in soup.select('li > div.entry-meta > h3 > a')]
        links = [a.get('href') for a in soup.select('li > div.entry-meta > h3 > a')]
        
        for i in range(len(titles)):
            data[titles[i]] = links[i]
            
    return data


def kakaopay():
    data = {}
    base_link = 'https://tech.kakaopay.com'
    # 여러 페이지에서 데이터 수집 (20개)
    for page_num in range(1, 5):
        url = f'https://tech.kakaopay.com/page/{page_num}/'
        response = requests.get(url)
        html = response.text
        soup = bs(html, 'html.parser')

        base_link = 'https://tech.kakaopay.com/'
        my_titles = soup.select(
            'li > a > div._postInfo_aknty_99 > strong'
        )
        my_links = soup.select(
            'li > a'
        )
        for title, link in zip(my_titles, my_links):
            data[title.text] = base_link + link.get('href')

    return data


def netmarble():
    url = 'https://netmarble.engineering/'
    response = requests.get(url)
    html = response.text
    soup = bs(html, 'html.parser')
    data = {}
    my_titles = soup.select(
        
        '.entry-title > a'
    )

    
    for title in my_titles:
        data[title.text] = title.get('href')

    return data


blog_list = [toss, yozm, geeknews, kurly, gabia, ridi, kakaopay, netmarble]


# 기존 블로그 게시글이 있으면 pass 없으면 추가
def crawling_blog_data():
    for blog_function in blog_list:
        blog_name = blog_function.__name__
        blog_entry, created = Blogs.objects.get_or_create(name=blog_name)

        if created:
            blog_data_dict = blog_function()

            for title, link in blog_data_dict.items():
                if not BlogData.objects.filter(blog=blog_entry, title=title).exists():
                    BlogData.objects.create(blog=blog_entry, title=title, link=link)
        else:
            existing_titles = set(BlogData.objects.filter(blog=blog_entry).values_list('title', flat=True))
            blog_data_dict = blog_function()

            for title, link in blog_data_dict.items():
                if title not in existing_titles:
                    BlogData.objects.create(blog=blog_entry, title=title, link=link)
