/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.general;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import web.webinf.menu.MenuPage;

/**
 *
 * @author jvm
 */
public class GeneralTest {
    static private WebDriver driver;
    private final MenuPage menuPage = new MenuPage(driver);
    public GeneralTest() {
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
    
    
    @Test
    public void lostOfControlTest(){
        System.out.println("LostOfControlTest: ");
        loginUserTest("admin", "12345");
        changeRoleTest(false);
    }
    
    @Test
    public void desableAccessToAdminProfile(){
        System.out.println("DesableAccessToAdminProfileTest: ");
        logoutTest();
        loginUserTest("ivan", "123");
        changeUserProfile("admin", Boolean.FALSE);
    }    
    
    public void loginUserTest(String login, String password){
        System.out.println("loginUserTest: ");
        String result = menuPage.getLoginFormPage().loginValidUser(login,password).getMessageInfo();
        String expected = "Вы вошли как "+login;
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        Assert.assertEquals(result, expected);
    }
        
    public void changeRoleTest(boolean allowed){
            System.out.println("changeRoleTest("+allowed+"):");
            menuPage.getAdminFormPage().validLostAccess();
            String result = menuPage.getMessageInfo();
            String expected;
        if(allowed){
            expected = "Роль изменена";
        }else{
            expected = "Изменить роль невозможно";
        }
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        Assert.assertEquals(result, expected);
    }
    public void logoutTest(){
        System.out.println("logoutTest: ");
        menuPage.logout();
        String result = menuPage.getMessageInfo().trim();
        String expected = "Вы вышли";
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        Assert.assertEquals(result, expected);
    }
    
    public void changeUserProfile(String login, boolean allowed){
        System.out.println("changeUserProfile("+login+","+allowed+"): ");
        menuPage.getListReadersPage().getEditUserPage(login).changeUserProfile();
        String result = menuPage.getMessageInfo().trim();
        String expected = "Данные пользователя изменены";
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        if(allowed){ 
            System.out.println("Expected и Result должны совпадать");
           Assert.assertEquals(result, expected);
        }else{
           System.out.println("Expected и Result должны несовпадать");
           Assert.assertNotEquals(result, expected);
        }
    }
    
}
