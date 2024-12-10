package api;

import dto.Order;
import dto.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class Diplom_2_utils {
    public static User getRandom() {
        final String email = RandomStringUtils.randomAlphabetic(12) + "@yandex.ru";
        final String password = RandomStringUtils.randomAlphabetic(12);
        final String name = RandomStringUtils.randomAlphabetic(12);
        return new User(email, password, name);
    }

    public static Integer getRandomInt(Integer max) {
        return RandomUtils.nextInt(0, max-1);
    }

}
