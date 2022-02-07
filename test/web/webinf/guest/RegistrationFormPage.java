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
public class RegistrationFormPage {
    protected WebDriver driver;
    private final By firstNameBy = By.id("firstname");
    private final By lastNameBy = By.id("lastname");
    private final By phoneBy = By.id("phone");
    private final By moneyBy = By.id("money");
    private final By loginBy = By.id("login");
    private final By passwordBy = By.id("password");
    private final By registrationButtonBy = By.xpath("//input[@type='submit']");
    public RegistrationFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public IndexPage registerNewUser(String login, String password) {
       
        driver.findElement(firstNameBy).sendKeys("ivan");
        driver.findElement(lastNameBy).sendKeys("ivanov");
        driver.findElement(phoneBy).sendKeys("1111111");
        driver.findElement(moneyBy).sendKeys("1111"); 
        driver.findElement(loginBy).sendKeys(login);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(registrationButtonBy).click();
        return new IndexPage(driver);
    }
    
}
