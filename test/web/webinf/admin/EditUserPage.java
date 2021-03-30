/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.webinf.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.IndexPage;

/**
 *
 * @author jvm
 */
public class EditUserPage {
     protected WebDriver driver;
     private final By buttonsubmitBy = By.xpath("//input[@type='submit']");
    
    public EditUserPage(WebDriver driver) {
        this.driver = driver;
    }
    public IndexPage changeUserProfile(){
        driver.findElement(buttonsubmitBy).click();
        return new IndexPage(driver);
    }
    
}
