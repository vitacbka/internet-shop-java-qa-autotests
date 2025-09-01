package tests;

import org.junit.jupiter.api.*;
import pages.PasswordRecoveryPage;
import static com.codeborne.selenide.Selenide.open;
import static readproperties.ConfigProvider.*;
import static testdata.MyAccountPageTestData.EXPECTED_PASSWORD_RECOVERY_TITLE;
import static testdata.PasswordRecoveryTestData.EXPECTED_INVALID_USERNAME_OR_EMAIL_ERROR_MESSAGE;
import static testdata.PasswordRecoveryTestData.EXPECTED_SUCCESSFUL_SEND_PASSWORD_MESSAGE;

public class PasswordRecoveryTests extends BaseTest{
    PasswordRecoveryPage passwordRecovery = new PasswordRecoveryPage();

    @BeforeEach
    void setup() {
        open(PASSWORD_RECOVERY_URL);
    }

    @Test
    @DisplayName("Page title should be displayed")
    void pageTitleShouldBeDisplayedTest() {
        passwordRecovery
                .isOnPasswordRecoveryPage(EXPECTED_PASSWORD_RECOVERY_TITLE);
    }

    @Test
    @DisplayName("Password should be send when user input valid username")
    void passwordShouldBeSendWhenUserInputValidUsernameTest() {
        passwordRecovery
                .enterUsernameOrEmail(passwordRecovery.emailOrUsernameInputField,
                        VALID_USER_LOGIN);
        passwordRecovery
                .successfulPasswordSendMessageShouldBeVisible(passwordRecovery.successPasswordRecoverySendToEmailMessage,
                        EXPECTED_SUCCESSFUL_SEND_PASSWORD_MESSAGE);
    }

    @Test
    @DisplayName("Password should be send when user input valid email")
    void passwordShouldBeSendWhenUserInputValidEmailTest() {
        passwordRecovery
                .enterUsernameOrEmail(passwordRecovery.emailOrUsernameInputField,
                        VALID_USER_EMAIL);
        passwordRecovery
                .successfulPasswordSendMessageShouldBeVisible(passwordRecovery.successPasswordRecoverySendToEmailMessage,
                        EXPECTED_SUCCESSFUL_SEND_PASSWORD_MESSAGE);
    }

    @Test
    @DisplayName("Error message should be displayed when user input invalid username")
    void passwordShouldBeSendWhenUserInputInvalidUsernameTest() {
        passwordRecovery
                .enterUsernameOrEmail(passwordRecovery.emailOrUsernameInputField,
                        INVALID_USER_LOGIN);
        passwordRecovery
                .errorPasswordSendMessageShouldBeVisible(passwordRecovery.errorPasswordRecoverToEmailMessage,
                        EXPECTED_INVALID_USERNAME_OR_EMAIL_ERROR_MESSAGE);
    }
}