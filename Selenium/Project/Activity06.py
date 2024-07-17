from selenium import webdriver
from selenium.webdriver.common.by import By


with webdriver.Firefox() as driver:

    driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users")
   
    username = driver.find_element(By.ID,"user_name")
    password = driver.find_element(By.ID,"username_password")
    username.send_keys("admin")
    password.send_keys("pa$$w0rd")
    driver.find_element(By.ID,"bigbutton").click()
    activitiesMenu = driver.find_element(By.ID,"grouptab_3")
    assert(activitiesMenu.is_displayed)
    print("Activities Menu is displayed")

    driver.quit()
