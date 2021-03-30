/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.webinf.guest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.IndexPage;

/**
 *
 * @author jvm
 */
public class LoginFormPage {
    protected WebDriver driver;
    private final By loginBy = By.id("login");
    private final By passwordBy = By.id("password");
    private final By loginButtonBy = By.xpath("//input[@type='submit']");
    public LoginFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public IndexPage loginValidUser(String login, String password) {
        driver.findElement(loginBy).sendKeys(login);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(loginButtonBy).click();
        return new IndexPage(driver);
    }
    
}
