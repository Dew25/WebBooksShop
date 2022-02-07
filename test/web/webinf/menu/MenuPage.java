/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.webinf.menu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.webinf.admin.AdminPanelPage;
import web.webinf.admin.ListReadersPage;
import web.webinf.guest.LoginFormPage;

/**
 *
 * @author jvm
 */
public class MenuPage {
    protected WebDriver driver;
    private final By infoBy = By.id("info");
    private final By loginformBy = By.id("loginForm");
    private final By adminformBy = By.id("adminForm");
    private final By listReadersBy = By.id("adminForm");
    private final By logoutBy = By.id("logout");
    private final By registrationBy = By.id("registrationForm");
    public MenuPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public String getMessageInfo(){
        return driver.findElement(infoBy).getText();
    }
    
    public LoginFormPage getLoginFormPage(){
        driver.findElement(loginformBy).click();
        return new LoginFormPage(driver);
    }

    public AdminPanelPage getAdminFormPage() {
        driver.findElement(adminformBy).click();
        return new AdminPanelPage(driver);
    }

    public ListReadersPage getListReadersPage() {
        driver.findElement(By.id("listReaders")).click();
        return new ListReadersPage(driver);
    }

    public void logout() {
        driver.findElement(logoutBy).click();
    }
    public void registrationForm(){
        driver.findElement(registrationBy).click();
    }
}
