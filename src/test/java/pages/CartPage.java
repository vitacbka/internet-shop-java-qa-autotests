package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static readproperties.ConfigProvider.*;
import static testdata.CartPageTestData.EXPECTED_CART_PAGE_TITLE;

public class CartPage {

    public final SelenideElement
            cartPageTitle = $("span.current"),
            cartIsEmptyMessage = $("p.cart-empty"),
            itemInCart = $(".woocommerce-cart-form__cart-item"),
            toPayPrice = $("tr[class='order-total'] bdi:nth-child(1)"),
            couponInputField = $("#coupon_code"),
            toPayTitle = $("tr[class='order-total'] th"),
            deleteCouponButton = $(".woocommerce-remove-coupon"),
            successfulCouponMessage = $("div[role='alert']"),
            errorCouponMessage = $("ul[role='alert'] li"),
            doubleApplyCouponMessage = $("ul[role='alert'] li"),
            emptyCouponMessage = $("ul[role='alert'] li"),
            placingAnOrderPageTitle = $("div#accesspress-breadcrumb span.current"),
            applyCouponButton = $x("//button[contains(text(),'Применить купон')]"),
            removeItemFromCartButton = $("a[aria-label='Remove this item']"),
            restoreItemButton = $x("//a[@class='restore-item' and text()='Вернуть?']"),
            toPayButton = $(".checkout-button.button.alt.wc-forward"),
            deletedCouponMessage = $x("//div[@role='alert' and contains(text(), 'Купон удален')]"),
            placeAnOrderButton = $(".checkout-button.button.alt.wc-forward"),
            productNameInCart = $x("(//*[@class='product-name'])[2]");

    public void openCartPage() {
        open(CART_PAGE_URL);
    }

    public void isOnCartPage() {
        webdriver().shouldHave(url(CART_PAGE_URL));
        cartPageTitle.shouldBe(visible).shouldHave(text(EXPECTED_CART_PAGE_TITLE));
    }

    public void clearCart() {
        removeItemFromCartButton.shouldBe(visible).click();
    }

    public SelenideElement getCartPageTitle() {
        return cartPageTitle;
    }

    public void setCouponData(String coupon) {
        couponInputField.setValue(coupon);
    }

    public void clickOnApplyCouponButton() {
        applyCouponButton.shouldBe(visible).click();
    }
}