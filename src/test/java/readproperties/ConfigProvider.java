package readproperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("application.conf");
    }

    String BASE_URL = readConfig().getString("BASE_URL");
    String BOOKS_CATEGORY_URL = readConfig().getString("BOOKS_CATEGORY_URL");
    String TABLETS_CATEGORY_URL = readConfig().getString("TABLETS_CATEGORY_URL");
    String CAMERAS_CATEGORY_URL = readConfig().getString("CAMERAS_CATEGORY_URL");
    String CATALOG_PAGE_URL = readConfig().getString("CATALOG_PAGE_URL");
    String CART_PAGE_URL = readConfig().getString("CART_PAGE_URL");
    String TV_CATEGORY_URL = readConfig().getString("TV_CATEGORY_URL");
    String PLACE_AN_ORDER_URL = readConfig().getString("PLACE_AN_ORDER_URL");

    String MY_ACCOUNT_PAGE_URL = readConfig().getString("MY_ACCOUNT_PAGE_URL");
    String PASSWORD_RECOVERY_URL = readConfig().getString("PASSWORD_RECOVERY_URL");
    String REGISTRATION_PAGE_URL = readConfig().getString("REGISTRATION_PAGE_URL");

    String VALID_COUPON = readConfig().getString("VALID_COUPON");
    String INVALID_COUPON = readConfig().getString("INVALID_COUPON");

    String VALID_USER_LOGIN = readConfig().getString("usersParams.validUser.login");
    String VALID_USER_EMAIL = readConfig().getString("usersParams.validUser.email");
    String VALID_USER_PASSWORD = readConfig().getString("usersParams.validUser.password");

    String INVALID_USER_LOGIN = readConfig().getString("usersParams.invalidUser.invalidLogin");
    String INVALID_USER_EMAIL = readConfig().getString("usersParams.invalidUser.invalidEmail");
    String INVALID_USER_PASSWORD = readConfig().getString("usersParams.invalidUser.invalidPassword");
}