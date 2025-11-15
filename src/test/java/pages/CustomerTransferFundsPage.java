package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CustomerTransferFundsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Web Elements
    @FindBy(css = "div[data-action='transfer'] h3")
    private WebElement transferFundsCardButton;

    @FindBy(css = "#transaction-amount")
    private WebElement amountField;

    @FindBy(css = "#transfer-recipient")
    private WebElement recipientEmail;

    @FindBy(css = "#transaction-description")
    private WebElement descriptionField;

    @FindBy(css = "#confirm-transaction")
    private WebElement confirmButton;

    // Constructor
    public CustomerTransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickTransferFundsCard() {
        wait.until(ExpectedConditions.elementToBeClickable(transferFundsCardButton));
        transferFundsCardButton.click();
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOf(amountField));
        amountField.clear();
        amountField.sendKeys(amount);
    }

    public void enterEmail(String Email) {
        wait.until(ExpectedConditions.visibilityOf(recipientEmail));
        recipientEmail.clear();
        recipientEmail.sendKeys(Email);
    }

    public void enterDescription(String description) {
        wait.until(ExpectedConditions.visibilityOf(descriptionField));
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void clickConfirmButton() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }

    // Complete deposit fund method
    public void transferFunds(String amount,String email, String description) {
        clickTransferFundsCard();
        enterAmount(amount);
        enterEmail(email);
        enterDescription(description);
        clickConfirmButton();

    }

}
