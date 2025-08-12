package pages;

import com.codeborne.selenide.SelenideElement;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

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
            removeCouponButton = $(".woocommerce-remove-coupon"),
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

    public void clickRemoveCouponButton() {
        removeCouponButton.click();
    }

    public double getTotalPriceAsDouble() {
        String priceText = toPayPrice.text()
                .replaceAll("[^\\d,]", "")
                .replace(",", ".");

        return Double.parseDouble(priceText);
    }


    public void waitForPriceChange(double oldPrice) {
        toPayPrice.shouldNotHave(text(String.valueOf(oldPrice)), Duration.ofSeconds(10));
    }
}