package com.tung.example.tests;

import com.tung.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/auth/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.login("valid@email.com", "validPassword");
        // Chờ chuyển trang hoặc xuất hiện phần tử đặc trưng sau đăng nhập
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("invalid@email.com", "wrongPassword");
        // Chờ thông báo lỗi xuất hiện
        boolean isError = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getPageSource().contains("Email hoặc mật khẩu không đúng"));
        Assert.assertTrue(isError);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
