package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegistrationPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Form Tests using Page Object Model")
public class RegistrationTest extends BaseTest {
    static WebDriverWait wait;
    static RegistrationPage registrationPage;

    @BeforeAll
    static void initPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Should register successfully with valid data")
    void testRegistrationSuccess() {
        registrationPage.navigate();
        registrationPage.fillForm("John", "Doe", "john.doe@example.com", "Male", "1234567890");
        registrationPage.submitForm();
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getSuccessLocator()));
        assertTrue(success.getText().contains("Thanks for submitting the form"));
    }

    @Test
    @Order(2)
    @DisplayName("Should show error for invalid email")
    void testRegistrationInvalidEmail() {
        registrationPage.navigate();
        registrationPage.fillForm("John", "Doe", "invalid-email", "Male", "1234567890");
        registrationPage.submitForm();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(registrationPage.getErrorLocator()));
        assertTrue(error.isDisplayed());
    }

    @ParameterizedTest(name = "CSV Inline: {0} / {1} / {2} / {3} / {4}")
    @Order(3)
    @CsvSource({
            "John, Doe, john.doe@example.com, Male, 1234567890, success",
            "Jane, Smith, invalid-email, Male, 1234567890, error",
            ", , , Male, , error"
    })
    void testRegistrationCsvInline(String firstName, String lastName, String email, String gender, String mobile, String expected) {
        registrationPage.navigate();
        firstName = (firstName == null) ? "" : firstName.trim();
        lastName = (lastName == null) ? "" : lastName.trim();
        email = (email == null) ? "" : email.trim();
        mobile = (mobile == null) ? "" : mobile.trim();

        registrationPage.fillForm(firstName, lastName, email, gender, mobile);
        registrationPage.submitForm();
        By resultLocator = expected.equals("success") ? registrationPage.getSuccessLocator() : registrationPage.getErrorLocator();
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));

        if (expected.equals("success")) {
            assertTrue(result.getText().contains("Thanks for submitting the form"));
        } else {
            assertTrue(result.isDisplayed());
        }
    }

    @ParameterizedTest(name = "CSV File: {0} / {1} / {2} / {3} / {4}")
    @Order(4)
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    void testRegistrationFromCSV(String firstName, String lastName, String email, String gender, String mobile, String expected) {
        registrationPage.navigate();
        firstName = (firstName == null) ? "" : firstName.trim();
        lastName = (lastName == null) ? "" : lastName.trim();
        email = (email == null) ? "" : email.trim();
        mobile = (mobile == null) ? "" : mobile.trim();

        registrationPage.fillForm(firstName, lastName, email, gender, mobile);
        registrationPage.submitForm();
        By resultLocator = expected.equals("success") ? registrationPage.getSuccessLocator() : registrationPage.getErrorLocator();
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));

        if (expected.equals("success")) {
            assertTrue(result.getText().contains("Thanks for submitting the form"));
        } else {
            assertTrue(result.isDisplayed());
        }
    }
}
