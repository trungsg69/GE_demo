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
 * Created by TrungNT47 on 2/23/2017.
 */
public class LogonPage{
    static Logger log = LoggerFactory.getLogger(LogonPage.class);

    private static final Properties m_properties = new Properties();

    private static  String APP_URL = "";
    private static  String USER = "";
    private static  String PASSWORD = "";
    private static  String USER_FIELD = "";
    private static  String PASSWORD_FIELD = "";
    private static  String EXPECTED_TITLE = "";
    private static  String LOGIN_BUTTON = "";

    LogonPage(){
        try {
            setParameters("D:\\TestData\\Java\\GE_Demo\\src\\properties\\ge01_loginpage.properties");
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
            APP_URL = m_properties.getProperty("app.link");
            USER = m_properties.getProperty("page.login.user");
            PASSWORD = m_properties.getProperty("page.login.passowrd");
            USER_FIELD = m_properties.getProperty("page.login.user.field");
            PASSWORD_FIELD = m_properties.getProperty("page.login.passowrd.field");
            EXPECTED_TITLE = m_properties.getProperty("page.login.title");
            LOGIN_BUTTON = m_properties.getProperty("page.login.button.field");
            log.info("Your parameters ");
            log.info("app.link = " + APP_URL);
            log.info("page.login.user = " + USER);
            log.info("page.login.passowrd= " + PASSWORD);
            log.info("page.login.user.field = " + USER_FIELD);
            log.info("page.login.passowrd.field= " + PASSWORD_FIELD);
            log.info("page.login.title= " + EXPECTED_TITLE);
            log.info("page.login.button.field= " + LOGIN_BUTTON);
        } catch (IOException e) {
            throw e;
        }
    }

    public static void Login() {
        log.info("Logon Page: Checking Login  ");
        try {
            log.info("Open link = " + APP_URL);
            selenium.openUrl(APP_URL);

            TimeUnit.SECONDS.sleep(10);

            try {
                By username = createElementLocator(USER_FIELD);
                selenium.clear(username);
                selenium.sendkeys(username,USER);

                By password = createElementLocator(PASSWORD_FIELD);
                selenium.clear(password);
                selenium.sendkeys(password,PASSWORD);

                By btnLogin = createElementLocator(LOGIN_BUTTON);
                selenium.click(btnLogin);

                TimeUnit.SECONDS.sleep(2);
            } catch (NoSuchElementException E1) {
                log.error("NoSuchElementException occurs : " + E1);
            }

        } catch (InterruptedException E2) {
            log.error("InterruptedException occurs : " + E2);
        }
    }
}
