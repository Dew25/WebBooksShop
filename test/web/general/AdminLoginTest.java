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
import web.IndexPage;
import web.webinf.admin.AdminPanelPage;
import web.webinf.admin.EditUserPage;
import web.webinf.admin.ListReadersPage;
import web.webinf.guest.LoginFormPage;
import web.webinf.menu.MenuPage;

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
    @Test
    public void lostOfControlTest(){
        System.out.print("LostOfControlTest: ");
        MenuPage menuPage = new MenuPage(driver);
        LoginFormPage loginFormPage = menuPage.getLoginFormPage();
        IndexPage indexPage = loginFormPage.loginValidUser("admin","12345");
        if(!"Вы вошли как admin".equals(indexPage.getMessageInfo())){
            Assert.fail("Ошибка входа!");
        }
        AdminPanelPage adminPanelPage = menuPage.getAdminFormPage();
        String massage = adminPanelPage.validLostAccess();
        if(!"Изменить роль невозможно".equals(massage)){
            Assert.fail("Ошибка в тесте понижения роли админу");
        }
        System.out.println("пройден.");
        desableAccessToAdminProfile();
    }
    
    public void desableAccessToAdminProfile(){
        System.out.print("desableAccessToAdminProfileTest: ");
        MenuPage menuPage = new MenuPage(driver);
        menuPage.logout();
        LoginFormPage loginFormPage = menuPage.getLoginFormPage();
        loginFormPage.loginValidUser("ivan","123");
        ListReadersPage listReadersPage = menuPage.getListReadersPage();
        EditUserPage editUserPage = listReadersPage.getEditUserPage("admin");
        IndexPage indexPage = editUserPage.changeUserProfile();
        String massage = indexPage.getMessageInfo();
        Assert.assertEquals("Вы не имеете прав на изменение данных этого пользователя", massage);
        System.out.println("пройден.");
    }    
}
