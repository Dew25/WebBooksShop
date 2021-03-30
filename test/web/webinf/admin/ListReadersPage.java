/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.webinf.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 *
 * @author jvm
 */
public class ListReadersPage {
    protected WebDriver driver;
    private final By tablelistreadersBy = By.id("tableListReaders");
    private final By tagA_By = By.tagName("a");
    public ListReadersPage(WebDriver driver) {
        this.driver = driver;
    }

    public EditUserPage getEditUserPage(String login) {
        WebElement table = driver.findElement(tablelistreadersBy);
        WebElement tr = table.findElement(By.xpath("//tr[td[text()='"+login+"']]"));
        tr.findElement(tagA_By).click();
        return new EditUserPage(driver);
    }

   
    
}
