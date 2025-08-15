package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static readproperties.ConfigProvider.*;

public class MyAccountPage {

    public final SelenideElement
            usernameInputField = $("#username"),
            passwordInputField = $("#password"),
            myAccountPageTitle = $(".post-title"),
            loginButton = $(".woocommerce-button"),
            rememberMeCheckbox = $("#rememberme"),
            forgotPasswordLink = $("a[href*='/lost-password/']"),
            passwordIsRequiredError = $(".woocommerce-error"),
            usernameIsRequiredError = $(".woocommerce-error"),
            invalidUserNameOrPasswordMessage = $(".woocommerce-error"),
            helloMessageText = $(".woocommerce-MyAccount-content p"),
            logoutButton = $(".woocommerce-MyAccount-navigation a[href*='logout']"),
            unknownEmailAddressErrorMessage = $(".woocommerce-error");

    public void openAuthPage() {
        open(MY_ACCOUNT_PAGE_URL);
    }

    public void myAccountPageTitleShouldBeVisible(String title) {
        myAccountPageTitle.shouldBe(visible).shouldHave(text(title));
    }

    public void forgotPasswordLinkClick() {
        forgotPasswordLink.shouldBe(visible).click();
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
        loginButton.shouldBe(visible).click();
    }

    public void clickLogoutButton() {

        logoutButton.shouldBe(visible).click();
    }

    public void login(String usernameOrEmail, String password) {
        openAuthPage();
        enterCredentials(usernameOrEmail, password);
        clickLoginButton();
    }

    public void helloMessageShouldBeVisible(String helloMessage) {
        helloMessageText.shouldBe(visible).shouldHave(text(helloMessage));
    }

    public void helloMessageShouldNotBeVisible() {
        helloMessageText.shouldNotBe(visible);
    }

    public void errorCredentialsMessageShouldBeVisible(SelenideElement element, String errorMessage) {
        element.shouldBe(visible).shouldHave(text(errorMessage));
    }

    public void errorUsernameOrEmailMessageShouldBeDisplayed(SelenideElement element, String errorMessage) {
        element.shouldBe(visible).shouldHave(text(errorMessage));
    }
}