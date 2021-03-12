package studentsinfo;

import TestBase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;
import serenity.StudentSerenitySteps;

import java.util.ArrayList;

@Concurrent(threads = "4x")
@UseTestDataFrom("testdata/studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentsDataDriven extends TestBase {


    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getCourses() {
        return course;
    }

    public void setCourses(String course) {
        this.course = course;
    }

    public StudentSerenitySteps getStepsDataDriven() {
        return stepsDataDriven;
    }

    public void setStepsDataDriven(StudentSerenitySteps stepsDataDriven) {
        this.stepsDataDriven = stepsDataDriven;
    }

    private String lastName;
    private String email;
    private String programme;
    private String course;

    @Steps
    StudentSerenitySteps stepsDataDriven;


    @Title("Data Driven Test for adding multiple students")
    @Test
    public void createMultipleStudents() {

        ArrayList<String> courses = new ArrayList<>();
        courses.add(course);
        stepsDataDriven.createStudent(firstName, lastName, email, programme, courses).statusCode(201);
    }


}
