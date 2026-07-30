package Lesson_10;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class MtsOnlinePaymentTest extends BaseTest {

    @Test
    public void testOnlinePaymentBlockTitle() {
        driver.get("https://www.mts.by");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement section = driver.findElement(By.xpath("//section[@class='pay']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", section);

        WebElement title = section.findElement(By.tagName("h2"));

        assertThat(title.getText().replace("\n", " ").replace("\"", "").trim()).
                isEqualTo("ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ");
    }

    @Test
    public void testOnlinePaymentBlockLogos() {
        driver.get("https://www.mts.by");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement section = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//section[contains(@class, 'pay')]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", section);

        String[] expectedLogos = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String logoName : expectedLogos) {
            WebElement logo = section.findElement(By.xpath(".//div[@class='pay__partners']//img[@alt='" + logoName + "']"));
            assertThat(logo.isDisplayed()).isTrue();
            assertThat(logo.getAttribute("src")).isNotEmpty();
        }
    }

    @Test
    public void testOnlinePaymentBlockLink() {
        driver.get("https://www.mts.by");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement section = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[contains(@class, 'pay')]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", section);

        WebElement link = section.findElement(By.xpath("//a[contains(text(),'Подробнее о сервисе')]"));
        assertThat(link.isDisplayed()).isTrue();
        assertThat(link.isEnabled()).isTrue();

        assertThat(link.getAttribute("href")).contains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }

    @Test
    public void testFillFormAndContinueButton() {
        driver.get("https://www.mts.by");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement cookieAccept = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));
            cookieAccept.click();
        } catch (Exception e) {
            System.out.println("Cookie-баннер не найден");
        }

        WebElement section = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//section[contains(@class, 'pay')]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", section);

        WebElement form = section.findElement(By.xpath(".//div[@class='pay__form']"));

        WebElement phoneField = form.findElement(By.xpath(".//input[@placeholder='Номер телефона']"));
        phoneField.clear();
        phoneField.sendKeys("297777777");

        WebElement amountField = form.findElement(By.xpath(".//input[@placeholder='Сумма']"));
        amountField.clear();
        amountField.sendKeys("10");

        WebElement emailField = form.findElement(By.xpath(".//input[@placeholder='E-mail для отправки чека']"));
        emailField.clear();
        emailField.sendKeys("test@test.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pay-connection\"]/button")));
        assertThat(continueButton.isDisplayed()).isTrue();
        assertThat(continueButton.isEnabled()).isTrue();
        assertThat(continueButton.getText()).isEqualTo("ПРОДОЛЖИТЬ");
        continueButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(driver.getCurrentUrl()).isNotEqualTo("https://www.mts.by");
    }


}
