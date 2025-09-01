package tests;

import org.junit.jupiter.api.*;
import pages.MainPage;
import pages.MyAccountPage;

import static readproperties.ConfigProvider.*;
import static testdata.HeaderTestData.EXPECTED_LOGIN_BUTTON_TEXT;
import static testdata.MainPageTestData.EXPECTED_MAIN_PAGE_TITLE_TEXT;
import static testdata.MyAccountPageTestData.*;
import static testdata.HeaderTestData.EXPECTED_HELLO_MESSAGE_AT_HEADER_TEXT;

import helpers.CookiesHelper;
import pages.HeaderPage;
import pages.PasswordRecoveryPage;

public class AuthTests extends BaseTest {
    MyAccountPage myAccount = new MyAccountPage();
    CookiesHelper cookiesHelper = new CookiesHelper();
    HeaderPage header = new HeaderPage();
    PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage();
    MainPage mainPage = new MainPage();

    @BeforeEach
    void setup() {
        myAccount
                .openAuthPage()
                .isOnAuthPage(EXPECTED_MY_ACCOUNT_TITLE);
    }

    @Test
    @DisplayName("Welcome text at header should be displayed after user is login")
    void welcomeTextAtHeaderShouldBeDisplayedAfterUserIsLogin() {
        myAccount
                .enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD)
                .clickLoginButton();
        header.welcomeTextAtHeaderShouldBeDisplayed(EXPECTED_HELLO_MESSAGE_AT_HEADER_TEXT);
    }

    @Test
    @DisplayName("User should be logged with valid username and walid password without remember me checkbox and should be displayed Hello message")
    void userShouldBeLoggedInTest() {
        myAccount
                .enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD)
                .clickLoginButton()
                .verifyMyAccountPageTitleIsVisible(EXPECTED_MY_ACCOUNT_TITLE)
                .verifyHelloMessageIsVisible(EXPECTED_HELLO_MESSAGE_TEXT);
    }

    @Test
    @DisplayName("Login button at header should be displayed after user logout")
    void loginButtonLinkShouldBeDisplayedAfterUserLogout() {
        myAccount
                .enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD)
                .clickLoginButton()
                .clickLogoutButton()
                .verifyMyAccountPageTitleIsVisible(EXPECTED_MY_ACCOUNT_TITLE);
        header.verifyLoginButtonVisibleInHeader(EXPECTED_LOGIN_BUTTON_TEXT);
    }

    @Test
    @DisplayName("Welcome message should not be displayed after user logout")
    void logoutTest() {
        myAccount
                .enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD)
                .clickLoginButton()
                .verifyHelloMessageIsVisible(EXPECTED_HELLO_MESSAGE_TEXT)
                .clickLogoutButton()
                .helloMessageShouldNotBeVisible();
    }

    @Test
    @DisplayName("Password is required error message should be displayed when user does not enter password")
    void passwordIsRequiredErrorMessageShouldBeDisplayedTest() {
        myAccount
                .enterCredentials(VALID_USER_LOGIN, "")
                .clickLoginButton()
                .verifyErrorCredentialsMessageVisible(myAccount.passwordIsRequiredError,
                        EXPECTED_PASSWORD_IS_REQUIRED_ERROR);
    }

    @Test
    @DisplayName("Username is required error message should be displayed when user does not enter username")
    void usernameIsRequiredErrorMessageShouldBeDisplayedTest() {
        myAccount
                .enterCredentials("", VALID_USER_PASSWORD)
                .clickLoginButton()
                .verifyErrorCredentialsMessageVisible(myAccount.usernameIsRequiredError,
                        EXPECTED_USERNAME_IS_REQUIRED_ERROR);
    }

    @Test
    @DisplayName("Error message should be displayed when user click login button with empty username and empty password")
    void errorMessageShouldBeDisplayedWhenClickLoginButtonWithoutCredentialsTest() {
        myAccount
                .enterCredentials("", "")
                .clickLoginButton()
                .verifyErrorCredentialsMessageVisible(myAccount.usernameIsRequiredError,
                        EXPECTED_USERNAME_IS_REQUIRED_ERROR);
    }

    @Test
    @DisplayName("Error message should be displayed when user enter invalid password")
    void errorMessageShouldBeDisplayedWhenUserEnterInvalidPasswordTest() {
        myAccount.enterCredentials(VALID_USER_LOGIN, INVALID_USER_PASSWORD)
                .clickLoginButton()
                .verifyErrorCredentialsMessageVisible(myAccount.invalidUserNameOrPasswordMessage,
                        EXPECTED_INVALID_PASSWORD_ERROR);
    }

    @Test
    @DisplayName("Error message should be displayed when user login with invalid username and invalid password")
    void errorMessageShouldBeDisplayedWhenUserLoginWithInvalidCredentialsTest() {
        myAccount.enterCredentials(INVALID_USER_LOGIN, INVALID_USER_PASSWORD)
                .clickLoginButton()
                .verifyErrorCredentialsMessageVisible(myAccount.invalidUserNameOrPasswordMessage,
                        EXPECTED_INVALID_CREDENTIALS_ERROR_MESSAGE);
    }

    @Test
    @DisplayName("User should be logged in with valid email and valid password")
    void userShouldBeLoggedInWithValidCredentialsViaEmailTest() {
        myAccount
                .enterCredentials(VALID_USER_EMAIL, VALID_USER_PASSWORD)
                .clickLoginButton()
                .verifyHelloMessageIsVisible(EXPECTED_HELLO_MESSAGE_TEXT);
    }

    @Test
    @DisplayName("User should be logged in with valid credentials with remember me checkbox")
    void userShouldBeLoggedInWithValidCredentialsWithRememberMeCheckboxTest() {
        myAccount
                .enterCredentials(VALID_USER_EMAIL, VALID_USER_PASSWORD)
                .clickRememberMeCheckbox()
                .clickLoginButton()
                .verifyHelloMessageIsVisible(EXPECTED_HELLO_MESSAGE_TEXT);
        cookiesHelper
                .saveCookies()
                .refresh()
                .clearCookies();

        cookiesHelper.closeWebDriver();
        myAccount.openAuthPage();
        cookiesHelper
                .pasteCookies()
                .refresh();

        myAccount.isOnAuthPage(EXPECTED_MY_ACCOUNT_TITLE);
        header.verifyWelcomeUserMessageAtHeader(EXPECTED_HELLO_MESSAGE_AT_HEADER_TEXT);
        myAccount.verifyHelloMessageIsVisible(EXPECTED_HELLO_MESSAGE_TEXT);
    }

    @Test
    @DisplayName("User should be logout after click logout button in header")
    void userShouldBeLogoutAfterClickLogoutButtonInHeader() {
        myAccount
                .enterCredentials(VALID_USER_EMAIL, VALID_USER_PASSWORD)
                .clickLoginButton()
                .verifyHelloMessageIsVisible(EXPECTED_HELLO_MESSAGE_TEXT);
        header
                .clickLogoutButton()
                .verifyLoginButtonVisibleInHeader(EXPECTED_LOGIN_BUTTON_TEXT)
                .welcomeTextAtHeaderShouldNotBeVisible();
    }

    @Test
    @DisplayName("Click on lost password link should open lost password page")
    void clickOnLostPasswordLinkShouldOpenLostPasswordPageTest() {
        myAccount.cklickOnForgotPasswordLink(EXPECTED_FORGOT_PASSWORD_LINK_TEXT);
        passwordRecoveryPage.isOnPasswordRecoveryPage(EXPECTED_PASSWORD_RECOVERY_TITLE);
    }
}