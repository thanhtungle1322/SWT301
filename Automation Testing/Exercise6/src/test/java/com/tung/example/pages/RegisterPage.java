package com.tung.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    WebDriver driver;

    By emailField = By.cssSelector("input[type='email'][placeholder='Nhập email']");
    By registerButton = By.cssSelector("button[type='submit']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    public void register(String email) {
        enterEmail(email);
        clickRegister();
    }
}