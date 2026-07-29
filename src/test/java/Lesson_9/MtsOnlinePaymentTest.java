package Lesson_9;

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
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//section[contains(@class, 'pay')]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", section);

        String[] expectedLogos = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String logoName : expectedLogos) {
            WebElement logo = section.findElement(
                    By.xpath(".//div[@class='pay__partners']//img[@alt='" + logoName + "']")
            );
            assertThat(logo.isDisplayed()).isTrue();
            assertThat(logo.getAttribute("src")).isNotEmpty();
        }
    }

    @Test
    public void testOnlinePaymentBlockLink() {
        driver.get("https://www.mts.by");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement section = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//section[contains(@class, 'pay')]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", section);

        WebElement link = section.findElement(By.xpath("//a[contains(text(),'Подробнее о сервисе')]"));
        assertThat(link.isDisplayed());
        assertThat(link.isEnabled());

        assertThat(link.getAttribute("href")).contains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");

    }


}
