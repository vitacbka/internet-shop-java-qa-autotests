package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    @BeforeAll
    static void setUpAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 20_000;
        Configuration.pollingInterval = 200;
        Configuration.pageLoadTimeout = 60_000;
        Configuration.remoteReadTimeout = 30_000;
        Configuration.remoteConnectionTimeout = 30_000;
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}