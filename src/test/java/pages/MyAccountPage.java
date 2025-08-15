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

    public MyAccountPage myAccountPageTitleShouldBeVisible(String title) {
        myAccountPageTitle.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public void forgotPasswordLinkClick() {
        forgotPasswordLink.shouldBe(visible).click();
    }

    public MyAccountPage enterCredentials(String usernameOrEmail, String password) {
        usernameInputField.setValue(usernameOrEmail);
        passwordInputField.setValue(password);
        return this;
    }

    public MyAccountPage clickRememberMeCheckbox() {
        rememberMeCheckbox.click();
        return this;
    }

    public MyAccountPage clickLoginButton() {
        loginButton.shouldBe(visible).click();
        return this;
    }

    public MyAccountPage clickLogoutButton() {
        logoutButton.shouldBe(visible).click();
        return this;
    }

    public void login(String usernameOrEmail, String password) {
        openAuthPage();
        enterCredentials(usernameOrEmail, password);
        clickLoginButton();
    }

    public MyAccountPage helloMessageShouldBeVisible(String helloMessage) {
        helloMessageText.shouldBe(visible).shouldHave(text(helloMessage));
        return this;
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