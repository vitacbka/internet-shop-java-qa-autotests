package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class CamerasCategoryPage {
    public final SelenideElement camerasPageTitle = $("header#title_bread_wrap h1");

    public CamerasCategoryPage isOnCamerasPage(String url, String title) {
      camerasPageTitle.shouldHave(text(title));
      webdriver().shouldHave(url(url));
      return this;
    }
}