package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CatalogPage {

    public SelenideElement catalogPageTitle = $(".entry-title.ak-container");

    public void catalogPageTitleShouldBeVisible (String title) {
        catalogPageTitle.shouldBe(visible).shouldHave(text(title));
    }
}
