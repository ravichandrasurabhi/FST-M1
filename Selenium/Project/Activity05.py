from selenium import webdriver
from selenium.webdriver.common.by import By


with webdriver.Firefox() as driver:

    driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users")
   
    username = driver.find_element(By.ID,"user_name")
    password = driver.find_element(By.ID,"username_password")
    username.send_keys("admin")
    password.send_keys("pa$$w0rd")
    driver.find_element(By.ID,"bigbutton").click()
    userMenu = driver.find_element(By.ID,"with-label")
    assert(userMenu.is_displayed)
    print("User Logged in Successful : ")

    navigationMenu = driver.find_element(By.ID,"toolbar")
    print("Color of navigation Menu : " ,navigationMenu.value_of_css_property("color"))
    driver.quit()
