package serenity;

import Model.StudentClass;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.yecht.Data;
import utils.ReuseableSpecifications;

import java.util.HashMap;
import java.util.List;

public class StudentSerenitySteps {

    @Step("Creating a student with firstName:{0}, lastName:{1}, email:{2}, programme;{3}, courses:{4}")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courses){

        StudentClass student = new StudentClass();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setProgramme(programme);
        student.setCourses(courses);

        return  SerenityRest.rest()
                .given()
                //.contentType(ContentType.JSON)
                .spec(ReuseableSpecifications.getGenericRequestSpec())
                .when()
                .body(student)
                .post()
                .then();
    }

    @Step("Get Student Info by First Name:{0}")
    public HashMap<String,Object> getStudentInfoByFirstName(String firstName){

        // return a HashMap<String,Object>
        return SerenityRest.rest().given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path("findAll{it.firstName=='"+ firstName+ "'}.get(0)");
    }

    @Step("Get Student Info by student Id:{0}")
    public ValidatableResponse getStudentInfoByStudentId(int studentId) {

       return  SerenityRest.rest()
                .given()
                .when()
                .get("/" + studentId)
                .then();
    }

    @Step("Updating a student with studentId:{0},  firstName:{1}, lastName:{2}, email:{3}, programme;{4}, courses:{5}")
    public ValidatableResponse updateStudent(int studentId, String firstName, String lastName, String email, String programme, List<String> courses){

            StudentClass student = new StudentClass();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            student.setEmail(email);
            student.setProgramme(programme);
            student.setCourses(courses);

            return  SerenityRest.rest()
                    .given()
                    //.contentType(ContentType.JSON)
                    .spec(ReuseableSpecifications.getGenericRequestSpec())
                    .when()
                    .body(student)
                    .put("/" + studentId)
                    .then();

        }

        @Step("Delete A student by studentId")
    public void deleteStudent(int studentId){
            SerenityRest.rest()
                .given()
                .when()
                .delete("/" + studentId);

    }
}
