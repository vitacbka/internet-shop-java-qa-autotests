package tests;

import helpers.ClearCartHelper;
import helpers.ProductAddToCartHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.HeaderPage;
import pages.MyAccountPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        productAddToCartHelper.addPhoneToCart();
        header.clickCartTab();
        cartPage.isOnCartPage(EXPECTED_CART_PAGE_TITLE);
    }

    @Test
    @DisplayName("Added in cart product name should be visible")
    void addedInCartProductNameShouldBeVisible() {
        List<String> addedProductNames = productAddToCartHelper.getAddedProductNames();
        assertFalse(addedProductNames.isEmpty(), "Сипсок товаров н едолжен быть пустым");

        String expectedItemNAme = addedProductNames.get(0);

        cartPage.verifyItemInCartVisible(expectedItemNAme);
    }

    @Test
    @DisplayName("Cart should be empty after remove item from cart")
    void cartShouldBeEmptyAfterClearTest() {
        cartPage
                .removeItemFromCart()
                .verifyEmptyCartMessage(EXPECTED_CART_IS_EMPTY_MESSAGE);
    }

    @Test
    @DisplayName("Item should be restored after click on restore button")
    void itemShouldBeRestoredAfterClickOnRestoreButtonTest() {
        List<String> addedProductNames = productAddToCartHelper.getAddedProductNames();
        assertFalse(addedProductNames.isEmpty(), "Сипсок товаров н едолжен быть пустым");
        String expectedItemName = addedProductNames.get(0);
        clearCartHelper.clearCart();
        cartPage
                .verifyEmptyCartMessage(EXPECTED_CART_IS_EMPTY_MESSAGE)
                .clickOnRestoreItemButton()
                .verifyItemInCartVisible(expectedItemName);
    }

    @Test
    @DisplayName("Successful apply coupon message should be displayed after apply valid coupon")
    void successfulApplyCouponMessageShouldBeDisplayedAfterApplyValidCouponTest() {
        cartPage
                .setCouponData(VALID_COUPON)
                .clickOnApplyCouponButton()
                .verifySuccessfulCouponMessage(EXPECTED_SUCCESSFUL_APPLY_COUPON_MESSAGE);
    }

    @Test
    @DisplayName("Invalid coupon error message should be displayed after apply invalid coupon")
    void invalidCouponErrorCouponMessageShouldBeDisplayedAfterApplyInvalidCouponTest() {
        cartPage
                .setCouponData(INVALID_COUPON)
                .clickOnApplyCouponButton()
                .verifyInvalidCouponMessage(EXPECTED_ERROR_INVALID_COUPON_MESSAGE);
    }

    @Test
    @DisplayName("Empty coupon message should be displayed after user use empty coupon and click apply coupon button")
    void emptyCouponMessageShouldBeDisplayedAfterUserUseEmptyCouponAndCLickCouponButtonTest() {
        cartPage
                .setCouponData("")
                .clickOnApplyCouponButton()
                .verifyEmptyCouponMessage(EXPECTED_EMPTY_COUPON_MESSAGE);
    }

    @Test
    @DisplayName("Deleted coupon message should be displayed after delete coupon")
    void deletedCouponMessageShouldBeDisplayedAfterDeleCouponTest() {
        cartPage
                .setCouponData(VALID_COUPON)
                .clickOnApplyCouponButton()
                .verifySuccessfulCouponMessage(EXPECTED_SUCCESSFUL_APPLY_COUPON_MESSAGE)
                .clickRemoveCouponButton()
                .verifyDeletedCouponMessage(EXPECTED_COUPON_IS_REMOVED_MESSAGE);
    }

    @Test
    @DisplayName("Price should be changed after apply valid coupon")
    void priceShouldBeChangedAfterApplyValidCouponTest() {
        header.clickCartTab();
        cartPage.isOnCartPage(EXPECTED_CART_PAGE_TITLE);

        double beforeCouponPrice = cartPage.getTotalPriceAsDouble();

        cartPage
                .setCouponData(VALID_COUPON)
                .clickOnApplyCouponButton()
                .verifySuccessfulCouponMessage(EXPECTED_SUCCESSFUL_APPLY_COUPON_MESSAGE)
                .waitForPriceChange(beforeCouponPrice);
        double afterCouponPrice = cartPage.getTotalPriceAsDouble();

        assertTrue(beforeCouponPrice > afterCouponPrice,
                "Цена после купона должна быть меньше");
    }

    @Test
    @DisplayName("Error message should be displayed after reuse coupon")
    void errorMessageShouldBeDisplayedAfterReuseCoupon() {
        cartPage
                .twiceApplyValidCoupon(VALID_COUPON)
                .veifyAlreadyApplyCouponMessage(EXPECTED_COUPON_ALREADY_APPLIED_MESSAGE);
    }
}