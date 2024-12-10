package user;

import api.Diplom_2_utils;
import api.UserApi;
import dto.LoginUser;
import dto.ResponseUser;
import dto.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Test;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.hamcrest.Matchers.equalTo;

public class ChangeParametersUserTest extends BestTest {

    private String accessToken;

    @Test
    @Description("Изменение данных пользователя")
    @DisplayName("С авторизацией")
    @Step
    public void ChangeUserEmailWithAuth(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        Response response1 = UserApi.loginUser(new LoginUser(user.email, user.password));
        response1.then().log().all();
        ResponseUser responseUser = response1.as(ResponseUser.class);
        String accessToken = responseUser.accessToken;
        User changeUserEmail = new User(user.email + "a", user.password, user.name);
        Response response2 = UserApi.updateUser(accessToken, changeUserEmail);
        response2.then().log().all()
                .assertThat().body("success", equalTo(true))
                .and()
                .assertThat().body("user.email", equalTo(user.email.toLowerCase() + "a"))
                .statusCode(SC_OK);
    }

    @Test
    @Description("Изменение данных пользователя")
    @DisplayName("С авторизацией")
    @Step
    public void ChangeUserPasswordWithAuth(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        Response response1 = UserApi.loginUser(new LoginUser(user.email, user.password));
        response1.then().log().all();
        ResponseUser responseUser = response1.as(ResponseUser.class);
        String accessToken = responseUser.accessToken;
        User changeUserPassword = new User(user.email, user.password + "a", user.name);
        Response response2 = UserApi.updateUser(accessToken, changeUserPassword);
        response2.then().log().all()
                .assertThat().body("success", equalTo(true))
                .and()
                .assertThat().body("user.email", equalTo(user.email.toLowerCase()))
                .and()
                .assertThat().body("user.name", equalTo(user.name))
                .statusCode(SC_OK);
    }

    @Test
    @Description("Изменение данных пользователя")
    @DisplayName("С авторизацией")
    @Step
    public void ChangeUserNameWithAuth(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        Response response1 = UserApi.loginUser(new LoginUser(user.email, user.password));
        response1.then().log().all();
        ResponseUser responseUser = response1.as(ResponseUser.class);
        String accessToken = responseUser.accessToken;
        User changeUserName = new User(user.email, user.password, user.name + "a");
        Response response2 = UserApi.updateUser(accessToken, changeUserName);
        response2.then().log().all()
                .assertThat().body("success", equalTo(true))
                .and()
                .assertThat().body("user.email", equalTo(user.email.toLowerCase()))
                .and()
                .assertThat().body("user.name", equalTo(user.name + "a"))
                .statusCode(SC_OK);
    }

    @Test
    @Description("Изменение данных пользователя")
    @DisplayName("Без авторизацией")
    @Step
    public void ChangeUserNameWithoutAuth(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        ResponseUser responseUser = response.as(ResponseUser.class);
//        String accessToken = responseUser.accessToken;
        User changeUserName = new User(user.email, user.password, user.name + "a");
        Response response2 = UserApi.updateUser("", changeUserName);
        response2.then().log().all()
                .assertThat().body("message", equalTo("You should be authorised"))
                .and()
                .assertThat().body("success", equalTo(false))
                .statusCode(SC_UNAUTHORIZED);
    }

    @Test
    @Description("Изменение данных пользователя")
    @DisplayName("Без авторизацией")
    @Step
    public void ChangeUserPasswordWithoutAuth(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        ResponseUser responseUser = response.as(ResponseUser.class);
//        String accessToken = responseUser.accessToken;
        User changeUserPassword = new User(user.email, user.password + "a", user.name);
        Response response2 = UserApi.updateUser("", changeUserPassword);
        response2.then().log().all()
                .assertThat().body("message", equalTo("You should be authorised"))
                .and()
                .assertThat().body("success", equalTo(false))
                .statusCode(SC_UNAUTHORIZED);
    }


    @Test
    @Description("Изменение данных пользователя")
    @DisplayName("Без авторизацией")
    @Step
    public void ChangeUserEmailWithoutAuth(){
        User user = Diplom_2_utils.getRandom();
        Response response = UserApi.createUser(user);
        response.then().log().all();
        ResponseUser responseUser = response.as(ResponseUser.class);
//        String accessToken = responseUser.accessToken;
        User changeUserEmail = new User(user.email + "a", user.password, user.name);
        Response response2 = UserApi.updateUser("", changeUserEmail);
        response2.then().log().all()
                .assertThat().body("message", equalTo("You should be authorised"))
                .and()
                .assertThat().body("success", equalTo(false))
                .statusCode(SC_UNAUTHORIZED);
    }




    @After
    public void tearDown(){
        try{
            UserApi.deleteUser(accessToken);
        } catch(java.lang.IllegalArgumentException error) {
        }
    }


}
