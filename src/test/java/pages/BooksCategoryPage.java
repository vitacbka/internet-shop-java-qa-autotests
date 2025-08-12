package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static readproperties.ConfigProvider.BASE_URL;
import static readproperties.ConfigProvider.BOOKS_CATEGORY_URL;
import static testdata.MainPageTestData.EXPECTED_BOOKS_PAGE_TITLE;
import static testdata.MainPageTestData.EXPECTED_MAIN_PAGE_TITLE_TEXT;

public class BooksCategoryPage {
    public final SelenideElement booksPageTitle = $("header#title_bread_wrap h1");
}