package GE01;

import com.core.selenium.selenium;

import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TrungNT47 on 2/21/2017.
 */
public class GE01_main {
    public static void main(String[] args){
        Logger log = LoggerFactory.getLogger(GE01_main.class);
        try {
            selenium.startSelenium();
            LogonPage logonPage = new LogonPage();
            logonPage.Login();
            if (logonPage.checkTitle()){
                System.out.println("PASSED: Checking Login Page");
            }
            else{
                System.out.println("FAILED: Could not find expected title");
            }

            log.info("Checking Lands Page");
            Lands lands = new Lands();
            if (lands.checkUserLink()){
                System.out.println("PASSED: CheckLandsUser");
            }
            else{
                System.out.println("FAILED: CheckLandsUser");
            }

            selenium.stopSelenium();
        }catch (IOError Er) {System.out.println(Er);}
    }
}
