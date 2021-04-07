/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.webinf.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author jvm
 */
public class AdminPanelPage {
    protected WebDriver driver;
    private final By infoBy = By.id("info");
    private final By useridselectBy = By.name("userId");
    private final By roleidselectBy = By.name("roleId");
    private final By buttonsubmitBy = By.xpath("//input[@type='submit']");
    
    public AdminPanelPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getMessageInfo(){
        return driver.findElement(infoBy).getText();
    }
    
    public void validLostAccess(){
       Select selectUsers = new Select(driver.findElement(useridselectBy)); 
       selectUsers.selectByVisibleText("Juri Melnikov, логин: admin, роль: ADMIN");
       Select selectRoles = new Select(driver.findElement(roleidselectBy)); 
       selectRoles.selectByVisibleText("READER");
       driver.findElement(buttonsubmitBy).click();
    }
    
}
