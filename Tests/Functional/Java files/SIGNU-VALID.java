package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class IDDYSIGNUVALID {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/IDDY/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testIDDYSIGNUVALID() throws Exception {
    driver.get(baseUrl + "/IDDY/");
    driver.findElement(By.linkText("Inscription")).click();
    driver.findElement(By.id("j_idt45:password")).clear();
    driver.findElement(By.id("j_idt45:password")).sendKeys("1234");
    driver.findElement(By.id("j_idt45:login_name")).clear();
    driver.findElement(By.id("j_idt45:login_name")).sendKeys("testCaseUser");
    driver.findElement(By.id("j_idt45:email")).clear();
    driver.findElement(By.id("j_idt45:email")).sendKeys("testCaseUser@user.com");
    driver.findElement(By.id("j_idt45:bio")).clear();
    driver.findElement(By.id("j_idt45:bio")).sendKeys("My biography for a Selenium test");
    driver.findElement(By.name("j_idt45:j_idt50")).click();
    assertEquals(driver.findElement(By.cssSelector("td")).getText(), "User was successfully created.");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
