from selenium import webdriver
from selenium.webdriver.common.by import By


with webdriver.Firefox() as driver:

    driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users")
    copyrigntText = driver.find_element(By.ID,"admin_options")
    print("The copyRight Text = " ,copyrigntText.text)
    driver.quit()
