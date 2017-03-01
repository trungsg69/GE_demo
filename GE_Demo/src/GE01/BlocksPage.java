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
public class BlocksPage {
    static Logger log = LoggerFactory.getLogger(LogonPage.class);

    private static final Properties m_properties = new Properties();

    private static  String BLOCK_INFO = "";
    private static  String DETAIL_BUTTON = "";
    private static  String EXPECTED_TITLE = "";
    private static  String SPRINKLERS_BUTTON = "";
    private static  String LANDS_LINK = "";

    BlocksPage(){
        try {
            setParameters("D:\\TestData\\Java\\GE_Demo\\src\\properties\\ge01_blockspage.properties");
        }catch (IOException er){
            log.error("Blocks Page error:" + er);
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
        log.info("Block Page: Checking checkTitle ");
        if (EXPECTED_TITLE.equals(selenium.getTitle()))
            return true;
        else
            return false;
    }

    public static boolean checkLandsLink() {
        log.info("Block Page: Checking checkLandsLink  ");
        try {

            By linkLands = createElementLocator(LANDS_LINK);
            if (selenium.isExist(linkLands))
                log.info(linkLands + " is displayed");
            else {
                log.error(linkLands + " is not displayed");
                return false;
            }
            //selenium.click(linkLands);
        } catch (NoSuchElementException E1) {
            log.error("NoSuchElementException occurs : " + E1);
            return false;
        }
        return true;
    }

    public boolean checkBlocksInfo() {
        try {
            log.info("Block Page: Checking checkBlocksInfo  ");
            By BlocksInfo = createElementLocator(BLOCK_INFO);
            List<WebElement> listBlocksInfo = selenium.getElements(BlocksInfo);
            log.info("There are " + selenium.getElementsCount(BlocksInfo) + " Blocks Info");
            for (WebElement obj : listBlocksInfo) {
                log.info(obj.getText());
            }
            return true;
        } catch (NoSuchElementException er) {
            log.error("checkBlockDetails error: " + er);
            return false;
        }
    }

    public boolean checkBlockDetails() {
        try {
            log.info("Block Page: Checking checkBlockDetails  ");
            By buttonDetail = createElementLocator(DETAIL_BUTTON);
            List<WebElement> listDetail = selenium.getElements(buttonDetail);
            log.info("There are " + selenium.getElementsCount(buttonDetail) + " detail buttons");
            for (WebElement obj : listDetail) {
                log.info(obj.getText());
            }
            return true;
        } catch (NoSuchElementException er) {
            log.error("checkBlockDetails error: " + er);
            return false;
        }

    }

    public boolean checkBlockSprinklers() {
        try {
            log.info("Block Page: Checking checkBlockSprinklers  ");
            By buttonSprinklers = createElementLocator(SPRINKLERS_BUTTON);
            List<WebElement> listSprinklers = selenium.getElements(buttonSprinklers);
            log.info("There are " + selenium.getElementsCount(buttonSprinklers) + " Sprinklers buttons");
            for (WebElement obj : listSprinklers) {
                log.info(obj.getText());
            }
            return true;
        } catch (NoSuchElementException er) {
            log.error("checkBlockSprinklers error: " + er);
            return false;
        }

    }

    public boolean ViewBlockDetail() {
        try {
            log.info("Block Page: Checking ViewBlockDetail  ");
            By buttonDetail = createElementLocator(DETAIL_BUTTON);
            selenium.click(buttonDetail);
            TimeUnit.SECONDS.sleep(2);

            return true;
        } catch (InterruptedException er) {
            log.error("checkBlockSprinklers error: " + er);
            return false;
        }

    }
}
