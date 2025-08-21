package helpers;

import com.github.javafaker.Faker;

public class GenerateRandomRegistrationData {

    private static final Faker faker = new Faker();

    // Генерация валидного пользователя
    public static TestUser generateValidUser() {
        String username = generateUsername(13); // 13 символов для email длиной 20 (13 + 7 = 20)
        String email = username + "@ex.com";
        String password = faker.internet().password(8, 20);
        return new TestUser(username, email, password);
    }

    // Email без имени в доменной части
    public static TestUser generateUserWithoutNameInEmail() {
        String username = generateUsername(12);
        String email = "@ex.com"; // 7 символов
        String password = faker.internet().password(8, 20);
        return new TestUser(username, email, password);
    }

    // Email без символа @
    public static TestUser generateUserWithoutAtSymbol() {
        String username = generateUsername(12);
        String email = username + "ex.com"; // Без @
        String password = faker.internet().password(8, 20);
        return new TestUser(username, email, password);
    }

    // Email без доменной части
    public static TestUser generateUserWithoutDomain() {
        String username = generateUsername(19); // 19 + 1 = 20
        String email = username + "@"; // 20 символов
        String password = faker.internet().password(8, 20);
        return new TestUser(username, email, password);
    }

    // Email длиннее 20 символов
    public static TestUser generateUserWithEmailTooLong() {
        String domain = "@ex.com";
        int usernameLength = 22 - domain.length();
        String longUsername = faker.lorem().characters(usernameLength);

        return new TestUser(
                faker.name().username(),
                longUsername + domain,
                faker.internet().password(8, 22)
        );
    }

    // Пользователь с пустым именем
    public static TestUser generateUserWithEmptyUsername() {
        String email = generateUsername(12) + "@ex.com";
        String password = faker.internet().password(8, 20);
        return new TestUser("", email, password);
    }

    // Пользователь с пустым email
    public static TestUser generateUserWithEmptyEmail() {
        String username = generateUsername(12);
        String password = faker.internet().password(8, 20);
        return new TestUser(username, "", password);
    }

    // Пользователь с пустым паролем
    public static TestUser generateUserWithEmptyPassword() {
        String username = generateUsername(12);
        String email = username + "@ex.com";
        return new TestUser(username, email, "");
    }

    // Вспомогательный метод для генерации username нужной длины
    private static String generateUsername(int maxLength) {
        String username = faker.name().username()
                .replaceAll("[^a-zA-Z0-9]", "");
        return username.substring(0, Math.min(username.length(), maxLength));
    }

    public static class TestUser {
        public final String username;
        public final String email;
        public final String password;

        public TestUser(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }
    }
}