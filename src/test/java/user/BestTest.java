package user;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

public class BestTest {
    @Before
    public void setUP(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }
}
