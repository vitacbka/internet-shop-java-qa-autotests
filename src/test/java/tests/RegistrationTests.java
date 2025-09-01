package tests;

import helpers.GenerateRandomRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.RegistrationPage;

import java.util.stream.Stream;

import static readproperties.ConfigProvider.*;
import static testdata.RegistrationPageTestData.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationTests extends BaseTest {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void setUp() {
        registrationPage
                .openRegistrationPage()
                .shouldBeOnRegistrationPage();
    }

    @Test
    @DisplayName("New user should be registered")
    void newUserShouldBeRegistered1() {
        GenerateRandomRegistrationData.TestUser validUser = GenerateRandomRegistrationData.generateValidUser();
        registrationPage
                .fillRegistrationFormWithGeneratedData(validUser)
                .clickSubmitButton()
                .successfulRegistrationMessageShouldBeVisible(EXPECTED_SUCCESSFUL_REGISTRATION_MESSAGE);
    }

    @Test
    @DisplayName("Error message without \"@\" symbol at email should be visible")
    void errorMessageWithoutAtSymbolShouldBeVisible() {
        GenerateRandomRegistrationData.TestUser userWithoutAtSymbol = GenerateRandomRegistrationData.generateUserWithoutAtSymbol();
        String expectedMessage = String.format(EXPECTED_WITHOUT_AT_SYMBOL_EMAIL_SYMBOL_AT_ERROR, userWithoutAtSymbol.email);
        registrationPage
                .fillRegistrationFormWithGeneratedData(userWithoutAtSymbol)
                .clickSubmitButton()
                .shouldHaveEmailHtml5ValidationMessage(expectedMessage);
    }

    @Test
    @DisplayName("Error existing email message should be visible")
    void errorExistingEmailShouldBeVisible() {
        registrationPage
                .fillRegistrationForm(VALID_USER_LOGIN, VALID_USER_PASSWORD, VALID_USER_EMAIL)
                .clickSubmitButton()
                .invalidDataErrorMessageShouldBeVisible(EXPECTED_ALREADY_EXISTING_USER_DATA_ERROR_TEXT);
    }

    @Test
    @DisplayName("Error empty email field message should be visible")
    void errorEmptyFieldMessageShouldBeVisible() {
        GenerateRandomRegistrationData.TestUser emptyEmailField = GenerateRandomRegistrationData.generateUserWithEmptyEmail();
        registrationPage
                .fillRegistrationFormWithGeneratedData(emptyEmailField)
                .clickSubmitButton()
                .invalidDataErrorMessageShouldBeVisible(EXPECTED_EMPTY_EMAIL_ERROR_TEXT);
    }

    @Test
    @DisplayName("Error empty username field message should be visible")
    void errorEmptyUsernameFieldMessageShouldBeVisible() {
        GenerateRandomRegistrationData.TestUser emptyUsernameField = GenerateRandomRegistrationData.generateUserWithEmptyUsername();
        registrationPage
                .fillRegistrationFormWithGeneratedData(emptyUsernameField)
                .clickSubmitButton()
                .invalidDataErrorMessageShouldBeVisible(EXPECTED_EMPTY_USERNAME_FIELD_ERROR_TEXT);
    }

    @Test
    @DisplayName("Error email without domain part message should be visible")
    void errorEmailWithoutDomainPartMessageShouldBeVisible() {
        GenerateRandomRegistrationData.TestUser emailWithoutDomainName = GenerateRandomRegistrationData.generateUserWithoutDomain();
        String expectedMessage = String.format(EXPECTED_EMAIL_WITHOUT_DOMAIN_ERROR_TEXT, emailWithoutDomainName.email);

        registrationPage
                .fillRegistrationFormWithGeneratedData(emailWithoutDomainName)
                .clickSubmitButton()
                .shouldHaveEmailHtml5ValidationMessage(expectedMessage);
    }

//    static Stream<Object> invalidRegistrationData() {
//        GenerateRandomRegistrationData.TestUser invalidUserWithoutAtSymbol = GenerateRandomRegistrationData.generateUserWithoutAtSymbol();
//        GenerateRandomRegistrationData.TestUser userWithoutDomain = GenerateRandomRegistrationData.generateUserWithoutDomain();
//        GenerateRandomRegistrationData.TestUser generateUserWithEmptyEmail =  GenerateRandomRegistrationData.generateUserWithEmptyEmail();
//        GenerateRandomRegistrationData.TestUser generateUserWithEmptyUsername = GenerateRandomRegistrationData.generateUserWithEmptyUsername();
//        GenerateRandomRegistrationData.TestUser generateUserWithEmptyPassword = GenerateRandomRegistrationData.generateUserWithEmptyPassword();
//
//        return Stream.of(
//                // {user, expectedMessage, isHTML5Validation
//                new Object[]{invalidUserWithoutAtSymbol, EXPECTED_WITHOUT_AT_SYMBOL_EMAIL_SYMBOL_AT_ERROR, true},
//                new Object[]{userWithoutDomain, EXPECTED_EMAIL_WITHOUT_DOMAIN_ERROR_TEXT, true},
//                new Object[]{generateUserWithEmptyEmail, EXPECTED_EMPTY_EMAIL_ERROR_TEXT, false},
//                new Object[]{generateUserWithEmptyUsername, EXPECTED_EMPTY_USERNAME_FIELD_ERROR_TEXT, false},
//                new Object[]{generateUserWithEmptyPassword, EXPECTED_EMPTY_PASSWORD_FIELD_ERROR_TEXT, false}
//        );
//    }
//    @ParameterizedTest(name = "Registration with invalid data: username={0}, email={1}, password={2}")
//    @MethodSource("invalidRegistrationData")
//    @DisplayName("Error message for invalid registration data should be visible")
//    void errorMessageForInvalidRegistrationDataShouldBeVisible(String username,
//                                                               String email,
//                                                               String password,
//                                                               String expectedMessage,
//                                                               boolean isHtml5Validation) {
//    registrationPage
//            .fillRegistrationForm(username, email, password)
//            .clickSubmitButton();
//    if (isHtml5Validation) {
//        registrationPage.shouldHaveEmailHtml5ValidationMessage(String.format(expectedMessage, email));
//    } else {
//        registrationPage.invalidDataErrorMessageShouldBeVisible(expectedMessage);
//    }
//    }
}