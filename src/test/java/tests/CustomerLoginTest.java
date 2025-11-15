package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomerLoginPage;
import utils.ConfigReader;

import java.time.Duration;

public class CustomerLoginTest {
    private WebDriver driver;
    private CustomerLoginPage loginPage;

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

        // Initialize page object
        loginPage = new CustomerLoginPage(driver);
    }

    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testSuccessfulLogin() {
        String email = ConfigReader.getProperty("email");
        String password = ConfigReader.getProperty("password");

        loginPage.login(email, password);

        // Verify Success Message
        if (loginPage.getSuccessMessage().equals("Login successful! Welcome back.")) {
            System.out.println("Test Passed for Login with Valid credentials.Actual Message is: "+loginPage.getSuccessMessage());
        } else {
            System.out.println("Test Failed for Login with Valid credentials.Actual Message is: "+loginPage.getSuccessMessage());
        }

    }

    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testLoginWithInvalidCredentials() {
        String email = "invalid@mail.com";
        String password = "112222";

        loginPage.login(email, password);

        // Verify Success Message
        if (loginPage.getSuccessMessage().equals("Invalid email or password.")) {
            System.out.println("Test Passed for Login with inValid credentials.Actual Message is: "+loginPage.getSuccessMessage());
        } else {
            System.out.println("Test Passed for Login with inValid.Actual Message is: "+loginPage.getSuccessMessage());
        }

    }

    @Test(priority = 2, description = "Test login with blank email")
    public void testLoginWithBlankEmail() {
        String email = "";
        String password = ConfigReader.getProperty("password");
        loginPage.login(email, password);

        // Verify Error Message
        if (loginPage.getErrorMessage().equals("Please enter both email and password.")) {
            System.out.println("Test Passed for Empty Email.Actual Message is: "+loginPage.getErrorMessage());
        } else {
            System.out.println("Test Failed for Empty Email.Actual Message is: "+loginPage.getErrorMessage());
        }

    }

    @Test(priority = 3, description = "Test login with blank password")
    public void testLoginWithBlankPassword() {
        String email = ConfigReader.getProperty("email");
        String password = "";
        loginPage.login(email, password);

        // Verify Error Message
        if (loginPage.getErrorMessage().equals("Please enter both email and password.")) {
            System.out.println("Test Passed for Empty Email.Actual Message is: "+loginPage.getErrorMessage());
        } else {
            System.out.println("Test Failed for Empty Email.Actual Message is: "+loginPage.getErrorMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}