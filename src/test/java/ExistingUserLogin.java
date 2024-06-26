import org.example.Main;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExistingUserLogin {
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
    public void existingUserLoginPositive(){
        String actualTitle = Main.textForAssertionNewUserLogin(Main.titleTextForLogin);
        String expectedTitle = "Prisijungimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillingLoginForm();
        String actualUser = Main.textForAssertionNewUserLogin(Main.checkLoginTitle);
        String expectedUser = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertEquals(actualUser, expectedUser);
    }
    @Test (priority = 2)
    public void existingUserLoginNegative(){
        String actualTitle = Main.textForAssertionNewUserLogin(Main.titleTextForLogin);
        String expectedTitle = "Prisijungimas";
        Assert.assertEquals(actualTitle, expectedTitle);
        Main.fillingLoginFormNegative();
        String actualErrorMessage = Main.textForAssertionNewUserLogin(Main.loginErrorMessage);
        String expectedErrorMessage1 = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage1);
    }

}
// LIKO ATASKAITŲ DARYMĄ PASIDARYTI !!!!!!!!!!!!!!! (run -> Edit Configurations ->
// " + " -> TestNG -> Class: "...Test" -> Listeners -> " + " ->
// EmailableReporter2 -> OK -> Applay -> OK