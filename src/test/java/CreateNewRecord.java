import org.example.Main;
import org.example.Main;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateNewRecord {
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
    public void createNewRecordPositive() {
        Main.fillingLoginForm();
        Main.fillInputWithNumbersAndGetResult("16","2","-");
        String actualResult = Main.textForAssertionNewUserLogin(Main.countResult);
        Main.waitForElementVisibility(Main.countResult);
        String expectedResult = "16 / 2 = 8";
        Assert.assertEquals(actualResult, expectedResult);
    }
    @Test(priority = 2)
    public void createNewRecordNegative() {
        Main.fillingLoginForm();
        Main.fillInputWithNumbersAndGetResult("-16","2","-");
        String actualResult = Main.textForAssertionNewUserLogin(Main.validationError);
        String expectedResult = "Validacijos klaida: skaičius negali būti neigiamas";
        Assert.assertEquals(actualResult, expectedResult);
    }

}
