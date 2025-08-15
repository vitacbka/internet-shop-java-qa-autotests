package helpers;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.HashSet;
import java.util.Set;

public class Cookies {
    private Set<Cookie> savedCookies = new HashSet<>();

    public Cookies saveCookies() {
        savedCookies = WebDriverRunner.getWebDriver().manage().getCookies();
        return this;
    }

    public Cookies pasteCookies() {
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

    public Cookies refresh() {
        Selenide.refresh();
        return this;
    }

    private Set<Cookie> getSavedCookies() {
        return savedCookies;
    }

    public Cookies closeWebDriver() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
        return this;
    }
}