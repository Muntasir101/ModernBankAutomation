package tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomerRegisterPage;
import utils.ConfigReader;
import java.time.Duration;

public class CustomerRegisterTest {
    private WebDriver driver;
    private CustomerRegisterPage registerPage;

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

        // Navigate to register page
        driver.get(ConfigReader.getProperty("base.url"));

        // Initialize page object
        registerPage = new CustomerRegisterPage(driver);
    }

    @Test(priority = 1, description = "Test successful Register with valid credentials")
    public void testSuccessfulRegister() {
        String baseEmail = ConfigReader.getProperty("email");
        String uniqueEmail = baseEmail.split("@")[0]
                + System.currentTimeMillis()
                + "@" + baseEmail.split("@")[1];

        String password = ConfigReader.getProperty("password");
        String fullName =  ConfigReader.getProperty("fullName");
        String initialDepositAmount =  ConfigReader.getProperty("deposit_amount");

        registerPage.register(fullName, uniqueEmail, password, initialDepositAmount);

    }

    @Test(priority = 2, description = "Test Invalid Register with blank Name")
    public void testInvalidRegisterWithBlankName() {
        String baseEmail = ConfigReader.getProperty("email");
        String uniqueEmail = baseEmail.split("@")[0]
                + System.currentTimeMillis()
                + "@" + baseEmail.split("@")[1];

        String password = ConfigReader.getProperty("password");
        String fullName =  "";
        String initialDepositAmount =  ConfigReader.getProperty("deposit_amount");

        registerPage.register(fullName, uniqueEmail, password, initialDepositAmount);

    }

    @Test(priority = 3, description = "Test Invalid Register with blank Email")
    public void testInvalidRegisterWithBlankEmail() {
        String uniqueEmail = "";
        String password = ConfigReader.getProperty("password");
        String fullName =  "Smith";
        String initialDepositAmount =  ConfigReader.getProperty("deposit_amount");

        registerPage.register(fullName, uniqueEmail, password, initialDepositAmount);

    }

    @Test(priority = 4, description = "Test Invalid Register with blank Email")
    public void testInvalidRegisterWithBlankPassword() {
        String baseEmail = ConfigReader.getProperty("email");
        String uniqueEmail = baseEmail.split("@")[0]
                + System.currentTimeMillis()
                + "@" + baseEmail.split("@")[1];

        String password = "";
        String fullName =  "Smith";
        String initialDepositAmount =  ConfigReader.getProperty("deposit_amount");

        registerPage.register(fullName, uniqueEmail, password, initialDepositAmount);

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
