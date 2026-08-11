package Lesson_11;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MtsOnlinePaymentTest extends BaseTest {

    private main.Lesson_10.MainPage mainPage;
    private PaymentPopupPage popupPage;

    @Step("Открыть главную страницу")
    public void openPage() {
        mainPage.open();
    }

    @Step("Закрыть баннер с куками")
    public void closeCookie() {
        mainPage.closeCookieBanner();
    }

    @Step("Проскроллить к блоку оплаты")
    public void scrollToPayment() {
        mainPage.scrollToPaymentSection();
    }

    @Step("Открыть главную страницу и перейти к блоку оплаты")
    public void openMainPageAndScrollToPayment() {
        mainPage = new main.Lesson_10.MainPage(driver);
        openPage();
        closeCookie();
        scrollToPayment();
    }

    @Step("Проверить заголовок блока: ожидается '{expectedTitle}'")
    public void verifyBlockTitle(String expectedTitle) {
        String actualTitle = mainPage.getTitle().replace("\n", " ").replace("\"", "").trim();
        assertThat(actualTitle)
                .as("Название блока должно соответствовать ожидаемому")
                .isEqualTo(expectedTitle);
    }

    @Step("Проверить наличие логотипов платежных систем")
    public void verifyLogos(String... expectedLogos) {
        List<WebElement> logos = mainPage.getLogos();
        for (String logoName : expectedLogos) {
            boolean found = logos.stream()
                    .anyMatch(logo -> logo.getAttribute("alt").equals(logoName));
            assertThat(found)
                    .as("Логотип '" + logoName + "' должен присутствовать")
                    .isTrue();
        }
    }

    @Step("Проверить, что ссылка 'Подробнее о сервисе' видима")
    public void verifyServiceLinkVisible() {
        assertThat(mainPage.isLinkVisible())
                .as("Ссылка 'Подробнее о сервисе' должна быть видима")
                .isTrue();
    }

    @Step("Проверить, что ссылка 'Подробнее о сервисе' ведет на правильный URL")
    public void verifyServiceLinkHref() {
        assertThat(mainPage.getLinkHref())
                .as("Ссылка должна вести на страницу с описанием сервиса")
                .contains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/");
    }


    @Step("Проверить плейсхолдеры для услуги '{service}'")
    public void verifyPlaceholdersForService(String service) {
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

    @Step("Заполнить номер телефона: '{phone}'")
    public void fillPhoneNumber(String phone) {
        mainPage.fillPhoneNumber(phone);
    }

    @Step("Заполнить сумму: '{amount}'")
    public void fillAmount(String amount) {
        mainPage.fillAmount(amount);
    }

    @Step("Заполнить email: '{email}'")
    public void fillEmail(String email) {
        mainPage.fillEmail(email);
    }

    @Step("Заполнить данные для оплаты: телефон '{phone}', сумма '{amount}', email '{email}'")
    public void fillPaymentData(String phone, String amount, String email) {
        fillPhoneNumber(phone);
        fillAmount(amount);
        fillEmail(email);
    }

    @Step("Нажать кнопку 'Продолжить'")
    public void clickContinueButton() {
        popupPage = mainPage.clickContinueButton();
    }

    @Step("Переключиться на попап-окно")
    public void switchToPopup() {
        popupPage.switchToPopup();
    }

    @Step("Нажать кнопку 'Продолжить' и переключиться на попап-окно")
    public void clickContinueAndSwitchToPopup() {
        clickContinueButton();
        switchToPopup();
    }

    @Step("Проверить, что попап-окно отображается")
    public void verifyPopupDisplayed() {
        assertThat(popupPage.isPaymentPopupDisplayed())
                .as("Окно оплаты должно отображаться")
                .isTrue();
    }

    @Step("Проверить номер телефона в попапе: ожидается '{expectedPhone}'")
    public void verifyPopupPhoneNumber() {
        assertThat(popupPage.getPhoneNumber())
                .as("Номер телефона должен отображаться корректно")
                .contains("297777777");
    }

    @Step("Проверить сумму на кнопке оплаты: ожидается '{expectedAmount}'")
    public void verifyPopupAmount() {
        assertThat(popupPage.getPaymentButtonAmount())
                .as("Сумма на кнопке должна соответствовать введенной")
                .isEqualTo("10");
    }

    @Step("Проверить наличие полей для ввода данных карты")
    public void verifyCardFields() {
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
    }

    @Step("Проверить плейсхолдеры в попап-окне")
    public void verifyPopupPlaceholders() {
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
    }

    @Step("Проверить наличие логотипов платежных систем в попапе")
    public void verifyPopupLogos() {
        List<WebElement> logos = popupPage.getPaymentSystemLogos();
        assertThat(logos)
                .as("Иконки платежных систем должны отображаться")
                .isNotEmpty();
    }

    @Step("Проверить кнопку 'Оплатить' в попапе")
    public void verifyPayButton() {
        assertThat(popupPage.isPayButtonDisplayed())
                .as("Кнопка 'Оплатить' должна отображаться")
                .isTrue();
        assertThat(popupPage.getPayButtonText())
                .as("Текст кнопки должен содержать 'Оплатить'")
                .contains("Оплатить");
    }

    @Step("Проверить попап-окно оплаты")
    public void verifyPaymentPopup() {
        verifyPopupDisplayed();
        verifyPopupPhoneNumber();
        verifyPopupAmount();
        verifyCardFields();
        verifyPopupPlaceholders();
        verifyPopupLogos();
        verifyPayButton();
    }


    @Test
    @DisplayName("Проверка названия блока")
    @Description("Проверяет, что отображается название \"ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ\"")
    @Severity(SeverityLevel.MINOR)
    public void testOnlinePaymentBlockTitle() {
        openMainPageAndScrollToPayment();
        verifyBlockTitle("ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ");
    }

    @Test
    @DisplayName("Проверка логотипов платежных систем")
    @Description("Проверяет, что отображаются логотипы Visa, Verified By Visa, MasterCard, " +
            "MasterCard Secure Code, Белкарт")
    @Severity(SeverityLevel.MINOR)
    public void testOnlinePaymentBlockLogos() {
        openMainPageAndScrollToPayment();
        verifyLogos("Visa", "Verified By Visa", "MasterCard",
                "MasterCard Secure Code", "Белкарт");
    }

    @Test
    @DisplayName("Проверка работы ссылки")
    @Description("Проверяет, что ссылка \"Подробнее о сервисе\" видна и ведет на нужную страницу")
    @Severity(SeverityLevel.MINOR)
    public void testOnlinePaymentBlockLink() {
        openMainPageAndScrollToPayment();
        verifyServiceLinkVisible();
        verifyServiceLinkHref();
    }

    @Test
    @DisplayName("Проверка плейсхолдеров")
    @Description("Проверяет плейсхолдеры для всех услуг")
    @Severity(SeverityLevel.NORMAL)
    public void testServiceOptionsPlaceholders() {
        openMainPageAndScrollToPayment();

        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String service : services) {
            verifyPlaceholdersForService(service);
        }
    }

    @Test
    @DisplayName("Проверка процесса перехода к оплате")
    @Description("Проверяет попап окно полностью")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Смоук")
    public void testFullPaymentFlow() {
        openMainPageAndScrollToPayment();
        fillPaymentData("297777777", "10", "test@test.com");
        clickContinueAndSwitchToPopup();
        verifyPaymentPopup();
    }
}
