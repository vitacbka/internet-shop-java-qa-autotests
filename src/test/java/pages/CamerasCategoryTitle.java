package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CamerasCategoryTitle {
    public final SelenideElement camerasPageTitle = $("header#title_bread_wrap h1");
}