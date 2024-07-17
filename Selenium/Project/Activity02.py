from selenium import webdriver
from selenium.webdriver.common.by import By


with webdriver.Firefox() as driver:

    driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users")
    imageUrl = driver.find_element(By.XPATH,"//div/img")
    print("The Image URL = " ,imageUrl.get_attribute("src"))
    driver.quit()
