package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.AuthPage;
import pages.PasswordRecoveryPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static readproperties.ConfigProvider.*;
import static testdata.AuthPageTestData.EXPECTED_PASSWORD_RECOVERY_PAGE_TITLE;
import static testdata.PasswordRecoveryTestData.EXPECTED_ERROR_INVALID_USERNAME_OR_EMAIL;
import static testdata.PasswordRecoveryTestData.EXPECTED_SUCCESSFUL_PASSWORD_RECOVERY_SEND_EMAIL_MESSAGE;

public class PasswordRecoveryTests {
    AuthPage authPage = new AuthPage();
    PasswordRecoveryPage passwordRecovery = new PasswordRecoveryPage();

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
    @DisplayName("Password recovery should be send to user valid email")
    void passwordRecoveryShouldBeSendToValidEmailTest() {
        authPage.passwordRecoveryLink.shouldBe(visible).click();
        passwordRecovery.passwordRecoveryPageTitle.shouldBe(visible, text(EXPECTED_PASSWORD_RECOVERY_PAGE_TITLE));
        passwordRecovery.emailOrUsernameInputField.shouldBe(visible).sendKeys(VALID_USER_EMAIL);
        passwordRecovery.resetPasswordButton.shouldBe(visible).click();
        passwordRecovery.successPasswordRecoverySendToEmailMessage.shouldBe(visible, text(EXPECTED_SUCCESSFUL_PASSWORD_RECOVERY_SEND_EMAIL_MESSAGE));
    }

    @Test
    @DisplayName("Password recovery should be send to user valid username")
    void passwordRecoveryShouldBeSendToValidUsernameTest() {
        authPage.passwordRecoveryLink.shouldBe(visible).click();
        passwordRecovery.passwordRecoveryPageTitle.shouldBe(visible, text(EXPECTED_PASSWORD_RECOVERY_PAGE_TITLE));
        passwordRecovery.emailOrUsernameInputField.shouldBe(visible).sendKeys("javaQaTester");
        passwordRecovery.resetPasswordButton.shouldBe(visible).click();
        passwordRecovery.successPasswordRecoverySendToEmailMessage.shouldBe(visible, text(EXPECTED_SUCCESSFUL_PASSWORD_RECOVERY_SEND_EMAIL_MESSAGE));
    }


    @Test
    @DisplayName("Error message sould be displayed and Password recovery should not be send to user invalid email")
    void errorMessageShouldBeDisplayedAndPasswordRecoveryShouldNotBeSendToInvalidEmailTest() {
        authPage.passwordRecoveryLink.shouldBe(visible).click();
        passwordRecovery.passwordRecoveryPageTitle.shouldBe(visible, text(EXPECTED_PASSWORD_RECOVERY_PAGE_TITLE));
        passwordRecovery.emailOrUsernameInputField.shouldBe(visible).sendKeys(INVALID_USER_EMAIL);
        passwordRecovery.resetPasswordButton.shouldBe(visible).click();
        passwordRecovery.errorPasswordRecoverToEmailMessage.shouldBe(visible, text(EXPECTED_ERROR_INVALID_USERNAME_OR_EMAIL));
    }
}
