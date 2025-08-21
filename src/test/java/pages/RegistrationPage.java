package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.GenerateRandomRegistrationData;
import static org.assertj.core.api.Assertions.assertThat;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;
import static readproperties.ConfigProvider.REGISTRATION_PAGE_URL;
import static testdata.RegistrationPageTestData.EXPECTED_REGISTRATION_PAGE_TITLE;

public class RegistrationPage {
    GenerateRandomRegistrationData generateRandomRegistrationData = new GenerateRandomRegistrationData();

    public final SelenideElement registrationPageTitle = $("h2.post-title"),
            usernameInputField = $("#reg_username"),
            emailInputField = $("#reg_email"),
            passwordInputField = $("#reg_password"),
            submitButton = $("button[value='Зарегистрироваться']"),
            successRegistrationMessage = $(".content-page"),
            errorMessageMinUsernameLength = $(".woocommerce-error"),
            errorMessageMaxEmailLength = $(".woocommerce-error"),
            invalidRegistrationError = $(".woocommerce-error li"),
            emptyEmailFieldErrorMessage = $(".woocommerce-error li"),
            emptyPasswordFieldErrorMessage = $(".woocommerce-error li"),
            emptyUsernameFieldErrorMessage = $(".woocommerce-error li"),
            emailAlreadyExistsErrorMessage = $(".woocommerce-error li");

    public RegistrationPage openRegistrationPage() {
        open(REGISTRATION_PAGE_URL);
        return this;
    }

    public RegistrationPage shouldBeOnRegistrationPage() {
        webdriver().shouldHave(urlContaining(REGISTRATION_PAGE_URL));
        registrationPageTitle.shouldBe(visible)
                .shouldHave(text(EXPECTED_REGISTRATION_PAGE_TITLE));
        return this;
    }

    public RegistrationPage fillRegistrationFormWithExistingData(String username, String password, String email ) {
        usernameInputField.shouldBe(visible)
                .setValue(username)
                .shouldHave(value(username));

        emailInputField.shouldBe(visible)
                .setValue(email)
                .shouldHave(value(email));

        passwordInputField.shouldBe(visible)
                .setValue(password)
                .shouldHave(value(password));
        return this;
    }

    public RegistrationPage fillRegistrationFormWithGeneratedData(GenerateRandomRegistrationData.TestUser user) {
        usernameInputField.shouldBe(visible)
                .setValue(user.username);

        emailInputField.shouldBe(visible)
                .setValue(user.email);

        passwordInputField.shouldBe(visible)
                .setValue(user.password);
        return this;
    }

    public RegistrationPage clickSubmitButton() {
        submitButton.shouldBe(visible).click();
        return this;
    }

    public RegistrationPage successfulRegistrationMessageShouldBeVisible(String expectedSuccessMessageText) {
        successRegistrationMessage.shouldBe(visible).shouldHave(text(expectedSuccessMessageText));
    return this;
    }

    public RegistrationPage invalidDataErrorMessageShouldBeVisible(String expectedErrorMessageText) {
        invalidRegistrationError.shouldBe(visible).shouldHave(text(expectedErrorMessageText));
        return this;
    }

    public String html5ValidationMessage(SelenideElement field) {
        field.shouldBe(visible);
        return executeJavaScript(
                "arguments[0].reportValidity(); return arguments[0].validationMessage;",
                field
        );
    }

    public RegistrationPage shouldHaveEmailHtml5ValidationMessage(String expectedText) {
        Boolean typeMismatch = executeJavaScript(
                "return arguments[0].validity.typeMismatch;",
                emailInputField
        );
        assertThat(typeMismatch)
                .as("Email field should have typeMismatch validation error")
                .isTrue();

        String actualMessage = executeJavaScript(
                "return arguments[0].validationMessage;",
                emailInputField
        );
        assertThat(actualMessage)
                .as("Email validation message text")
                .isEqualTo(expectedText);

        return this;
    }
}
