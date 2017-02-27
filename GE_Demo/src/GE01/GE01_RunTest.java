package GE01;

import com.core.selenium.selenium;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by TrungNT47 on 2/24/2017.
 */
public class GE01_RunTest {

    static Logger log = LoggerFactory.getLogger(GE01_RunTest.class);

    @BeforeTest
    public void BeforeTest(){
        log.info("Start Selenium");
        selenium.startSelenium();
    }

    @Test
    public void CheckLogin(){
        log.info("Checking Login Page");
        LogonPage logonPage = new LogonPage();
        logonPage.Login();
        if (logonPage.checkTitle()){
           log.info("PASSED: Checking Login Page");
        }
        else{
            log.info("FAILED: Could not find expected title");
        }
    }

    @Test
    public void CheckLandsUser(){
        log.info("Checking Lands Page");
        Lands lands = new Lands();
        if (lands.checkUserLink()){
            log.info("PASSED: CheckLandsUser");
        }
        else{
            log.info("FAILED: CheckLandsUser");
        }
    }

    @AfterTest
    public void AfterTest(){
        log.info("Stop Selenium");
        selenium.stopSelenium();
    }
}
