package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.HashSet;
import java.util.Set;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class CookiesHelper {
    private Set<Cookie> savedCookies = new HashSet<>();

    public CookiesHelper saveCookies() {
        savedCookies = WebDriverRunner.getWebDriver().manage().getCookies();
        return this;
    }

    public CookiesHelper pasteCookies() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().manage().deleteAllCookies();
            for (Cookie cookie : getSavedCookies()) {
                try {
                    WebDriverRunner.getWebDriver().manage().addCookie(cookie);
                    System.out.println("Added cookie: " + cookie.getName());
                } catch (Exception e) {
                    System.out.println("Failed to add cookie: " + cookie.getName() + " - " + e.getMessage());
                }
            }
        } else {
            System.err.println("WebDriver not started. Cannot paste cookies.");
        }
        return this;
    }

    public CookiesHelper refresh() {
        Selenide.refresh();
        return this;
    }

    public CookiesHelper clearCookies() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.getWebDriver().manage().deleteAllCookies();
        } else {
            System.err.println("WebDriver not started. Cannot clear cookies.");
        }
        return this;
    }

    private Set<Cookie> getSavedCookies() {
        return savedCookies;
    }

    public CookiesHelper closeWebDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
        return this;
    }
}