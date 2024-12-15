package order;

import api.Diplom_2_utils;
import api.IngredientsApi;
import api.OrderApi;
import api.UserApi;
import dto.*;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Test;

import java.util.Arrays;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

public class OrdersTest extends BestTest{

    private String accessToken;

    @Test
    @DisplayName("Получение заказов конкретного пользователя")
    @Description("Авторизованный пользователь")
    @Step
    public void getOrderForUserWithAuth() {
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;
        Response responseingred = IngredientsApi.getIngredients();
        ResponseIngredients responseIngredients = responseingred.as(ResponseIngredients.class);
        Ingredient[] ingredients = responseIngredients.getData();
        String[] IdIngridients = Arrays.asList(
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id(),
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id())
                .toArray(new String[0]);
        Order order = new Order(IdIngridients);
        OrderApi.createOrderWithAuth(accessToken, order);
        Response response1 = OrderApi.getOrderForUserWithAuth(accessToken);
            response1.then().log().all()
                .assertThat().body("success", equalTo(true))
                .statusCode(SC_OK);

    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя")
    @Description("Неавторизованный пользователь")
    @Step
    public void getOrderForUserWithoutAuth() {
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;
        Response responseingred = IngredientsApi.getIngredients();
        ResponseIngredients responseIngredients = responseingred.as(ResponseIngredients.class);
        Ingredient[] ingredients = responseIngredients.getData();
        String[] IdIngridients = Arrays.asList(
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id(),
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id())
                .toArray(new String[0]);
        Order order = new Order(IdIngridients);
        OrderApi.createOrderWithoutAuth(order);
        Response response1 = OrderApi.getOrderWithoutAuth();
        response1.then().log().all()
                .assertThat().body("message", equalTo("You should be authorised"))
                .statusCode(SC_UNAUTHORIZED);


    }

    @After
    public void tearDown() {
        UserApi.deleteUser(accessToken);
    }
}
