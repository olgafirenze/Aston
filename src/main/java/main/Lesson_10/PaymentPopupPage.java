package main.Lesson_10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaymentPopupPage extends BasePage {


    @FindBy(xpath = "//button//span[contains(text(), 'Оплатить') and contains(text(), 'BYN')] ")
    private WebElement paymentButtonWithAmount;

    @FindBy(xpath = "//div[contains(@class, 'pay-description__text')]//span")
    private WebElement phoneNumberDisplay;

    @FindBy(css = "input[formcontrolname='creditCard']")
    private WebElement cardNumberField;

    @FindBy(css = "input[formcontrolname='expirationDate']")
    private WebElement expiryDateField;

    @FindBy(css = "input[formcontrolname='cvc']")
    private WebElement cvcField;

    @FindBy(css = "input[formcontrolname='holder']")
    private WebElement cardholderNameField;

    @FindBy(xpath = "//img[contains(@src, 'payment-icons/card-types/')]")
    private List<WebElement> paymentSystemLogos;

    @FindBy(xpath = "//button//span[contains(text(), 'Оплатить')]")
    private WebElement payButton;


    public PaymentPopupPage(WebDriver driver) {
        super(driver);
    }



    public PaymentPopupPage switchToPopup() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            try {
                if (!driver.findElements(By.cssSelector("input[formcontrolname='creditCard']")).isEmpty()) {
                    return this;
                }
            } catch (Exception ignored) {}

            WebElement iframe = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("//iframe[contains(@src, 'checkout.bepaid.by')]")
                    )
            );

            driver.switchTo().frame(iframe);

            wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("input[formcontrolname='creditCard']")
                    )
            );

        } catch (Exception e) {
            System.out.println("Критическая ошибка переключения: " + e.getMessage());
            throw new RuntimeException("Не удалось переключиться на iframe", e);
        }
        return this;
    }


    public String getPaymentButtonAmount() {
        String fullText = paymentButtonWithAmount.getText();
        String amount = fullText.replaceAll("[^0-9.,]", "").trim();
        if (amount.contains(".")) {
            amount = amount.replaceAll("0*$", "").replaceAll("\\.$", "");
        }
        return amount;
    }

    public String getPhoneNumber() {
        return phoneNumberDisplay.getText().trim();
    }

    public List<WebElement> getPaymentSystemLogos() {
        return paymentSystemLogos;
    }

    public boolean isCardNumberFieldDisplayed() {
        return cardNumberField.isDisplayed();
    }

    public boolean isExpiryDateFieldDisplayed() {
        return expiryDateField.isDisplayed();
    }

    public boolean isCvcFieldDisplayed() {
        return cvcField.isDisplayed();
    }

    public boolean isCardholderNameFieldDisplayed() {
        return cardholderNameField.isDisplayed();
    }

    public String getCardNumberFieldPlaceholder() {
        return cardNumberField.getAttribute("placeholder");
    }

    public String getExpiryDateFieldPlaceholder() {
        return expiryDateField.getAttribute("placeholder");
    }

    public String getCvcFieldPlaceholder() {
        return cvcField.getAttribute("placeholder");
    }

    public String getCardholderNameFieldPlaceholder() {
        return cardholderNameField.getAttribute("placeholder");
    }

    public boolean isPayButtonDisplayed() {
        return payButton.isDisplayed() && payButton.isEnabled();
    }

    public String getPayButtonText() {
        return payButton.getText();
    }

    public boolean isPaymentPopupDisplayed() {
        try {
            return !driver.findElements(By.cssSelector("input[formcontrolname='creditCard']")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCardNumberLabelText() {
        WebElement label = driver.findElement(
                By.xpath("//input[@id='cc-number']/following-sibling::label")
        );
        return label.getText().trim();
    }

    public String getExpiryDateLabelText() {
        WebElement label = driver.findElement(
                By.xpath("//input[@autocomplete='cc-exp']/following-sibling::label")
        );
        return label.getText().trim();
    }

    public String getCvcLabelText() {
        WebElement label = driver.findElement(
                By.xpath("//input[@name='verification_value']/following-sibling::label")
        );
        return label.getText().trim();
    }

    public String getCardHolderNameLabelText() {
        WebElement label = driver.findElement(
                By.xpath("//input[@autocomplete='cc-name']/following-sibling::label")
        );
        return label.getText().trim();
    }

}
