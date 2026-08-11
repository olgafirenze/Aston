package Lesson_10;

import org.junit.jupiter.api.Test;
import main.Lesson_10.MainPage;
import main.Lesson_10.PaymentPopupPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MtsOnlinePaymentTest extends BaseTest {

    @Test
    public void testOnlinePaymentBlockTitle() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .closeCookieBanner()
                .scrollToPaymentSection();

        assertThat(mainPage.getTitle().replace("\n", " ").replace("\"", "").trim())
                .as("Название блока должно соответствовать ожидаемому")
                .isEqualTo("ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ");
    }



    @Test
    public void testOnlinePaymentBlockLogos() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .closeCookieBanner()
                .scrollToPaymentSection();

        String[] expectedLogos = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        List<WebElement> logos = mainPage.getLogos();

        for (String logoName : expectedLogos) {
            boolean found = logos.stream()
                    .anyMatch(logo -> logo.getAttribute("alt").equals(logoName));
            assertThat(found)
                    .as("Логотип '" + logoName + "' должен присутствовать")
                    .isTrue();
        }
    }



    @Test
    public void testOnlinePaymentBlockLink() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .closeCookieBanner()
                .scrollToPaymentSection();

        assertThat(mainPage.isLinkVisible())
                .as("Ссылка 'Подробнее о сервисе' должна быть видима")
                .isTrue();

        assertThat(mainPage.getLinkHref())
                .as("Ссылка должна вести на страницу с описанием сервиса")
                .contains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }



    @Test
    public void testServiceOptionsPlaceholders() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .closeCookieBanner()
                .scrollToPaymentSection();

        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};

        for (String service : services) {
            mainPage.selectServiceByValue(service);

            String phonePlaceholder = mainPage.getFieldPlaceholder("Номер телефона");
            String internetPhonePlaceholder = mainPage.getFieldPlaceholder("Номер абонента");
            String account44Placeholder = mainPage.getFieldPlaceholder("Номер счета на 44");
            String account2073Placeholder = mainPage.getFieldPlaceholder("Номер счета на 2073");
            String amountPlaceholder = mainPage.getFieldPlaceholder("Сумма");
            String emailPlaceholder = mainPage.getFieldPlaceholder("E-mail для отправки чека");

            assertThat(amountPlaceholder)
                    .as("Плейсхолдер для суммы для " + service)
                    .isEqualTo("Сумма");

            assertThat(emailPlaceholder)
                    .as("Плейсхолдер для email для " + service)
                    .isEqualTo("E-mail для отправки чека");

            switch (service) {
                case "Услуги связи" -> assertThat(phonePlaceholder)
                        .as("Плейсхолдер для телефона для " + service)
                        .isEqualTo("Номер телефона");
                case "Домашний интернет" -> assertThat(internetPhonePlaceholder)
                        .as("Плейсхолдер для телефона для " + service)
                        .isEqualTo("Номер абонента");
                case "Рассрочка" -> assertThat(account44Placeholder)
                        .as("Плейсхолдер для счета для услуги" + service)
                        .isEqualTo("Номер счета на 44");
                case "Задолженность" -> assertThat(account2073Placeholder)
                        .as("Плейсхолдер для счета для услуги" + service)
                        .isEqualTo("Номер счета на 2073");
            }
        }
    }



    @Test
    public void testFullPaymentFlow() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open()
                .closeCookieBanner()
                .scrollToPaymentSection()
                .fillPhoneNumber("297777777")
                .fillAmount("10")
                .fillEmail("test@test.com");


        PaymentPopupPage popupPage = mainPage.clickContinueButton();
        popupPage.switchToPopup();

        assertThat(popupPage.isPaymentPopupDisplayed())
                .as("Окно оплаты должно отображаться")
                .isTrue();

        assertThat(popupPage.getPhoneNumber())
                .as("Номер телефона должен отображаться корректно")
                .contains("297777777");

        assertThat(popupPage.getPaymentButtonAmount())
                .as("Сумма на кнопке должна соответствовать введенной")
                .isEqualTo("10");

        assertThat(popupPage.isCardNumberFieldDisplayed())
                .as("Поле для номера карты должно отображаться")
                .isTrue();

        assertThat(popupPage.isExpiryDateFieldDisplayed())
                .as("Поле для срока действия карты должно отображаться")
                .isTrue();

        assertThat(popupPage.isCvcFieldDisplayed())
                .as("Поле для CVC-кода должно отображаться")
                .isTrue();

        assertThat(popupPage.isCardholderNameFieldDisplayed())
                .as("Поле для имени держателя карты должно отображаться")
                .isTrue();


        assertThat(popupPage.getCardNumberLabelText())
                .as("Плейсхолдер для номера карты")
                .isEqualTo("Номер карты");

        assertThat(popupPage.getExpiryDateLabelText())
                .as("Плейсхолдер для срока действия")
                .isEqualTo("Срок действия");

        assertThat(popupPage.getCvcLabelText())
                .as("Плейсхолдер для CVC")
                .isEqualTo("CVC");

        assertThat(popupPage.getCardHolderNameLabelText())
                .as("Плейсхолдер для имени владельца")
                .isEqualTo("Имя и фамилия на карте");


        List<WebElement> logos = popupPage.getPaymentSystemLogos();
        assertThat(logos)
                .as("Иконки платежных систем должны отображаться")
                .isNotEmpty();

        assertThat(popupPage.isPayButtonDisplayed())
                .as("Кнопка 'Оплатить' должна отображаться")
                .isTrue();

        assertThat(popupPage.getPayButtonText())
                .as("Текст кнопки должен содержать 'Оплатить'")
                .contains("Оплатить");
    }
}
