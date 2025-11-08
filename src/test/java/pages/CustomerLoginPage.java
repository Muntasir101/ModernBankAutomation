package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Web Elements
    @FindBy(id = "login-btn")
    private WebElement signInButton;

    @FindBy(css = "#login-email")
    private WebElement emailField;

    @FindBy(css = "#login-password")
    private WebElement passwordField;

    @FindBy(css = "#do-login")
    private WebElement loginButton;


    // Constructor
    public CustomerLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Page Methods
    public void navigateLoginPage(){
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();
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

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }

    public boolean isLoginPageDisplayed() {
        return emailField.isDisplayed() && passwordField.isDisplayed();
    }

    // Complete login method
    public void login(String email, String password) {
        navigateLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
}