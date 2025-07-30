package testdata;

import static readproperties.ConfigProvider.INVALID_USER_LOGIN;
import static readproperties.ConfigProvider.VALID_USER_LOGIN;

public class AuthPageTestData {

    public static final String EXPECTED_MY_ACCOUNT_TITLE = "Мой аккаунт";

    public static final String EXPECTED_REGISTRATION_BUTTON_TITLE = "ЗАРЕГИСТРИРОВАТЬСЯ";
    public static final String EXPECTED_FORGOT_PASSWORD_LINK_TEXT = "Забыли пароль?";
    public static final String EXPECTED_PASSWORD_RECOVERY_TITLE = "Восстановление пароля";
    public static final String EXPECTED_REMEMBER_ME_CHECKBOX_TITLE = "Запомнить меня";

    public static final String EXPECTED_PASSWORD_IS_REQUIRED_ERROR = "Пароль обязателен.";
    public static final String EXPECTED_USERNAME_IS_REQUIRED_ERROR = "Error: Имя пользователя обязательно.";
    public static final String EXPECTED_INVALID_USERNAME_OR_PASSWORD_ERROR = "Неизвестный адрес почты. Попробуйте еще раз или введите имя пользователя.";
    public static final String EXPECTED_HELLO_MESSAGE_TEXT = "Привет " + VALID_USER_LOGIN + " (Выйти)";
    public static final String EXPECTED_INVALID_PASSWORD_ERROR = "Веденный пароль для пользователя " + VALID_USER_LOGIN + " неверный. Забыли пароль?";
    public static final String EXPECTED_INVALID_CREDENTIALS_ERROR_MESSAGE = "Неизвестное имя пользователя. Попробуйте еще раз или укажите адрес почты.";
}
