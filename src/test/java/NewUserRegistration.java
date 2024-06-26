import org.example.Main;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewUserRegistration {

    @BeforeMethod
    public static void setUp() {
        Main.setup();
    }

    @AfterMethod
    public static void closeBrowser(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            Main.takeScreenshots();
        }
        Main.closeBrowser();
    }
    @Test(priority = 1)
    public void registrationOfNewUserPositive(){
        Main.waitForElementVisibility(Main.createNewAccountLink);
        Main.clickOnLink(Main.createNewAccountLink);
        String actualTitle = Main.textForAssertionNewUserLogin(Main.titleTextForNewAccountPage);
        String expectedTitle = "Naujos paskyros sukūrimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillingRegistrationForm();
        String actualUser = Main.textForAssertionNewUserLogin(Main.checkLoginTitle);
        String expectedUser = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertEquals(actualUser, expectedUser);
    }
    @Test (priority = 2)
    public void registrationOfNewUserNegative(){
        Main.waitForElementVisibility(Main.createNewAccountLink);
        Main.clickOnLink(Main.createNewAccountLink);
        String actualTitle = Main.textForAssertionNewUserLogin(Main.titleTextForNewAccountPage);
        String expectedTitle = "Naujos paskyros sukūrimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillingRegistrationFormNegative();
        String actualErrorMessage = Main.textForAssertionNewUserLogin(Main.registrationNameFieldErrorMesage);
        String expectedErrorMessage = "Privaloma įvesti nuo 3 iki 32 simbolių";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        String actualErrorMessage1 = Main.textForAssertionNewUserLogin(Main.registrationPasswordFieldErrorMesage);
        String expectedErrorMessage1 = "Privaloma įvesti bent 3 simbolius";
        Assert.assertEquals(actualErrorMessage1, expectedErrorMessage1);
    }
}
// LIKO ATASKAITŲ DARYMĄ PASIDARYTI !!!!!!!!!!!!!!! (run -> Edit Configurations ->
// " + " -> TestNG -> Class: "...Test" -> Listeners -> " + " ->
// EmailableReporter2 -> OK -> Applay -> OK
