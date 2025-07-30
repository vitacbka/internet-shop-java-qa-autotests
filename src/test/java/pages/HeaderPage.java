package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.openqa.selenium.Keys.ENTER;

public class HeaderPage {

    public final SelenideElement
    headerContactInfo = $("#custom_html-2 > div"),
    tabsInHeader = $("#menu-primary-menu"),
    searchInputField = $(".search-form input[type='text']");

    public void searchItemByNameAtSearchInputField(String text) {
        searchInputField.shouldBe(visible);
        searchInputField.setValue(text);
        searchInputField.sendKeys(ENTER);
    }

}
