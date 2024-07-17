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

    wait.until(EC.visibility_of_element_located((By.ID, "moduleTab_9_Accounts")))

    driver.find_element(By.ID, "moduleTab_9_Accounts").click()

    print("User navigated to Sales > Accounts ")

    wait.until(
        EC.visibility_of_element_located(
            (
                By.XPATH,
                "//*[@class='list-view-rounded-corners']//tbody[1]/tr[contains(@class,'oddListRow')][1]",
            )
        )
    )

    listOfElements = driver.find_elements(
        By.XPATH,
        "//*[@class='list-view-rounded-corners']//tbody[1]/tr[contains(@class,'oddListRow')]",
    )

    rangeUpTo = 1
    for ele in listOfElements:

        if rangeUpTo <= 5:
            print(ele.text)
            rangeUpTo += 1

    driver.quit()
