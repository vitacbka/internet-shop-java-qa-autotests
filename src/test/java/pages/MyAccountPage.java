package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static readproperties.ConfigProvider.*;

public class MyAccountPage {

    public final SelenideElement
            usernameInputField = $("#username"),
            passwordInputField = $("#password"),
            MyAccountPageTitle = $(".post-title"),
            loginButton = $(".woocommerce-button"),
            registrationButton = $(".custom-register-button"),
            rememberMeCheckbox = $("#rememberme"),
            forgotPasswordLink = $("a[href*='/lost-password/']"),
            passwordRecoveryPageTitle = $(".post-title"),
            passwordIsRequiredError = $(".woocommerce-error"),
            usernameIsRequiredError = $(".woocommerce-error"),
            invalidUserNameOrPasswordMessage = $(".woocommerce-error"),
            helloMessageText = $(".woocommerce-MyAccount-content p"),
            logoutButton = $(".woocommerce-MyAccount-navigation a[href*='logout']"),
            invalidCredentialsErrorMessage = $(".woocommerce-error"),
            unknownEmailAddressErrorMessage = $(".woocommerce-error");

    public void openAuthPage() {
        open(MY_ACCOUNT_PAGE_URL);
    }

    public void enterCredentials(String usernameOrEmail, String password) {
        usernameInputField.setValue(usernameOrEmail);
        passwordInputField.setValue(password);
    }

    public void enterCredentialsWithRememberMeCheckbox(String usernameOrEmail, String password) {
        enterCredentials(usernameOrEmail, password);
        rememberMeCheckbox.click();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void login(String usernameOrEmail, String password) {
        openAuthPage();
        enterCredentials(usernameOrEmail, password);
        clickLoginButton();
    }

    public SelenideElement getHelloMessage() {
        return helloMessageText;
    }

    public SelenideElement getInvalidCredentialsErrorMessage() {
        return invalidCredentialsErrorMessage;
    }

    public SelenideElement getInvalidUserNameOrPasswordMessage() {
        return invalidUserNameOrPasswordMessage;
    }

    public SelenideElement getPasswordRecoveryPageTitle() {
        return passwordRecoveryPageTitle;
    }

    public SelenideElement getMyAccountPageTitle() {
        return MyAccountPageTitle;
    }

    public SelenideElement getPasswordIsRequiredError() {
        return  passwordIsRequiredError;
    }

    public SelenideElement geUsernameIsRequiredError() {
        return usernameIsRequiredError;
    }

    public SelenideElement getUnknownEmailAddressErrorMessage() {
        return unknownEmailAddressErrorMessage;
    }
}