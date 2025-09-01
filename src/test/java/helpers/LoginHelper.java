package helpers;

import pages.MyAccountPage;
import static readproperties.ConfigProvider.VALID_USER_LOGIN;
import static readproperties.ConfigProvider.VALID_USER_PASSWORD;

public class LoginHelper {
    MyAccountPage myAccountPage = new MyAccountPage();

    public void loginWithValidUserLogin() {
        myAccountPage
                .openAuthPage()
                .enterCredentials(VALID_USER_LOGIN, VALID_USER_PASSWORD)
                .clickLoginButton();
    }
}
