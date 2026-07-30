package main.Lesson_10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

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

    private String serviceName;

    public List<String> getAllServiceOptions() {
        Select select = new Select(serviceSelect);
        List<WebElement> options = select.getOptions();
        List<String> optionsText = new ArrayList<>();
        for (WebElement option : options) {
            optionsText.add(option.getText().trim());
        }
        return optionsText;
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }

    private By getInputPlaceholderLocator(String placeholder) {
        return By.xpath(".//input[@placeholder='" + placeholder + "']");

    public MainPage selectService(String serviceName) {
        Select select = new Select(serviceSelect);
        select.selectByVisibleText(serviceName);
        return this;




}
