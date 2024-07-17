from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.action_chains import ActionChains

with webdriver.Firefox() as driver:

    driver.get("https://alchemy.hguy.co/crm/index.php?action=Login&module=Users")

    username = driver.find_element(By.ID, "user_name")
    password = driver.find_element(By.ID, "username_password")
    username.send_keys("admin")
    password.send_keys("pa$$w0rd")
    driver.find_element(By.ID, "bigbutton").click()

    wait = WebDriverWait(driver, 10)
    wait.until(EC.visibility_of_element_located((By.ID, "grouptab_0")))
    act = ActionChains(driver)

    act.move_to_element(driver.find_element(By.ID, "grouptab_0")).perform()

    wait.until(EC.visibility_of_element_located((By.ID, "moduleTab_9_Leads")))

    driver.find_element(By.ID, "moduleTab_9_Leads").click()

    print("User navigated to Sales > Leads ")

    wait.until(
        EC.visibility_of_element_located(
            (By.XPATH, "//span[@title='Additional Details']")
        )
    )

    listOfElements = driver.find_elements(
        By.XPATH, "//span[@title='Additional Details']"
    )

    listOfElements[0].click()
    print("clicked on on first info icon")

    wait.until(
        EC.visibility_of_element_located(
            (
                By.XPATH,
                ".//div[contains(@style,'display: block') and contains(@aria-describedby,'ui-id')]//span[@class='phone']",
            )
        )
    )
    phoneNumber = driver.find_element(
        By.XPATH,
        ".//div[contains(@style,'display: block') and contains(@aria-describedby,'ui-id')]//span[@class='phone']",
    )

    print("Phone number : ", phoneNumber.text)

    driver.quit()
