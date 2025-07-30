package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class BooksCategoryPage {
    public final SelenideElement booksPageTitle = $("header#title_bread_wrap h1");
}