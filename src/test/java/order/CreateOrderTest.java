package order;

import api.Diplom_2_utils;
import api.IngredientsApi;
import api.OrderApi;
import api.UserApi;
import dto.*;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateOrderTest extends BestTest{

    private String accessToken;

    @Before
    public void setUp() {

        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        ResponseUser responseUser = response.as(ResponseUser.class);
        accessToken = responseUser.accessToken;

    }

    @Test
    @Description("Создание заказа")
    @DisplayName("C авторизацией")
    @Step
    public void createOrderWithAuth(){
        Response responseingred = IngredientsApi.getIngredients();
        ResponseIngredients responseIngredients = responseingred.as(ResponseIngredients.class);
        Ingredient[] ingredients = responseIngredients.getData();
        String[] IdIngridients = Arrays.asList(
                ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id(),
                ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id())
                .toArray(new String[0]);
        Order order = new Order(IdIngridients);
        Response response1 = OrderApi.createOrderWithAuth(accessToken, order);
        response1.then().log().all()
                .assertThat().body("success", equalTo(true))
                .statusCode(SC_OK);
    }



    @Test
    @Description("Создание заказа")
    @DisplayName("Без авторизации")
    @Step
    public void createOrderWithoutAuth(){
        Response responseingred = IngredientsApi.getIngredients();
        ResponseIngredients responseIngredients = responseingred.as(ResponseIngredients.class);
        Ingredient[] ingredients = responseIngredients.getData();
        String[] IdIngridients = Arrays.asList(
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id(),
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id())
                .toArray(new String[0]);
        Order order = new Order(IdIngridients);
        Response response1 = OrderApi.createOrderWithoutAuth(order);
        response1.then().log().all()
                .assertThat().body("success", equalTo(true))
                .statusCode(SC_OK);
    }


    @Test
    @Description("Создание заказа")
    @DisplayName("Без ингредиентов")
    @Step
    public void createOrderWithoutIngredients(){
        Order order = new Order(null);
        Response response1 = OrderApi.createOrderWithoutAuth(order);
        response1.then().log().all()
                .assertThat().body("message", equalTo("Ingredient ids must be provided"))
                .statusCode(SC_BAD_REQUEST);
    }


    @Test
    @Description("Создание заказа")
    @DisplayName("С неверным хешем ингредиентов")
    @Step
    public void createOrderWithWrongHashes(){
        Response responseingred = IngredientsApi.getIngredients();
        ResponseIngredients responseIngredients = responseingred.as(ResponseIngredients.class);
        Ingredient[] ingredients = responseIngredients.getData();
        String[] IdIngridients = Arrays.asList(
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id()+"1",
                        ingredients[Diplom_2_utils.getRandomInt(ingredients.length)].get_id()+"2")
                .toArray(new String[0]);
        Order order = new Order(IdIngridients);
        Response response1 = OrderApi.createOrderWithAuth(accessToken, order);
        response1.then().log().all()
                .statusCode(SC_INTERNAL_SERVER_ERROR);
    }




    @After
    public void tearDown() {
        try {
            UserApi.deleteUser(accessToken);
        } catch (java.lang.IllegalArgumentException error) {
        }
    }

}
