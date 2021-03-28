/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author jvm
 */
public class AdminLoginTest {
    static private WebDriver driver;
    public AdminLoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/WebBooksShop/");
    }
    
    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void login() {
         System.out.println("Admin login test");
         WebElement el = driver.findElement(By.id("loginForm"));
         el.click();
         el = driver.findElement(By.id("login"));
         el.sendKeys("admin");
         el = driver.findElement(By.id("password"));
         el.sendKeys("12345");
         el = driver.findElement(By.xpath("//input[@type='submit']"));
         el.click();
         el = driver.findElement(By.id("info"));
         System.out.println("Ожидается: Вы вошли как admin");
         System.out.println("Выводится: "+el.getText());
         Assert.assertEquals("Вы вошли как admin", el.getText());
     }
}
