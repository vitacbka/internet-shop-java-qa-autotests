package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import pages.MyAccountPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static readproperties.ConfigProvider.*;
import static testdata.MyAccountPageTestData.*;
import static testdata.HeaderTestData.EXPECTED_HELLO_MESSAGE_AT_HEADER_TEXT;

import helpers.Cookies;
import pages.HeaderPage;

public class AuthTest extends BaseTest{
    MyAccountPage myAccount = new MyAccountPage();
    Cookies cookies = new Cookies();
    HeaderPage header = new HeaderPage();

    @BeforeEach
    void setup() {
        myAccount.openAuthPage();
    }

    @Test
    @DisplayName("Login page title should be displayed")
    void loginPageTitleShouldBeDisplayedTest() {
        myAccount
                .getMyAccountPageTitle()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_MY_ACCOUNT_TITLE));
    }

    @Test
    @DisplayName("Welcome text at header should be displayed after user is login")
    void welcomeTextAtHeaderShouldBeDisplayedAfterUserIsLogin() {
        myAccount.enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        header.getWelcomeTextAtHeader()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_HELLO_MESSAGE_AT_HEADER_TEXT));
    }

    @Test
    @DisplayName("User should be logged with valid username and walid password without remember me checkbox and should be displayed Hello message title")
    void userShouldBeLoggedInTest() {
        myAccount.enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.clickLogoutButton();
        myAccount.getMyAccountPageTitle()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_MY_ACCOUNT_TITLE));
        myAccount.getHelloMessage()
                        .shouldNotBe(visible);
    }

    @Test
    @DisplayName("Login button at header should be displayed after user logout")
    void loginButtonLinkShouldBeDisplayedAfterUserLogout() {
        myAccount.enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.clickLogoutButton();
        myAccount.getMyAccountPageTitle()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_MY_ACCOUNT_TITLE));
        header.getLoginLinkButton()
                .shouldBe(visible)
                .shouldHave(text("Войти"));
    }

    @Test
    @DisplayName("Welcome message should not be displayed after user logout")
    void logoutTest() {
        myAccount.enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.getHelloMessage()
                .shouldBe(visible);
        myAccount.clickLogoutButton();
        myAccount.getHelloMessage()
                .shouldNotBe(visible);
    }

    @Test
    @DisplayName("Password is required error message should be displayed when user does not enter password")
    void passwordIsRequiredErrorMessageShouldBeDisplayedTest() {
        myAccount.enterCredentials(VALID_USER_LOGIN, "");
        myAccount.clickLoginButton();
        myAccount.getPasswordIsRequiredError()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_PASSWORD_IS_REQUIRED_ERROR));
    }

    @Test
    @DisplayName("Username is required error message should be displayed when user does not enter username")
    void usernameIsRequiredErrorMessageShouldBeDisplayedTest() {
        myAccount.enterCredentials("", VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.geUsernameIsRequiredError()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_USERNAME_IS_REQUIRED_ERROR));
    }

    @Test
    @DisplayName("Error message should be displayed when user click login button with empty username and empty password")
    void errorMessageShouldBeDisplayedWhenClickLoginButtonWithoutCredentialsTest() {
        myAccount.clickLoginButton();
        myAccount.geUsernameIsRequiredError()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_USERNAME_IS_REQUIRED_ERROR));
    }

    @Test
    @DisplayName("Error message should be displayed when user enter invalid password")
    void errorMessageShouldBeDisplayedWhenUserEnterInvalidPasswordTest() {
        myAccount.enterCredentials(VALID_USER_LOGIN, INVALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.getPasswordIsRequiredError()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_INVALID_PASSWORD_ERROR));
    }

    @Test
    @DisplayName("Error message should be displayed when user login with invalid username and invalid password")
    void errorMessageShouldBeDisplayedWhenUserLoginWithInvalidCredentialsTest() {
        myAccount.enterCredentials(INVALID_USER_LOGIN, INVALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.getInvalidCredentialsErrorMessage()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_INVALID_CREDENTIALS_ERROR_MESSAGE));
    }

    @Test
    @DisplayName("User should be logged in with valid email and valid password")
    void userShouldBeLoggedInWithValidCredentialsViaEmailTest() {
        myAccount.enterCredentials(VALID_USER_EMAIL, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.getHelloMessage()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_HELLO_MESSAGE_TEXT.trim()));
    }

    @Test
    @DisplayName("Error message should be displayed after user has input invalid email and valid password and click login button")
    void errorMessageShouldBeDisplayedAfterUserHasInputInvalidEmailTest() {
        myAccount.enterCredentials(INVALID_USER_EMAIL, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.getUnknownEmailAddressErrorMessage()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_UNKNOWN_EMAIL_ADDRESS_ERROR));

    }

    @Test
    @DisplayName("User should be logged in with valid credentials with remember me checkbox")
    void userShouldBeLoggedInWithValidCredentialsWithRememberMeCheckboxTest() {
        myAccount.enterCredentialsWithRememberMeCheckbox(VALID_USER_EMAIL, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        cookies.saveCookies();
        closeWebDriver();
        myAccount.openAuthPage();
        cookies.pasteCookies();
        Selenide.refresh();
        myAccount.getHelloMessage()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_HELLO_MESSAGE_TEXT.trim()));
    }

    @Test
    @DisplayName("User should be logout after click logout button in header")
    void userShouldBeLogoutAfterClickLogoutButtonInHeader() {
        myAccount.enterCredentialsWithRememberMeCheckbox(VALID_USER_EMAIL, VALID_USER_PASSWORD);
        myAccount.clickLoginButton();
        myAccount.getHelloMessage()
                .shouldBe(visible)
                .shouldHave(text(EXPECTED_HELLO_MESSAGE_TEXT.trim()));
        header.clickLogoutButton();
        header.getLoginButtonText()
                .shouldBe(visible)
                .shouldHave(text("Войти"));
    }
}