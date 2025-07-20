package com.tung.example.tests;

import com.tung.example.pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;A

public class RegisterTest {
    WebDriver driver;
    RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/auth/user-register");
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testValidRegister() {
        String email = "test" + System.currentTimeMillis() + "@mail.com";
        registerPage.register(email);
        // Chờ toast thành công xuất hiện (React-Toastify)
        By toastSuccess = By.cssSelector(".Toastify__toast--success .Toastify__toast-body");
        String successText = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(toastSuccess))
                .getText();
        Assert.assertTrue(successText.contains("Gửi mã OTP thành công"));
    }

    @Test
    public void testRegisterWithExistingEmail() {
        registerPage.register("existing@email.com");
        // Chờ toast lỗi xuất hiện (React-Toastify)
        By toastError = By.cssSelector(".Toastify__toast--error .Toastify__toast-body");
        String errorText = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(toastError))
                .getText();
        // Có thể là "Gửi mã OTP thất bại" hoặc thông báo khác, hãy kiểm tra thực tế
        Assert.assertTrue(
                errorText.contains("Gửi mã OTP thất bại") || errorText.contains("Email đã tồn tại")
        );
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}