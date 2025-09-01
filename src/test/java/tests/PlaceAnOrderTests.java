package tests;

import helpers.ClearCartHelper;
import helpers.LoginHelper;
import helpers.ProductAddToCartHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.HeaderPage;
import pages.MainPage;
import pages.PlaceAnOrderPage;

import java.text.ParseException;

import static com.codeborne.selenide.Selenide.sleep;
import static readproperties.ConfigProvider.*;
import static testdata.PlaceAnOrderTestData.*;

public class PlaceAnOrderTests extends BaseTest {
    LoginHelper loginHelper = new LoginHelper();
    ProductAddToCartHelper productAddToCartHelper = new ProductAddToCartHelper();
    HeaderPage headerPage = new HeaderPage();
    PlaceAnOrderPage placeAnOrderPage = new PlaceAnOrderPage();
    MainPage mainPage = new MainPage();
    ClearCartHelper clearCartHelper = new ClearCartHelper();

    @BeforeEach
    void setUp() {
        mainPage.openMainPage();
        loginHelper.loginWithValidUserLogin();
        clearCartHelper.removeCouponAndClearCart();
        productAddToCartHelper.addTwoPhonesToCart();
        headerPage.clickPlaceAnOrderTab();
        placeAnOrderPage.isOnPlaceAnOrderPage(EXPECTED_PLACE_AN_ORDER_PAGE_TITLE, PLACE_AN_ORDER_URL);
    }

    @Test
    @DisplayName("Total price should be equals to sum of product prices")
    void totalPriceShouldBeEqualsToSumOfProductPricesTest() throws ParseException {
        placeAnOrderPage.verifyTotalPricesMatch();
    }

    @Test
    @DisplayName("Item name should be visible at your order")
    void itemNameShouldBeVisibleTest() {
    placeAnOrderPage.itemNameShouldBeVisible();
    }

    @Test
    @DisplayName("Order information should be visible after place an order button click")
    void successfulOrderReceivedMessageShouldBeDisplayedTest() throws ParseException {
        placeAnOrderPage.clearInputFields()
                .fillInOrderForm(FIRST_NAME, LAST_NAME, COUNTRY,
                        ADDRESS, CITY, STATE,
                        POSTAL_CODE, PHONE, EMAIL)
                .fillAdditionalInformation(ADDITIONAL_INFORMATION_TEXT)
                .clickOnDirectBankTransferRadioButton()
                .clickOnPlaceOrderButtonWithPriceCapture()
                .orderReceivedTitleShouldBeVisible(EXPECTED_ORDER_RECEIVED_PAGE_TITLE)
                .verifyOrderDetails();
    }

    @Test
    @DisplayName("Order information with cash on delivery should be visible after place an order button click")
    void successfulOrderReceivedMessageCashOnDeliveryWithDirectBankTransferShouldBeDisplayedTest() throws ParseException {
        placeAnOrderPage
                .clearInputFields()
                .fillInOrderForm(FIRST_NAME, LAST_NAME, COUNTRY,
                        ADDRESS, CITY, STATE,
                        POSTAL_CODE, PHONE, EMAIL)
                .fillAdditionalInformation(ADDITIONAL_INFORMATION_TEXT)
                .clickOnCashOnDeliveryRadioButton()
                .clickOnPlaceOrderButtonWithPriceCapture()
                .orderReceivedTitleShouldBeVisible(EXPECTED_ORDER_RECEIVED_PAGE_TITLE)
                .verifyOrderDetails();
    }

    @Test
    @DisplayName("Should be displayed successful applied coupon message")
    void successfulAppliedCouponMessageShouldBeDisplayedTest() {
        placeAnOrderPage
                .clickPressToEnterCoupon(EXPECTED_PRESS_TO_ENTER_COUPON_TEXT)
                .appyCoupon(VALID_COUPON)
                .clickApplyCouponButton()
                .verifySuccesfullCouponAppliedMessage(EXPECTED_COUPON_APPLIED_TEXT);
    }

    @Test
    @DisplayName("Error coupon message should be displayed")
    void errorCouponMessageShouldBeDisplayedTest() {
        placeAnOrderPage
                .clickPressToEnterCoupon(EXPECTED_PRESS_TO_ENTER_COUPON_TEXT)
                .appyCoupon(INVALID_COUPON)
                .clickApplyCouponButton()
                .verifyInvalidCouponMessageIsDisplayed(EXPECTED_ERROR_COUPON_MESSAGE_TEXT);
    }

    @Test
    @DisplayName("Errors empty input fields should be displayed")
    void errorsEmptyInputFieldsShouldBeDisplayedTest() {
        placeAnOrderPage
                .clearInputFields()
                .clickOnPlaceOrderButton()
                .verifyFirstNameError(FIRST_NAME_REQUIRED_ERROR)
                .verifyLastNameError(LAST_NAME_REQUIRED_ERROR)
                .verifyAddressError(ADDRESS_REQUIRED_ERROR)
                .verifyCityError(CITY_REQUIRED_ERROR)
                .verifyRegionError(STATE_REQUIRED_ERROR)
                .verifyPostalCodeError(POSTCODE_REQUIRED_ERROR)
                .verifyPhoneError(PHONE_INVALID_ERROR)
                .verifyPhoneIsRequiredError(PHONE_INVALID_ERROR)
                .verifyEmailError(EMAIL_REQUIRED_ERROR);
    }
}