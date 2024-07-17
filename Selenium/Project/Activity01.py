from selenium import webdriver
from selenium.webdriver.common.by import By


with webdriver.Firefox() as driver:

    driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users")
    print("title is : ", driver.title)
    title = driver.title
    assert title == "SuiteCRM"

    driver.quit()
