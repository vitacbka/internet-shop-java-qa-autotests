package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PasswordRecoveryPage {

    public final SelenideElement
            passwordRecoveryPageTitle = $(".post-title"),
            resetPasswordButton = $(".woocommerce-Button.button"),
            emailOrUsernameInputField = $("#user_login"),
            successPasswordRecoverySendToEmailMessage = $(".woocommerce-message"),
            errorPasswordRecoverToEmailMessage = $x("//li[contains(text(), 'Неверное')]");
}
