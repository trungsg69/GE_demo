package GE01;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.core.selenium.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import static com.core.selenium.selenium.createElementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TrungNT47 on 2/24/2017.
 */
public class Lands {
    static Logger log = LoggerFactory.getLogger(LogonPage.class);

    private static final Properties m_properties = new Properties();

    private static  String USER_LINK1 = "";
    private static  String USER_TEXT1 = "";
    private static  String EXPECTED_TITLE = "";
    private static  String LOGIN_BUTTON = "";
    private static  String LANDS_PAGE = "";

    Lands(){
        try {
            setParameters("D:\\TestData\\Java\\GE_Demo\\src\\properties\\ge01_lands.properties");
        }catch (IOException er){
            log.error("Login Page error:" + er);
        }
    }

    public boolean checkTitle() {
        if (EXPECTED_TITLE.equals(selenium.getTitle()))
            return true;
        else
            return false;
    }

    private static void loadPropertiesFromFile(final String filePath) throws IOException {
        log.info("Load parameters from file" +filePath);
        try (final Reader reader = new FileReader(filePath)) {
            m_properties.load(reader);
        } catch (final IOException e) {
            throw e;
        }
    }

    public static String getValueProperties(String name){
        return m_properties.getProperty(name);
    }

    public static void setParameters(String filePath)throws IOException {
        try {
            loadPropertiesFromFile(filePath);
            EXPECTED_TITLE = m_properties.getProperty("page.lands.title");
            LOGIN_BUTTON = m_properties.getProperty("page.lands.button.field");
            USER_LINK1 = m_properties.getProperty("page.lands.user1");
            USER_TEXT1 = m_properties.getProperty("page.lands.user1.linktext");
            LANDS_PAGE = m_properties.getProperty("page.lands");
            log.info("Your parameters ");
            log.info("page.lands.title= " + EXPECTED_TITLE);
            log.info("page.lands.button.field= " + LOGIN_BUTTON);
            log.info("page.lands.user1= " + USER_LINK1);
            log.info("page.lands.user1.linktext= " + USER_TEXT1);
            log.info("page.lands= " + LANDS_PAGE);
        } catch (IOException e) {
            throw e;
        }
    }

    public static boolean checkUserLink() {
            log.info("Checking checkUserLink = " + USER_LINK1);

            try {
                By userLinkText1 = createElementLocator(USER_TEXT1);
                if (selenium.isTextDisplayed("User1"))
                    log.info("User1 link text is displayed");
                else {
                    log.error("User1 link text is not displayed");
                    return false;
                }

                By userLink1 = createElementLocator(USER_LINK1);
                if (selenium.isTextDisplayed("User1"))
                    log.info("User1 href is displayed");
                else {
                    log.error("User1 href is not displayed");
                    return false;
                }

                selenium.clear(userLink1);

                if (selenium.isTextDisplayed("Blocks"))
                    log.info("Blocks page is displayed");
                else {
                    log.error("Blocks page is not displayed");
                    return false;
                }

            } catch (NoSuchElementException E1) {
                log.error("NoSuchElementException occurs : " + E1);
                return false;
            }
        return true;
    }
}
