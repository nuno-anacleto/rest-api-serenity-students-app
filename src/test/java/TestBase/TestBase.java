package TestBase;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import org.junit.BeforeClass;

public class TestBase {


    @BeforeClass
    public static void init(){
        RestAssured.baseURI = "http://localhost:8005/student";
    }
}
