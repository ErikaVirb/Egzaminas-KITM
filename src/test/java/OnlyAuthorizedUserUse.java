import org.example.Main;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OnlyAuthorizedUserUse {
    @BeforeMethod
    public static void setUp() {
        Main.setup();
    }

    @AfterMethod
    public static void closeBrowser(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            Main.takeScreenshots();
        }
        Main.closeBrowser();
    }

    @Test(priority = 1)
    public void onlyAuthorizedUserUsePositive() {
        Main.fillingLoginForm();
        String actualResult = Main.textForAssertionNewUserLogin(Main.titleOfCalculator);
        Main.waitForElementVisibility(Main.titleOfCalculator);
        String expectedResult = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test(priority = 2)
    public void onlyAuthorizedUserUseNegative() {
        Main.fillingLoginFormNegative();
        String actualResult = Main.textForAssertionNewUserLogin(Main.titleOfCalculator);
        Main.waitForElementVisibility(Main.titleOfCalculator);
        String expectedResult = "Skaičiuotuvas jautrus neigiamiems skaičiams ;)";
        Assert.assertNotEquals(actualResult, expectedResult);
    }
}
