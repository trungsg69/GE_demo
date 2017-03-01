package GE01;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import com.core.selenium.selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import static com.core.selenium.selenium.createElementLocator;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by TrungNT47 on 2/24/2017.
 */
public class BlockDetail {
    static Logger log = LoggerFactory.getLogger(LogonPage.class);

    private static final Properties m_properties = new Properties();

    private static  String BLOCK_INFO = "";
    private static  String DETAIL_BUTTON = "";
    private static  String EXPECTED_TITLE = "";
    private static  String SPRINKLERS_BUTTON = "";
    private static  String LANDS_LINK = "";

    BlockDetail(){
        try {
            setParameters("D:\\TestData\\Java\\GE_Demo\\src\\properties\\ge01_blockdetailpage.properties");
        }catch (IOException er){
            log.error("Block Detail Page error:" + er);
        }
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
            EXPECTED_TITLE = m_properties.getProperty("page.blocks.title");
            BLOCK_INFO = m_properties.getProperty("page.blocks.info");
            DETAIL_BUTTON = m_properties.getProperty("page.blocks.details");
            SPRINKLERS_BUTTON = m_properties.getProperty("page.blocks.sprinklers");
            LANDS_LINK = m_properties.getProperty("page.blocks.lands");
            log.info("Your parameters ");
            log.info("page.blocks.title= " + EXPECTED_TITLE);
            log.info("page.blocks.info= " + BLOCK_INFO);
            log.info("page.blocks.details= " + DETAIL_BUTTON);
            log.info("page.blocks.sprinklers= " + SPRINKLERS_BUTTON);
            log.info("page.blocks.lands= " + LANDS_LINK);
        } catch (IOException e) {
            throw e;
        }
    }

    public boolean checkTitle() {
        log.info("Block Detail Page: Checking checkTitle ");
        if (EXPECTED_TITLE.equals(selenium.getTitle()))
            return true;
        else
            return false;
    }

    public boolean checkBlockDetailInfo() {
        try {
            log.info("Block ID:" +selenium.isTextDisplayed("Block ID"));
            log.info("Block label:" +selenium.isTextDisplayed("Block label"));
            log.info("Vegetable Name:" +selenium.isTextDisplayed("Vegetable Name"));
            log.info("Humidity:" +selenium.isTextDisplayed("Humidity"));
            log.info("History:" +selenium.isTextDisplayed("History"));
            return true;
        } catch (NoSuchElementException er) {
            log.error("checkBlockDetails error: " + er);
            return false;
        }
    }
}
