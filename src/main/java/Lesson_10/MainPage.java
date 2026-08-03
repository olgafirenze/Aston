package Lesson_10;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class MainPage extends BasePage {

    @FindBy(xpath = "//section[@class='pay']")
    private WebElement section;

    @FindBy(xpath = "//section[@class='pay']//h2")
    private WebElement title;

    @FindBy(xpath = "//div[@class='pay__partners']//img")
    private List<WebElement> logos;

    @FindBy(xpath = "//a[contains(text(),'Подробнее о сервисе')]")
    private WebElement link;

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


    public MainPage(WebDriver driver) {
        super(driver);
    }


    private By getInputPlaceholderLocator(String placeholder) {
        return By.xpath(".//input[@placeholder='" + placeholder + "']");
    }

    public MainPage selectServiceByValue(String serviceName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display = 'block';", serviceSelect);
        Select select = new Select(serviceSelect);
        select.selectByValue(serviceName);
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


    public PaymentPopupPage clickContinueButton() {
        waitForElementToBeClickable(continueButton);
        scrollToElement(continueButton);
        clickWithJS(continueButton);
        return new PaymentPopupPage(driver);
    }

}



