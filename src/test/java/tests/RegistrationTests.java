package tests;

import helpers.GenerateRandomRegistrationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static readproperties.ConfigProvider.*;
import static testdata.RegistrationPageTestData.*;

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
                .fillRegistrationFormWithExistingData(VALID_USER_LOGIN, VALID_USER_PASSWORD, VALID_USER_EMAIL)
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
}