package tests;

import helpers.ClearCartHelper;
import helpers.ProductAddToCartHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HeaderPage;
import pages.MyAccountPage;

import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static readproperties.ConfigProvider.*;
import static testdata.CartPageTestData.*;

public class CartPageTests extends BaseTest {
    ProductAddToCartHelper productAddToCartHelper = new ProductAddToCartHelper();
    CartPage cartPage = new CartPage();
    HeaderPage header = new HeaderPage();
    MyAccountPage myAccountPage = new MyAccountPage();
    ClearCartHelper clearCartHelper = new ClearCartHelper();

    @BeforeEach
    void setUp() {
        myAccountPage.login(VALID_USER_LOGIN, VALID_USER_PASSWORD);
        clearCartHelper.removeCouponAndClearCart();
        productAddToCartHelper.addPhoneAtCart();
        header.clickCartTab();
        cartPage.isOmCartPage(EXPECTED_CART_PAGE_TITLE, CART_PAGE_URL);
    }

    @Test
    @DisplayName("Added in cart product name should be visible")
    void addedInCartProductNameShouldBeVisible() {
        cartPage.productNameInCart.shouldBe(visible);
    }

    @Test
    @DisplayName("Cart should be empty after remove itmen")
    void cartShouldBeEmptyAfterClearTest() {
        cartPage
                .removeItemFromCart()
                .cartIsEmptyMessage(EXPECTED_CART_IS_EMPTY_MESSAGE);
    }

    @Test
    @DisplayName("Item should be restored after click on restore button")
    void itemShouldBeRestoredAfterClickOnRestoreButtonTest() {
        cartPage
                .removeItemFromCart()
                .clickOnRestoreItemButton()
                .restoreItemMessageShouldBeVisible(EXPECTED_ITEM_IS_RESTORED_MESSAGE);
    }

    @Test
    @DisplayName("Successful apply coupon message should be displayed after apply valid coupon")
    void successfulApplyCouponMessageShouldBeDisplayedAfterApplyValidCouponTest() {
        cartPage
                .setCouponData(VALID_COUPON)
                .clickOnApplyCouponButton()
                .successfulCouponMessageShouldBeVisible(EXPECTED_SUCCESSFUL_APPLY_COUPON_MESSAGE);
    }

    @Test
    @DisplayName("Invalid coupon error message should be displayed after apply invalid coupon")
    void invalidCouponErrorCouponMessageShouldBeDisplayedAfterApplyInvalidCouponTest() {
        cartPage
                .setCouponData(INVALID_COUPON)
                .clickOnApplyCouponButton()
                .invalidCouponMessageShouldBeVisible(EXPECTED_ERROR_INVALID_COUPON_MESSAGE);
        System.out.println(cartPage.errorCouponMessage.text());
    }

    @Test
    @DisplayName("Empty coupon message should be displayed after user use empty coupon and click apply coupon button")
    void emptyCouponMessageShouldBeDisplayedAfterUserUseEmptyCouponAndCLickCouponButtonTest() {
        cartPage
                .setCouponData("")
                .clickOnApplyCouponButton()
                .emptyCouponMessageShouldBeVisible(EXPECTED_EMPTY_COUPON_MESSAGE);
    }

    @Test
    @DisplayName("Deleted coupon message should be displayed after delete coupon")
    void deletedCouponMessageShouldBeDisplayedAfterDeleCouponTest() {
        cartPage
                .setCouponData(VALID_COUPON)
                .clickOnApplyCouponButton()
                .successfulCouponMessageShouldBeVisible(EXPECTED_SUCCESSFUL_APPLY_COUPON_MESSAGE)
                .clickRemoveCouponButton()
                .deletedCouponMessageShouldBeVisible(EXPECTED_COUPON_IS_REMOVED_MESSAGE);
    }

    @Test
    @DisplayName("Price should be changed after apply valid coupon")
    void priceShouldBeChangedAfterApplyValidCouponTest() {
        header.clickCartTab();
        cartPage.isOmCartPage(EXPECTED_CART_PAGE_TITLE, CART_PAGE_URL);
        double beforeCouponPrice = cartPage.getTotalPriceAsDouble();

        cartPage.setCouponData(VALID_COUPON);
        cartPage.clickOnApplyCouponButton();
        cartPage.waitForPriceChange(beforeCouponPrice);
        double afterCouponPrice = cartPage.getTotalPriceAsDouble();

        assertEquals(beforeCouponPrice, afterCouponPrice,
                "Цена после применения купона должна измениться.");
    }

    @Test
    @DisplayName("Error message should be displayed after reuse coupon")
    void errorMessageShouldBeDisplayedAfterReuseCoupon() {
        cartPage
                .twiceApplyValidCoupon(VALID_COUPON)
                .alreadyApplyCouponMessageShouldBeVisible(EXPECTED_COUPON_ALREADY_APPLIED_MESSAGE);
    }
}