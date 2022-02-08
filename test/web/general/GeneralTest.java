/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web.general;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.IndexPage;
import web.webinf.guest.RegistrationFormPage;
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
    public static void setUpClass() throws UnsupportedEncodingException {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8080/WebBooksShop/");
        driver.manage().window().maximize();
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
    /*
        Проверка потери управляемости приложения 
        (когда пользовательский ADMIN понижает права суперадмина)
    */
    @Test
    public void lostOfControlTest(){
        System.out.println("LostOfControlTest: ");
        loginUser("admin", "12345");
        changeRole(false, "READER", "1");
        logout();
    }
    /*
        Проверка на изменение профайла суперадмина пользовательским ADMIN
    */
    @Test
    public void desableAccessToAdminProfileTest(){
        System.out.println("DesableAccessToAdminProfileTest: ");
        registrationNewUser("ivan", "123");
        setRoleToUser("ADMIN", "2");
        logout();
        loginUser("ivan", "123");
        changeUserProfile("admin", Boolean.FALSE);
        logout();
    }    
    
    public void loginUser(String login, String password){
        System.out.println("loginUserTest: ");
        String result = menuPage.getLoginFormPage().loginValidUser(login,password).getMessageInfo();
        String expected = "Вы вошли как "+login;
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        Assert.assertEquals(result, expected);
    }
        
    public void changeRole(boolean onChange, String roleName, String userId){
            System.out.println("changeRoleTest("+onChange+" "+roleName+" "+userId+"):");
            menuPage.getAdminFormPage().setRoleToUser(roleName, userId);
            String result = menuPage.getMessageInfo();
            String expected;
        if(onChange){
            expected = "Роль изменена";
        }else{
            expected = "Изменить роль невозможно";
        }
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        System.out.println("Expected и Result должны совпадать");
        Assert.assertEquals(result, expected);
    }
    
    public void logout(){
        System.out.println("logoutTest: ");
        menuPage.logout();
        String result = menuPage.getMessageInfo();
        String expected = "Вы вышли";
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        Assert.assertEquals(result, expected);
    }
    
    public void changeUserProfile(String login, boolean allowed){
        System.out.println("changeUserProfile("+login+","+allowed+"): ");
        menuPage.getListReadersPage().getEditUserPage(login).changeUserProfile();
        String result = menuPage.getMessageInfo();
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
    
    public void registrationNewUser(String login,String password){
        System.out.println("registrationNewUser("+login+","+password+"): ");
        menuPage.registrationForm();
        RegistrationFormPage registrationFormPage = new RegistrationFormPage(driver);
        IndexPage indexPage = registrationFormPage.registerNewUser(login, password);
        String result = indexPage.getMessageInfo();
        String expected = "Что-то пошло не так :(";
        System.out.println("    Expected: "+ expected);
        System.out.println("    Result: " + result);
        System.out.println("Expected и Result должны несовпадать");
        Assert.assertNotEquals(result, expected);
    }
    
    public void setRoleToUser(String roleName, String userId){
        loginUser("admin","12345");
        changeRole(true, roleName, "2");
    }
    
}
