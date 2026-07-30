package main.Lesson_10;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import main.Lesson_10.BasePage;

public class MainPage extends BasePage {

    @FindBy(xpath = "//section[@class='pay']")
    private WebElement section;

    @FindBy(xpath = "//section[@class='pay']//h2")
    private WebElement title;

    @FindBy(xpath = "//div[@class='pay__partners']")
    private WebElement partnersContainer;

    @FindBy(xpath = "//div[@class='pay__partners']//img")
    private List<WebElement> logos;

    @FindBy(xpath = "//a[contains(text(),'Подробнее о сервисе')]")
    private WebElement link;

    @FindBy(xpath = ".//div[@class='pay__form']")
    private WebElement paymentForm;

    @FindBy(xpath = ".//input[@placeholder='Номер телефона']")
    private WebElement phoneField;

    @FindBy(xpath = ".//input[@placeholder='Сумма']")
    private WebElement amountField;

    @FindBy(xpath = ".//input[@placeholder='E-mail для отправки чека']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='pay-connection']/button")
    private WebElement continueButton;

    @FindBy(xpath = ".//select[contains(@id, 'pay')]")
    private WebElement serviceSelect;
    Select select = new Select(serviceSelect);

    private String serviceName;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getAllServiceOptions() {
        List<WebElement> options = select.getOptions();
        List<String> optionsText = new ArrayList<>();
        for (WebElement option : options) {
            optionsText.add(option.getText().trim());
        }
        return optionsText;
    }


    private By getInputPlaceholderLocator(String placeholder) {
        return By.xpath(".//input[@placeholder='" + placeholder + "']");
    }

    public MainPage selectServiceBySelect(String serviceName) {
        select.selectByVisibleText(serviceName);
        return this;
    }

    public MainPage selectServiceByClick(String serviceName) {
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equals(serviceName)) {
                option.click();
                break;
            }
        }
        return this;
    }


    public MainPage open() {
        driver.get("https://www.mts.by");
        return this;
    }

    public MainPage closeCookieBanner() {
        try {
            WebElement cookieAccept = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cookie-agree"))
            );
            cookieAccept.click();
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Cookie-баннер не найден");
        }
        return this;
    }

    public MainPage scrollToPaymentSection() {
        wait.until(ExpectedConditions.visibilityOf(section));
        scrollToElement(section);
        return this;
    }

    public String getTitle() {
        return title.getText().replace("\n", " ").replace("\"", "").trim();
    }

    public List<WebElement> getLogos() {
        return logos;
    }

    public String getLinkHref() {
        return link.getAttribute("href");
    }

    public boolean isLinkVisible() {
        return link.isDisplayed() && link.isEnabled();
    }

    public String getServiceOption(String serviceName) {
        List<WebElement> options = select.getOptions();
        for (WebElement option : options) {
            if (option.getText().trim().equals(serviceName)) {
                return option.getText().trim();
            }
        }
        throw new NoSuchElementException("Опция '" + serviceName + "' не найдена в select");
    }

    public String getFieldPlaceholder(String placeholder) {
        WebElement field = driver.findElement(getInputPlaceholderLocator(placeholder));
        return field.getAttribute("placeholder");
    }



    public MainPage fillPhoneNumber(String phone) {
        wait.until(ExpectedConditions.visibilityOf(phoneField));
        phoneField.clear();
        phoneField.sendKeys(phone);
        return this;
    }

    public MainPage fillAmount(String amount) {
        wait.until(ExpectedConditions.visibilityOf(amountField));
        amountField.clear();
        amountField.sendKeys(amount);
        return this;
    }

    public MainPage fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.clear();
        emailField.sendKeys(email);
        return this;
    }

    public String getPhoneFieldValue() {
        return phoneField.getAttribute("value");
    }

    public String getAmountFieldValue() {
        return amountField.getAttribute("value");
    }

    public String getEmailFieldValue() {
        return emailField.getAttribute("value");
    }

    public boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    public String getContinueButtonText() {
        return continueButton.getText();
    }

    public PaymentPopupPage clickContinueButton() {
        waitForElementToBeClickable(continueButton);
        scrollToElement(continueButton);
        clickWithJS(continueButton);
        return new PaymentPopupPage(driver);
    }

}



