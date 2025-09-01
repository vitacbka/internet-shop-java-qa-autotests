package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class TabletsCategoryPage {
    public final SelenideElement tabletsPageTitle = $("header#title_bread_wrap h1");

    public TabletsCategoryPage isOnTabletsPage (String url, String title) {
        webdriver().shouldHave(url(url));
        tabletsPageTitle.shouldBe(visible).shouldHave(Condition.text(title));
        return this;
    }
}