package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TvCategoryPage {

    public final SelenideElement
    tvCategoryTitle = $("header#title_bread_wrap h1");

    public void checkTvCategoryTitle(String expectedTitle) {
        tvCategoryTitle
                .shouldBe(visible)
                .shouldHave(text(expectedTitle));
    }
}
