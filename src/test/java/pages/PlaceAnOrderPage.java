package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.text.ParseException;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.byLessThan;

public class PlaceAnOrderPage {

    private final SelenideElement
            pageTitle = $(".post-title"),
    //Локаторы полей ввода
            nameInputField = $("#billing_first_name"),
            surnameInputField = $("#billing_last_name"),
            countrySelect = $("#billing_country"),
            addressInputField = $("#billing_address_1"),
            cityInputField = $("#billing_city"),
            regionInputField = $("#billing_state"),
            postalCodeInputField = $("#billing_postcode"),
            phoneInputField = $("#billing_phone"),
            emailInputField = $("#billing_email"),
            additionalInformation = $("#order_comments");
    //Селектор
    private final SelenideElement countryRussia = $x("//li[text()='Russia']");
    //Локаторы имени товара и цен
    private ElementsCollection productPrices = $$("#order_review .product-total .amount bdi");

    private final SelenideElement
            itemOrderName = $("td.product-name"),
            totalAmountPrice = $("tr.cart-subtotal .amount"),
            totalPriceToPay = $("tr.order-total span.amount"),

    //Локаторы радиокнопок
            directBankTransfer = $("#payment_method_bacs"),
            cashOnDelivery = $("#payment_method_cod"),

    //Локатор кнопки
            placeOrderButton = $("#place_order"),

            orderReceivedTitle = $("h2.post-title"),
            orderNumber = $("li.order"),
            dateInOrder = $("li.date"),
            emailInOrder = $("li.email"),
            totalPriceInOrder = $("li.total"),
            paymentMethodInOrder = $("li.method"),

    //Локаторы для купона
            pressToEnterCoupon = $("a.showcoupon"),
            couponInputField = $("#coupon_code"),
            applyCouponButton = $("button[value='Применить купон']"),
            invalidCouponMessage = $("ul[role='alert'] li"),
            couponAppliedMessage = $("div[role='alert']");

    public PlaceAnOrderPage isOnPlaceAnOrderPage(String title, String expectedUrl) {
        pageTitle.shouldBe(visible).shouldHave(text(title));
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }
    //Локаторы ошибок пустых полей
    public SelenideElement errorMessagesBlock = $(".woocommerce-error[role='alert']");

    // Локатор для конкретной ошибки по ID поля
    public SelenideElement getErrorByFieldId(String fieldId) {
        return $(String.format("li[data-id='%s']", fieldId));
    }

    // Локатор для всех ошибок
    public ElementsCollection getAllErrors() {
        return $$("ul.woocommerce-error[role='alert'] > li");
    }

    public PlaceAnOrderPage clearInputFields() {
        nameInputField.shouldBe(visible).clear();
        surnameInputField.shouldBe(visible).clear();
        addressInputField.shouldBe(visible).clear();
        cityInputField.shouldBe(visible).clear();
        regionInputField.shouldBe(visible).clear();
        postalCodeInputField.shouldBe(visible).clear();
        phoneInputField.shouldBe(visible).clear();
        emailInputField.shouldBe(visible).clear();
        return this;
    }

    public PlaceAnOrderPage fillInOrderForm(String name, String surname, String country,
                                            String address, String city, String region,
                                            String postalCode, String phone, String email) {
        nameInputField.shouldBe(visible).setValue(name);
        surnameInputField.shouldBe(visible).setValue(surname);
        countrySelect.shouldBe(visible).selectOption(country);
            addressInputField.shouldBe(visible).setValue(address);
            cityInputField.shouldBe(visible).setValue(city);
            regionInputField.shouldBe(visible).setValue(region);
            postalCodeInputField.shouldBe(visible).setValue(postalCode);
            phoneInputField.shouldBe(visible).setValue(phone);
            emailInputField.shouldBe(visible).setValue(email);
            this.enteredEmail = email;
        return this;
    }

    public PlaceAnOrderPage fillAdditionalInformation(String additionalInfo) {
        additionalInformation.shouldBe(visible).setValue(additionalInfo);
        return this;
    }

    private double parsePrice(String text) {
        String cleaned = text.replaceAll("[^\\d,\\.]", "").trim();
        cleaned = cleaned.replace(",", ".");
        return Double.parseDouble(cleaned);
    }
    // Сумма цен всех товаров
    public double getSumOfProductPrices() throws ParseException {
        double sum = 0;
        for (SelenideElement priceElement : productPrices) {
            sum += parsePrice(priceElement.getText());
        }
        return sum;
    }
    // Общая сумма (до оплаты)
    public double getTotalAmount() throws ParseException {
        return parsePrice(totalAmountPrice.getText());
    }
    // Итоговая сумма к оплате
    public double getTotalToPay() throws ParseException {
        return parsePrice(totalPriceToPay.getText());
    }
    // Проверка соответствия сумм
    public void verifyTotalPricesMatch() throws ParseException {
        double sumProducts = getSumOfProductPrices();
        double totalAmount = getTotalAmount();
        double totalToPay = getTotalToPay();

        assertThat(sumProducts)
                .as("Sum of all items should be equals Total price")
                .isCloseTo(totalAmount, byLessThan(0.01));

        assertThat(totalAmount)
                .as("Total price should be equals total to pay price")
                .isCloseTo(totalToPay, byLessThan(0.01));
    }

    public PlaceAnOrderPage itemNameShouldBeVisible() {
        itemOrderName.shouldBe(visible);
        return this;
    }

    public PlaceAnOrderPage clickOnPlaceOrderButtonWithPriceCapture() throws ParseException {
        // Сохраняем общую сумму ПЕРЕД оформлением заказа
        this.expectedTotalPrice = getTotalToPay();

        placeOrderButton.shouldBe(visible).click();
        return this;
    }

    public PlaceAnOrderPage clickOnPlaceOrderButton() {
        placeOrderButton.shouldBe(visible).click();
        return this;
    }

    public PlaceAnOrderPage clickOnDirectBankTransferRadioButton() {
        directBankTransfer.shouldBe(visible).click();
        this.selectedPaymentMethod = "Прямой банковский перевод";
        return this;
    }

    public PlaceAnOrderPage clickOnCashOnDeliveryRadioButton() {
        cashOnDelivery.shouldBe(visible).click();
        this.selectedPaymentMethod = "Оплата при доставке";
        return this;
    }

    private String enteredEmail;
    private double expectedTotalPrice;
    private String selectedPaymentMethod;

    public PlaceAnOrderPage verifyOrderDetails() {
        orderNumber.shouldBe(visible).shouldNotBe(empty);
        dateInOrder.shouldBe(visible);
        emailInOrder.shouldBe(visible).shouldHave(text(enteredEmail));
        double actualPrice = parsePrice(totalPriceInOrder.getText());
        assertThat(actualPrice).isCloseTo(expectedTotalPrice, byLessThan(0.01));

        paymentMethodInOrder.shouldBe(visible).shouldHave(text(selectedPaymentMethod));

        return this;
    }

    public PlaceAnOrderPage orderReceivedTitleShouldBeVisible(String title) {
        orderReceivedTitle.shouldBe(visible).shouldHave(text(title));
        return this;
    }

    public PlaceAnOrderPage clickPressToEnterCoupon(String expectedText) {
        pressToEnterCoupon.shouldBe(visible).shouldHave(text(expectedText));
        pressToEnterCoupon.click();
        return this;
    }

    public PlaceAnOrderPage appyCoupon(String coupon) {
        couponInputField.shouldBe(visible).setValue(coupon);
        return this;
    }

    public PlaceAnOrderPage clickApplyCouponButton() {
        applyCouponButton.shouldBe(visible).click();
        return this;
    }

    public PlaceAnOrderPage verifySuccesfullCouponAppliedMessage(String message) {
        couponAppliedMessage.shouldBe(visible).shouldHave(text(message));
        return this;
    }

    public PlaceAnOrderPage verifyInvalidCouponMessageIsDisplayed(String message) {
        invalidCouponMessage.shouldBe(visible).shouldHave(text(message));
        return this;
    }

    public PlaceAnOrderPage verifyFirstNameError(String expectedText) {
        getErrorByFieldId("billing_first_name")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public PlaceAnOrderPage verifyLastNameError(String expectedText) {
        getErrorByFieldId("billing_last_name")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public PlaceAnOrderPage verifyAddressError(String expectedText) {
        getErrorByFieldId("billing_address_1")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public PlaceAnOrderPage verifyCityError(String expectedText) {
        getErrorByFieldId("billing_city")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public PlaceAnOrderPage verifyRegionError(String expectedText) {
        getErrorByFieldId("billing_state")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public PlaceAnOrderPage verifyPostalCodeError(String expectedText) {
        getErrorByFieldId("billing_postcode")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public PlaceAnOrderPage verifyPhoneIsRequiredError(String expectedText) {
        getErrorByFieldId("billing_phone")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        System.out.println(getErrorByFieldId("billing_phone").text());
        return this;
    }

    public PlaceAnOrderPage verifyPhoneError(String expectedText) {
        getErrorByFieldId("billing_phone")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }

    public PlaceAnOrderPage verifyEmailError(String expectedText) {
        getErrorByFieldId("billing_email")
                .shouldBe(visible)
                .shouldHave(text(expectedText));
        return this;
    }
}
