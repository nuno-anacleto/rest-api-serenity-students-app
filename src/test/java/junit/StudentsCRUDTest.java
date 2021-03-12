package junit;

import Model.StudentClass;
import TestBase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.yecht.Data;
import serenity.StudentSerenitySteps;
import utils.ReuseableSpecifications;
import utils.TestUtils;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Assert.*;


import java.util.ArrayList;
import java.util.HashMap;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest  extends TestBase {


    static String firstName = "SMOKEUSER2";
    static String lastName = "SMOKEUSER2";
    static String programme = "ComputerScience";
    static String email = TestUtils.getRandomValue() + "xpty_xpto@gmail.com";
    static int studentId;

    @Steps
    StudentSerenitySteps steps;

    @Title("This test will create a new student")
    @Test
    public void T_001_createStudent(){

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        steps.createStudent(firstName, lastName, email, programme, courses)
                .statusCode(201)
                .spec(ReuseableSpecifications.getGenericResponseSpec());

    }

    @Test
    public void T_002_getAStudent() {

        HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);

        assertThat(value,hasValue(firstName));
        studentId = (int) value.get("id");
    }

    @Test
    public void T_003_update_student(){

        ArrayList<String> courses = new ArrayList<String>();
        courses.add("Java");
        courses.add("C++");

        firstName = firstName + "_Updated";
        steps.updateStudent(studentId, firstName, lastName, email, programme, courses);

        HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
        assertThat(value, hasValue(firstName));
    }

    @Title("Get infotmation of the student Id")
    @Test
    public void T_004_deleteAStudent(){

        steps.deleteStudent(studentId);
        steps.getStudentInfoByStudentId(studentId).statusCode(404);
    }

}
