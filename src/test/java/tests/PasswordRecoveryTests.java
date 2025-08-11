package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import pages.MyAccountPage;
import pages.PasswordRecoveryPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static readproperties.ConfigProvider.*;

public class PasswordRecoveryTests {
    MyAccountPage myAccount = new MyAccountPage();
    PasswordRecoveryPage passwordRecovery = new PasswordRecoveryPage();

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
    }
    @BeforeEach
    void setup() {
        myAccount.openAuthPage();
    }

    @AfterEach
    void teardown() {
        closeWebDriver();
    }

//    @Test
//    @DisplayName("Password recovery should be send to user valid email")
//    void passwordRecoveryShouldBeSendToValidEmailTest() {
//        myAccount.passwordRecoveryLink.shouldBe(visible).click();
//        passwordRecovery.passwordRecoveryPageTitle.shouldBe(visible, text(EXPECTED_PASSWORD_RECOVERY_PAGE_TITLE));
//        passwordRecovery.emailOrUsernameInputField.shouldBe(visible).sendKeys(VALID_USER_EMAIL);
//        passwordRecovery.resetPasswordButton.shouldBe(visible).click();
//        passwordRecovery.successPasswordRecoverySendToEmailMessage.shouldBe(visible, text(EXPECTED_SUCCESSFUL_PASSWORD_RECOVERY_SEND_EMAIL_MESSAGE));
//    }
//
//    @Test
//    @DisplayName("Password recovery should be send to user valid username")
//    void passwordRecoveryShouldBeSendToValidUsernameTest() {
//        myAccount.passwordRecoveryLink.shouldBe(visible).click();
//        passwordRecovery.passwordRecoveryPageTitle.shouldBe(visible, text(EXPECTED_PASSWORD_RECOVERY_PAGE_TITLE));
//        passwordRecovery.emailOrUsernameInputField.shouldBe(visible).sendKeys("javaQaTester");
//        passwordRecovery.resetPasswordButton.shouldBe(visible).click();
//        passwordRecovery.successPasswordRecoverySendToEmailMessage.shouldBe(visible, text(EXPECTED_SUCCESSFUL_PASSWORD_RECOVERY_SEND_EMAIL_MESSAGE));
//    }
//
//
//    @Test
//    @DisplayName("Error message should be displayed and Password recovery should not be send to user invalid email")
//    void errorMessageShouldBeDisplayedAndPasswordRecoveryShouldNotBeSendToInvalidEmailTest() {
//        myAccount.passwordRecoveryLink.shouldBe(visible).click();
//        passwordRecovery.passwordRecoveryPageTitle.shouldBe(visible, text(EXPECTED_PASSWORD_RECOVERY_PAGE_TITLE));
//        passwordRecovery.emailOrUsernameInputField.shouldBe(visible).sendKeys(INVALID_USER_EMAIL);
//        passwordRecovery.resetPasswordButton.shouldBe(visible).click();
//        passwordRecovery.errorPasswordRecoverToEmailMessage.shouldBe(visible, text(EXPECTED_ERROR_INVALID_USERNAME_OR_EMAIL));
//    }
}
