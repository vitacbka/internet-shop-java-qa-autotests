package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import java.util.HashSet;
import java.util.Set;

import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.refresh;

public class Cookies {
    private Set<Cookie> savedCookies = new HashSet<>();


    public void saveCookies() {
        savedCookies = WebDriverRunner.getWebDriver().manage().getCookies();
    }

    public void pasteCookies() {
        for (Cookie cookie : savedCookies) {
            WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        }
    }

    public void refreshPage() {
        refresh();
    }
}
