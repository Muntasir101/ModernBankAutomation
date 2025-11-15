package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CustomerWithdrawFundsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Web Elements
    @FindBy(css = "div[data-action='withdraw'] h3")
    private WebElement withdrawFundsCardButton;

    @FindBy(css = "#transaction-amount")
    private WebElement amountField;

    @FindBy(css = "#transaction-description")
    private WebElement descriptionField;

    @FindBy(css = "#confirm-transaction")
    private WebElement confirmButton;

    // Constructor
    public CustomerWithdrawFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickDepositFundsCard() {
        wait.until(ExpectedConditions.elementToBeClickable(withdrawFundsCardButton));
        withdrawFundsCardButton.click();
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOf(amountField));
        amountField.clear();
        amountField.sendKeys(amount);
    }

    public void enterDescription(String description) {
        wait.until(ExpectedConditions.visibilityOf(descriptionField));
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void clickConfirm() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }

    // Complete deposit fund method
    public void withdrawFunds(String amount, String description) {
        clickDepositFundsCard();
        enterAmount(amount);
        enterDescription(description);
        clickConfirm();
    }

}