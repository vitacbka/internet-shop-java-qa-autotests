package testdata;

import static readproperties.ConfigProvider.VALID_USER_LOGIN;

public class HeaderTestData {

    public static final String
            EXPECTED_HEADER_CONTACT_INFO = "+7-999-123-12-12 | skillbox@skillbox.ru",
            EXPECTED_HELLO_MESSAGE_AT_HEADER_TEXT = "Привет " + VALID_USER_LOGIN + " !",
            EXPECTED_MAIN_PAGE_TAB = "Главная",
            EXPECTED_CATALOG_TAB = "Каталог",
            EXPECTED_MY_ACCOUNT_TAB = "Мой аккаунт",
            EXPECTED_CART_TAB = "Корзина",
            EXPECTED_PLACE_AN_ORDER_TAB = "Оформление заказа",

    //Константы для выпадающего списка каталога в хедере
            EXPECTED_APPLIANCES_TEXT = "Бытовая техника",
            EXPECTED_ELECTRONICS_TEXT = "Электроника",
            EXPECTED_BOOKS_TAB = "Книги",
            EXPECTED_CLOSES_TAB = "Одежда",

    //Константы для выпадающего списка "бытовая техника"
            EXPECTED_REFRIGERATOR_TAB = "Холодильники",
            EXPECTED_WASHING_MACHINE_TAB = "Стиральные машины",

    //Констатны для выпадающего списка "Электроника"
            EXPECTED_PHONES_TAB = "Телефоны",
            EXPECTED_TABLETS_TAB = "Планшеты",
            EXPECTED_TELEVISION_TAB = "Телевизоры",
            EXPECTED_PHOTO_VIDEO_TAB = "Фото/Видео",
            EXPECTED_WATCH_TAB = "Часы",

    //Константы для кнопки "Войти" и кнопки "Выйти"
            EXPECTED_LOGIN_BUTTON_TEXT = "Войти",
            EXPECTED_LOGOUT_BUTTON_TEXT = "Выйти";
}
