import org.example.Main;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.example.Main.browser;

public class ExistingRecordSearch {
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
    public void searchForExistingRecordPositive() {
        Main.fillingLoginForm();
        Main.searchRecord();
        Assert.assertEquals("Skaičiai", browser.getTitle());
        Assert.assertTrue(Main.searchRecord());
    }
    @Test(priority = 2)
    public void searchForExistingRecordNegative() {
        Main.fillingLoginForm();
        boolean actual = Main.searchRecord();
        Assert.assertTrue(actual, "Boolean condition is not correct.");
    }
}
