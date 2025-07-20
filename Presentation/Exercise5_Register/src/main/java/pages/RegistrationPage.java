package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(String firstName, String lastName, String email, String gender, String mobile) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.xpath("//label[text()='" + gender + "']")).click();
        driver.findElement(By.id("userNumber")).sendKeys(mobile);
    }

    public void submitForm() {
        driver.findElement(By.id("submit")).click();
    }

    public By getSuccessLocator() {
        return By.id("example-modal-sizes-title-lg"); // tiêu đề của modal sau khi submit thành công
    }

    public By getErrorLocator() {
        // Tạm thời giả định dùng ID của email nếu invalid (do form không rõ ràng lỗi)
        return By.cssSelector(".was-validated .form-control:invalid");
    }
}
