/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author jvm
 */
public class IndexPage {
    protected WebDriver driver;
    private final By infoBy = By.id("info");
    public IndexPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getMessageInfo(){
        return driver.findElement(infoBy).getText();
    }
}
