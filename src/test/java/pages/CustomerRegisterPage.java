package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CustomerRegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Web Elements
    @FindBy(id = "register-btn")
    private WebElement registerButton;

    @FindBy(id = "register-name")
    private WebElement fullNameField;

    @FindBy(id = "register-email")
    private WebElement emailField;

    @FindBy(id = "register-password")
    private WebElement passwordField;

    @FindBy(id = "initial-deposit")
    private WebElement initialDepositField;

    @FindBy(id = "do-register")
    private WebElement doRegisterButton;

    // Constructor
    public CustomerRegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page Methods
    public void navigateRegisterPage(){
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
        registerButton.click();
    }

    public void enterFullName(String fullName) {
        wait.until(ExpectedConditions.visibilityOf(fullNameField));
        fullNameField.clear();
        fullNameField.sendKeys(fullName);
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void enterInitialDeposit(String amount) {
        wait.until(ExpectedConditions.visibilityOf(initialDepositField));
        initialDepositField.clear();
        initialDepositField.sendKeys(amount);
    }

    public void clickRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(doRegisterButton));
        doRegisterButton.click();
    }

    // Complete register method
    public void register(String fullName, String email, String password, String amount) {
        navigateRegisterPage();
        enterFullName(fullName);
        enterEmail(email);
        enterPassword(password);
        enterInitialDeposit(amount);
        clickRegister();
    }

}
