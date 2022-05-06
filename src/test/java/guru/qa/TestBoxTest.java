package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("demoqa")
public class TestBoxTest extends TestBase {

    @Test
    @DisplayName("Successful fill registration test")
    void fillFormTest(){

        step("Open registration form", () -> {
            open("/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
            executeJavaScript("$('footer').remove()");
            executeJavaScript("$('#fixedban').remove()");
        });
        step("Fill registration form",() -> {
            $("#firstName").setValue("Fedor");
            $("#lastName").setValue("Bobrov");
            $("#userEmail").setValue("fedor@bobrov.com");
            $("#genterWrapper").$(byText("Other")).click();
            $("#userNumber").setValue("88009008899");
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("February");
            $(".react-datepicker__year-select").selectOption("1980");
            $(".react-datepicker__day--013:not(.react-datepicker__day--outside-month)").click();
            $("#subjectsInput").setValue("Math").pressEnter();
            $("#hobbiesWrapper").$(byText("Sports")).click();
            $("#uploadPicture").uploadFromClasspath("vodorosli_rastenie_makro_krupnym_planom_106345_1920x1080.jpg");
            $("#currentAddress").setValue("RF Street 4");
            $("#state").click();
            $("#stateCity-wrapper").$(byText("NCR")).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText("Delhi")).click();
            $("#submit").click();
        });
        step("Verify form data", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Fedor Bobrov"), text("Fedor@Bobrov.com"), text("Other"));
        });
    }
}