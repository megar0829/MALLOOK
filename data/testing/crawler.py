# 패키지 import
import asyncio # 비동기 라이브러리
from playwright.async_api import Playwright, async_playwright

async def run(playwright: Playwright) -&gt; None:
    # headless=False =&gt; 브라우저 GUI 모드로 실행
    browser = await playwright.chromium.launch(headless=False) # 크롬 사용
    context = await browser.new_context()  # 새 콘텍스트 생성
    page = await context.new_page()  # 새 페이지 열기
    await page.goto("&lt;https://naver.com/&gt;")  # 네이버 홈페이지로 이동

    await context.close() # 콘텍스트 종료
    await browser.close() # 브라우저 종료

async def main() -&gt; None:
	async with async_playwright() as playwright:
    	await run(playwright)

asyncio.run(main()) # 실행