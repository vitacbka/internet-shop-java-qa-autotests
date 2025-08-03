package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static readproperties.ConfigProvider.*;

public class AuthPage {

    public final SelenideElement
            usernameInputField = $("#username"),
            passwordInputField = $("#password"),
            myAccountPageTitle = $(".post-title"),
            loginPageTitle = $(".current"),
            passwordTitleText = $("label[for='password']"),
            loginButton = $(".woocommerce-button"),
            registrationBtn = $(".custom-register-button"),
            rememberMeCheckbox = $("#rememberme"),
            rememberMeCheckboxTitle = $("label.woocommerce-form-login__rememberme span"),
            forgotPasswordLink = $("a[href*='/lost-password/']"),
            passwordRecoveryPageTitle = $(".post-title"),
            passwordIsRequiredError = $(".woocommerce-error"),
            usernameIsRequiredError = $(".woocommerce-error"),
            invalidUserNameOrPasswordMessage = $(".woocommerce-error"),
            helloMessageText = $(".woocommerce-MyAccount-content p"),
            logoutButton = $(".woocommerce-MyAccount-navigation a[href*='logout']"),
            invalidCredentialsErrorMessage = $(".woocommerce-error"),
            passwordRecoveryLink = $("a[href*='lost-password']");

    public void openAuthPage() {
        open(MY_ACCOUNT_PAGE_URL);
    }

    public void userLoginWithValidCredentialsViaUsername() {
        usernameInputField.sendKeys(VALID_USER_LOGIN);
        passwordInputField.sendKeys(VALID_USER_PASSWORD);
        clickLoginButton();
    }

    public void tryLoginWithEmptyPassword() {
        usernameInputField.sendKeys(VALID_USER_LOGIN);
        clickLoginButton();
    }

    public void loginWithInvalidPassword() {
        usernameInputField.sendKeys(VALID_USER_LOGIN);
        passwordInputField.sendKeys(INVALID_USER_PASSWORD);
        clickLoginButton();
    }

    public void tryLoginWitEmptyUsernameOrEmail() {
        passwordInputField.sendKeys(VALID_USER_PASSWORD);
        clickLoginButton();
    }

    public void loginWithInvalidUsernameAndInvalidPassword() {
        usernameInputField.sendKeys(INVALID_USER_LOGIN);
        passwordInputField.sendKeys(INVALID_USER_PASSWORD);
        clickLoginButton();
    }

    public void loginWithValidEmailAndValidPassowrd() {
        usernameInputField.sendKeys(VALID_USER_EMAIL);
        passwordInputField.sendKeys(VALID_USER_PASSWORD);
        clickLoginButton();
    }

    public void loginWithValidCredentialsWithRememberMeCheckbox() {
        usernameInputField.sendKeys(VALID_USER_LOGIN);
        passwordInputField.sendKeys(VALID_USER_PASSWORD);
        rememberMeCheckbox.click();
        clickLoginButton();
    }

    public void clickLoginButton() {
        loginButton
                .shouldBe(visible)
                .click();
    }

}