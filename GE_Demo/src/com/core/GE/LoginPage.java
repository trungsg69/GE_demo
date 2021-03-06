package com.core.GE;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;

import java.io.*;
import java.util.Properties;

/**
 * Created by TrungNT47 on 1/12/2017.
 */
public class LoginPage {
    private static final Properties m_properties = new Properties();
    private static WebDriver driver;

    private static  String APP_URL = "";
    private static  String USER = "";
    private static  String PASSWORD = "";
    private static  String EXPECTED_TITLE = "";


    public String getUser() {
        return USER;
    }

    public String getPassword() {
        return PASSWORD;
    }



    private static void Login() {

        try {
            driver = new ChromeDriver();
            System.out.println("link = " + APP_URL);
            driver.get(APP_URL);

            TimeUnit.SECONDS.sleep(10);

            try {
                WebElement username = driver.findElement(By.name("username"));
                username.clear();
                username.sendKeys(USER);

                WebElement password = driver.findElement(By.name("password"));
                password.clear();
                password.sendKeys(PASSWORD);

                WebElement SignInButton = driver.findElement(By.xpath("//input[@value='Sign in']"));
                SignInButton.click();

                TimeUnit.SECONDS.sleep(5);
            } catch (NoSuchElementException E1) {
                System.out.println("NoSuchElementException occurs : " + E1);
            }

        } catch (InterruptedException E2) {
            System.out.println("InterruptedException occurs : " + E2);
        }
    }


    // click on the Sign in button
    public boolean isSuccessful() {
        //loadSettingsFromFile(filePath);
        Login();
        if (EXPECTED_TITLE.equals(driver.getTitle()))
            return true;
        else
            return false;
    }

    private static void loadPropertiesFromFile(final String filePath) throws IOException {
        try (final Reader reader = new FileReader(filePath)) {
            m_properties.load(reader);
        } catch (final IOException e) {
            throw e;
        }
    }

    public static String getValueProperties(String name){
        return m_properties.getProperty(name);
    }

    public static void setParameters(String filePath)throws IOException{
        try {
            loadPropertiesFromFile(filePath);
            APP_URL = m_properties.getProperty("app.link");
            USER = m_properties.getProperty("page.login.user");
            PASSWORD = m_properties.getProperty("page.login.passowrd");
            EXPECTED_TITLE = m_properties.getProperty("page.login.title");
            System.out.println("Your parameters ");
            System.out.println("app.link = " + APP_URL);
            System.out.println("page.login.user = " + USER);
            System.out.println("page.login.passowrd= " + PASSWORD);
            System.out.println("page.login.title= " + EXPECTED_TITLE);
        }catch (IOException e){throw e;}
    }
}