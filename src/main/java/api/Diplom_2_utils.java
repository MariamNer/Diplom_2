package api;

import dto.User;
import org.apache.commons.lang3.RandomStringUtils;

public class Diplom_2_utils {
    public static User getRandom() {
        final String email = RandomStringUtils.randomAlphabetic(12) + "@yandex.ru";
        final String password = RandomStringUtils.randomAlphabetic(12);
        final String name = RandomStringUtils.randomAlphabetic(12);
        return new User(email, password, name);
    }

}
