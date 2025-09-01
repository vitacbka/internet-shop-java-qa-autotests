package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static readproperties.ConfigProvider.PASSWORD_RECOVERY_URL;

public class PasswordRecoveryPage {

    public final SelenideElement
            passwordRecoveryPageTitle = $(".post-title"),
            resetPasswordButton = $(".woocommerce-Button.button"),
            emailOrUsernameInputField = $("#user_login"),
            successPasswordRecoverySendToEmailMessage = $(".woocommerce-message"),
            errorPasswordRecoverToEmailMessage = $x("//li[contains(text(), 'Неверное')]");

    public void clickResetPassword() {
        resetPasswordButton.click();
    }

    public void isOnPasswordRecoveryPage(String title) {
        webdriver().shouldHave(url(PASSWORD_RECOVERY_URL));
        passwordRecoveryPageTitle.shouldBe(visible).shouldHave(text(title));
    }

    public void successfulPasswordSendMessageShouldBeVisible(SelenideElement element, String message) {
        element.shouldBe(visible).shouldHave(text(message));
    }
    public void errorPasswordSendMessageShouldBeVisible(SelenideElement element, String message) {
        element.shouldBe(visible).shouldHave(text(message));
    }

    public void enterUsernameOrEmail (SelenideElement element, String username) {
        element.setValue(username);
        clickResetPassword();
    }
 }