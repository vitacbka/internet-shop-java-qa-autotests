package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BooksCategoryPage {
    public final SelenideElement booksPageTitle = $("header#title_bread_wrap h1");

    public BooksCategoryPage isOnBooksPage(String url, String title) {
        webdriver().shouldHave(url(url));
        booksPageTitle.shouldBe(visible).shouldHave(text(title));
        return this;
    }
}