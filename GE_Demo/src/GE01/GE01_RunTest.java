package GE01;

import com.core.selenium.selenium;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by TrungNT47 on 2/24/2017.
 */
public class GE01_RunTest {

    static Logger log = LoggerFactory.getLogger(GE01_RunTest.class);
    LogonPage logonPage = new LogonPage();
    LandsPage lands = new LandsPage();
    BlocksPage blocks = new BlocksPage();
    BlockDetail blockDetail = new BlockDetail();

    @BeforeTest
    public void BeforeTest(){
        log.info("Start Selenium");
        selenium.startSelenium();
    }

    @Test (priority=1)
    public void CheckLogin(){
        log.info("Checking Login Page");
        log.info("Checking CheckLogin");
        logonPage.Login();
        log.info("CheckLogin result is " + logonPage.checkTitle());

    }


    @Test (priority=2)
    public void CheckLoginTitle(){
        log.info("Checking CheckLoginTitle");
        log.info("CheckLoginTitle result is " + logonPage.checkTitle());
    }

    //@Test (dependsOnMethods={"CheckLogin"})
    @Test (priority=3)
    public void CheckLandsTitle(){
        log.info("Checking Lands Page");
        log.info("Checking CheckLandsTitle");
        log.info("CheckLandsTitle result is " + lands.checkTitle());
    }


    @Test (priority=4)
    public void CheckLandsUser(){
        log.info("Checking CheckLandsUser");
        log.info("CheckLandsUser result is " + lands.checkUserLink());
    }

    @Test (priority=5)
    public void CheckBlocksTitle(){
        log.info("Checking Blocks Page");
        log.info("Checking CheckBlocksTitle");
        log.info("CheckBlocksTitle result is " + blocks.checkTitle());
    }

    @Test (priority=6)
    public void CheckBlocksDetail(){
        log.info("Checking CheckBlocksTitle");
        log.info("CheckBlocksTitle result is " + blocks.checkBlockDetails());
    }

    @Test (priority=7)
    public void CheckBlocksSprinklers(){
        log.info("Checking CheckBlocksSprinklers");
        log.info("CheckBlocksSprinklers result is " + blocks.checkBlockSprinklers());
    }

    @Test (priority=8)
    public void CheckBlocksInfo(){
        log.info("Checking CheckBlocksInfo");
        log.info("CheckBlocksInfo result is " + blocks.checkBlocksInfo());
    }

    /*@Test (priority=9)
    public void CheckLandsLink(){
        log.info("Checking CheckLandsLink");
        log.info("CheckLandsLink result is " + blocks.checkLandsLink());
    }*/

    @Test (priority=9)
    public void CheckViewDetail(){
        log.info("Checking CheckViewDetail");
        log.info("CheckViewDetail result is " + blocks.ViewBlockDetail());
    }

    @Test (priority=10)
    public void CheckBlockDetailInfo(){
        log.info("Checking Block Detail page");
        log.info("Checking CheckBlockDetailInfo");
        log.info("CheckBlockDetailInfo result is " + blockDetail.checkBlockDetailInfo());
    }

    @AfterTest
    public void AfterTest(){
        log.info("Stop Selenium");
        selenium.stopSelenium();
    }
}
