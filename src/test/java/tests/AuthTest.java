package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import pages.AuthPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static testdata.AuthPageTestData.*;

import helpers.Cookies;

public class AuthTest {
    AuthPage authPage = new AuthPage();
    Cookies cookies = new Cookies();

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }
    @BeforeEach
    void setup() {
        authPage.openAuthPage();
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }

    @Test
    @DisplayName("Login page title should be displayed")
    void loginPageTitleShouldBeDisplayed() {
        authPage
                .loginPageTitle
                .shouldBe(visible);
    }

    @Test
    @DisplayName("User should be logged in with valid credentials without remember me checkbox and should be displayed Hello message title")
    void userShouldBeLoggedInTest() {
        authPage.userLoginWithValidCredentialsViaUsername();

        authPage
                .helloMessageText
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_HELLO_MESSAGE_TEXT));
    }

    @Test
    @DisplayName("User should be logged out after click link logout link at my account page")
    void logoutTest() {
        authPage.userLoginWithValidCredentialsViaUsername();
        authPage
                .logoutButton
                .shouldBe(visible)
                .click();

        authPage
                .usernameInputField
                .shouldBe(visible);
        authPage
                .passwordInputField
                .shouldBe(visible);

        authPage
                .loginButton
                .shouldBe(visible)
                .shouldHave(Condition.text("Войти"));

        authPage
                .helloMessageText
                .shouldNot(be(visible));
    }

    @Test
    @DisplayName("Password is required error message should be displayed when user does not enter password")
    void passwordIsRequiredErrorMessageShouldBeDisplayedTest() {
        authPage.tryLoginWithEmptyPassword();
        authPage
                .passwordIsRequiredError
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_PASSWORD_IS_REQUIRED_ERROR));
    }

    @Test
    @DisplayName("Username is required error message should be displayed when user does not enter username")
    void usernameIsRequiredErrorMessageShouldBeDisplayedTest() {
        authPage.tryLoginWitEmptyUsernameOrEmail();
        authPage
                .usernameIsRequiredError
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_USERNAME_IS_REQUIRED_ERROR));
    }

    @Test
    @DisplayName("Error message should be displayed when user click login button with empty username and empty password")
    void errorMessageShouldBeDisplayedWhenClickLoginButtonWithoputCredentialsTest() {
        authPage.loginButton
                .shouldBe(visible)
                .click();
        authPage.
                usernameIsRequiredError
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_USERNAME_IS_REQUIRED_ERROR));
    }

    @Test
    @DisplayName("Error message should be displayed when user enter invalid password")
    void errorMessageShouldBeDisplayedWhenUserEnterInvalidPasswordTest() {
        authPage.loginWithInvalidPassword();
        authPage
                .passwordIsRequiredError
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_INVALID_PASSWORD_ERROR));
    }

    @Test
    @DisplayName("Error message should be displayed when user login with invalid credentials")
    void errorMessageShouldBeDisplayedWhenUserLoginWithInvalidCredentialsTest() {
        authPage.loginWithInvalidUsernameAndInvalidPassword();
        authPage.
                invalidCredentialsErrorMessage
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_INVALID_CREDENTIALS_ERROR_MESSAGE));
    }

    @Test
    @DisplayName("User should be logged in with valid email and valid password")
    void userShouldBeLoggedInWithValidCredentialsViaEmailTest() {
        authPage.loginWithValidEmailAndValidPassowrd();
        authPage
                .helloMessageText
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_HELLO_MESSAGE_TEXT.trim()));
    }

    @Test
    @DisplayName("User should be logged in with valid credentials with remember me checkbox")
    void userShouldBeLoggedInWithValidCredentialsWithRememberMeCheckboxTest() {
        authPage.loginWithValidCredentialsWithRememberMeCheckbox();

        cookies.saveCookies();

        closeWebDriver();

        authPage.openAuthPage();
        cookies.deleteCookies();
        cookies.pasteCookies();

        Selenide.refresh();

        authPage.helloMessageText
                .shouldBe(visible)
                .shouldHave(Condition.text(EXPECTED_HELLO_MESSAGE_TEXT));
    }
}