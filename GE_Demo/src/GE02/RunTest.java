package GE02;

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
public class RunTest {

    static Logger log = LoggerFactory.getLogger(RunTest.class);

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

    @AfterTest
    public void AfterTest(){
        log.info("Stop Selenium");
        selenium.stopSelenium();
    }
}
