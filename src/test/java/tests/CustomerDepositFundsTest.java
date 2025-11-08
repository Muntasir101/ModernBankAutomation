package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomerLoginPage;
import pages.CustomerDepositFundsPage;
import utils.ConfigReader;

import java.time.Duration;

public class CustomerDepositFundsTest {
    private WebDriver driver;
    private CustomerLoginPage loginPage;
    private CustomerDepositFundsPage depositFundsPage;

    @BeforeMethod
    public void setUp() {
        // Initialize driver based on configuration
        String browser = ConfigReader.getProperty("browser");

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to login page
        driver.get(ConfigReader.getProperty("base.url"));

        // Initialize page objects
        loginPage = new CustomerLoginPage(driver);

        depositFundsPage = new CustomerDepositFundsPage(driver);
    }

    @Test(priority = 1, description = "Test login and deposit funds in one flow")
    public void testDeposit() {


        // Login first
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");
        loginPage.login(email, password);

        // Then deposit funds
        String amount = "100";
        String description = "Test Description";
        depositFundsPage.depositFunds(amount, description);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}