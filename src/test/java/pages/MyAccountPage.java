package pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
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
            logoutButton = $(".woocommerce-MyAccount-navigation a[href*='logout']");

    public MyAccountPage openAuthPage() {
        open(MY_ACCOUNT_PAGE_URL);
        return this;
    }

    public MyAccountPage isOnAuthPage(String title) {
        myAccountPageTitle.shouldBe(visible).shouldHave(text(title));
        webdriver().shouldHave(url(MY_ACCOUNT_PAGE_URL));
        return this;

    }

    public MyAccountPage verifyMyAccountPageTitleIsVisible(String title) {
        myAccountPageTitle.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public MyAccountPage enterCredentials(String usernameOrEmail, String password) {
        usernameInputField.shouldBe(visible).setValue(usernameOrEmail);
        passwordInputField.shouldBe(visible).setValue(password);
        return this;
    }

    public MyAccountPage clickRememberMeCheckbox() {
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }
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

    public MyAccountPage verifyHelloMessageIsVisible(String helloMessage) {
        helloMessageText.shouldBe(visible).shouldHave(text(helloMessage));
        return this;
    }

    public void helloMessageShouldNotBeVisible() {
        helloMessageText.shouldNotBe(visible, Duration.ofSeconds(10));
    }

    public void verifyErrorCredentialsMessageVisible(SelenideElement element, String errorMessage) {
        element.shouldBe(visible).shouldHave(text(errorMessage));
    }

    public void errorUsernameOrEmailMessageShouldBeDisplayed(SelenideElement element, String errorMessage) {
        element.shouldBe(visible).shouldHave(text(errorMessage));
    }

    public MyAccountPage cklickOnForgotPasswordLink(String expectedText) {
        forgotPasswordLink.shouldBe(visible).shouldHave(text(expectedText)).click();
        return this;
    }
}