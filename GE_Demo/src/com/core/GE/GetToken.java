package com.core.GE;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class GetToken{
    String sWebLink = "ht   tps://predix-starter.run.aws-usw02-pr.ice.predix.io/#!/apiLoginClient";
    String sUser="predix_client";
    String sPassword="IM_SO_SECRET";

    public static String getToken(){
        String sToken ="";
        try{
            WebDriver driver = new ChromeDriver();
            String sUserLink = "https://481c1d00-4ac3-4bb3-a65c-207606a97941.predix-uaa.run.aws-usw02-pr.ice.predix.io";
            TextTransfer textTransfer = new TextTransfer();

            textTransfer.setClipboardContents(sUserLink);

            driver.get("https://predix-starter.run.aws-usw02-pr.ice.predix.io/#!/apiLoginClient");
            TimeUnit.SECONDS.sleep(5);

            List<WebElement> UserLink = driver.findElements(By.xpath("//input[@name='uaaUrlInput' and @class='style-scope client-login-form']"));
            System.out.print("String  " + UserLink.get(1).getText());
            UserLink.get(1).clear();
            UserLink.get(1).sendKeys(Keys.CONTROL,"v");

            //WebElement username = driver.findElement(By.name("clientId"));
            List<WebElement> username=driver.findElements(By.xpath("//input[@name='clientId' and @class='style-scope client-login-form']"));
            username.get(1).clear();
            username.get(1).sendKeys("predix_client");

            //WebElement password = driver.findElement(By.name("clientSecret"));
            List<WebElement> password = driver.findElements(By.xpath("//input[@name='clientSecret' and @class='style-scope client-login-form']"));
            password.get(1).clear();
            password.get(1).sendKeys("IM_SO_SECRET");

            List<WebElement> btnSubmit = driver.findElements(By.id("clientLoginButton"));
            btnSubmit.get(1).click();

            //List<WebElement> output= driver.findElements(By.xpath("//*[@class='string style-scope pretty-json']"));
            List<WebElement> output= driver.findElements(By.xpath("//pre[@id='output' and @class='style-scope pretty-json']"));
            System.out.println("output sie: " + output.size());

            for (int i=0 ; i< output.size(); i++) {
                if (output.get(i).getText().contains("access_token")){
                    sToken = output.get(i).getText();
                    System.out.println("Element " +i + " : " + sToken);
                }
            }
            //int firstIndex = "\"access_token\": \"".length();
            int firstIndex = 21;
            int lastIndex = sToken.indexOf(",") - 1;
            /*System.out.println("first index = " + firstIndex);
            System.out.println("last index = " + lastIndex);*/

            sToken = sToken.substring(firstIndex,lastIndex);
            System.out.println("after cut " + sToken);
            sToken = "bearer " + sToken;

            driver.quit();
        }catch (Exception Errs){System.out.println(Errs);}
        finally{}

        return sToken;
    }

    public static void main(String[] args) throws InterruptedException  {
        System.out.println("Token is: " + getToken());
    }
}
