package org.example;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Main {
    public static WebDriver browser;
    public static final int WAIT_DURATION_SECONDS = 7;

//    NAUJAS VARTOTOJAS -------------------------------------------------------------------
    public static By createNewAccountLink = By.cssSelector("a[href=\"/registruoti\"]");
    public static By titleTextForNewAccountPage = By.cssSelector(".form-signin-heading");
    public static By registrationFormNameField = By.id("username");
    public static By createUserAccountButton = By.cssSelector("button[type=\"submit\"]");
    public static By registrationFormPasswordField = By.id("password");
    public static By registrationFormPasswordConfirmationField = By.id("passwordConfirm");
    public static By checkLoginTitle = By.cssSelector("body h3");
    public static By registrationNameFieldErrorMesage = By.id("username.errors");
    public static By registrationPasswordFieldErrorMesage = By.id("password.errors");
//    ESAMAS VARTOTOJAS -----------------------------------------------------------------------------
    public static By titleTextForLogin = By.cssSelector(".form-heading");
    public static By loginFormName = By.cssSelector("input[placeholder=\"Prisijungimo vardas\"]");
    public static By loginFormPassword = By.cssSelector("input[placeholder=\"Slaptažodis\"]");
    public static By loginErrorMessage = By.cssSelector("body > div:nth-child(5) > form:nth-child(1) > div:nth-child(2) > span:nth-child(4)");
//     NAUJAS ĮRAŠAS ---------------------------------------------------------------------------------
    public static By countResult = By.xpath("//h4[contains(text(),\"2\")]");
    public static By validationError = By.cssSelector(".error");
    public static By inputFirstNumberField = By.id("sk1");
    public static By inputSecondNumberField = By.id("sk2");
    public static By dropdownOptions = By.cssSelector("select[name=\"zenklas\"]");
    public static By countButton = By.xpath("//input[@value=\"skaičiuoti\"]");
//    AUTORIZACIJA -----------------------------------------------------------------------------------
    public static By titleOfCalculator = By.cssSelector("body h3");

    public static void main(String[] args) {
        System.out.println("Puslapis - Skaičiai. Egzaminas:");
    }
    public static void setup(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver126.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");
        browser = new ChromeDriver(chromeOptions);
        browser.get("http://localhost:8080/");
    }
    public static void closeBrowser() {
//        if (browser != null) {
//            browser.quit();
//        }
    }
    public static void waitForElementVisibility(By locator){
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(WAIT_DURATION_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void clickOnLink(By locator){
        browser.findElement(locator).click();
    }
    public static String textForAssertionNewUserLogin(By locator){
        WebElement element = browser.findElement(locator);
        return element.getText();
    }
    public static void sendKeysName(String keyword){
        WebElement searchInput = browser.findElement(registrationFormNameField);
        searchInput.sendKeys(keyword);
    }
    public static void sendKeysPassword(String keyword){
        WebElement searchInput = browser.findElement(registrationFormPasswordField);
        searchInput.sendKeys(keyword);
    }
    public static void sendKeysPasswordConfirmation(String keyword){
        WebElement searchInput = browser.findElement(registrationFormPasswordConfirmationField);
        searchInput.sendKeys(keyword);
    }
    public static void clickButton(By locator){
        browser.findElement(locator).click();
    }
    public static void fillingRegistrationForm(){
        Main.waitForElementVisibility(registrationFormNameField);
        Main.sendKeysName("Adamaleii");
        Main.sendKeysPassword("kamikadzė1233");
        Main.sendKeysPasswordConfirmation("kamikadzė1233");
        Main.clickButton(createUserAccountButton);
    }
    public static void fillingRegistrationFormNegative(){
        Main.waitForElementVisibility(registrationFormNameField);
        Main.sendKeysName("am");
        Main.sendKeysPassword("ka");
        Main.sendKeysPasswordConfirmation("tu");
        Main.clickButton(createUserAccountButton);
    }
    public static void sendKeysLoginName(String keyword){
        WebElement searchInput = browser.findElement(loginFormName);
        searchInput.sendKeys(keyword);
    }
    public static void sendKeysLoginPassword(String keyword){
        WebElement searchInput = browser.findElement(loginFormPassword);
        searchInput.sendKeys(keyword);
    }
    public static void fillingLoginForm(){
        Main.waitForElementVisibility(loginFormName);
        Main.sendKeysLoginName("Adamas");
        Main.sendKeysLoginPassword("kamikadzė123");
        Main.clickButton(createUserAccountButton);
    }
    public static void fillingLoginFormNegative() {
        Main.waitForElementVisibility(loginFormName);
        Main.sendKeysLoginName("Adam");
        Main.sendKeysLoginPassword("kamikadzė123");
        Main.clickButton(createUserAccountButton);
    }
    public static void choseTransactionSign(By locator){
        WebElement dropdownElement = browser.findElement(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Dalyba");
    }
    public static void fillInputWithNumbersAndGetResult(String numberOne, String numberTwo, String result){
        WebElement firstNumber = browser.findElement(inputFirstNumberField);
        firstNumber.clear();
        firstNumber.sendKeys(numberOne);
        WebElement secondNumber = browser.findElement(inputSecondNumberField);
        secondNumber.clear();
        secondNumber.sendKeys(numberTwo);
        choseTransactionSign(dropdownOptions);
        clickButton(countButton);
    }
    public static boolean searchRecord(){
        browser.findElement(By.linkText("Atliktos operacijos")).click();
        try {
            WebElement irasas = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[4]"));
            return irasas.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static void takeScreenshots() {
        String name = new SimpleDateFormat("yyyy_MM_dd_HH-mm-ss").format(new Date());
        File imgFile = ((TakesScreenshot) browser).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(imgFile, new File("src/test/screenshots/" + name + "_screenshot.png"));
        } catch (IOException error) {
            System.out.println("Nepavyko padaryti screenshot. Placiau:" + error.getMessage());
        }
    }
}