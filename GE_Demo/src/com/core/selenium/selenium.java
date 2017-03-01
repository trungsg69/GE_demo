package com.core.selenium;

import GE01.GE01_RunTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ByIdOrName;
import java.util.List;

/**
 * Created by TrungNT47 on 2/22/2017.
 */
public class selenium {

    private static WebDriver driver;
    public static void startSelenium(){
        /*DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("--disable-web-security");
        options.addArguments("–-allow-running-insecure-content");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);*/
        driver = new ChromeDriver();
    }
    public static void closeWindow(){
        driver.close();
    }
    public static void stopSelenium(){
        driver.quit();
    }

    public static String[] ResolveName(String element){
        final String[] parts = element.split("=", 2);
        return parts;
    }

    public static void openUrl(final String url) {
        driver.get(url);
    }

    public static String getTitle() {
        final String result = driver.getTitle();
        return result;
    }

    public static boolean isExist(final By locator) {
        final boolean result = driver.findElement(locator).isDisplayed();
        return result;
    }

    public static boolean isChecked(final By locator) {
        return driver.findElement(locator).isSelected();
    }

    public static void setChecked(final By locator) {
        driver.findElement(locator).sendKeys(" ");
    }

    public static boolean isTextDisplayed(final String text) {
        return driver.getPageSource().contains(text);
    }

    public static void click(final By locator) {
        driver.findElement(locator).click();
    }

    public static void sendkeys(final By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public static void clear(final By locator) {
        driver.findElement(locator).clear();
    }

    public static List<WebElement> getElements(final By locator) throws NoSuchElementException {
        final List<WebElement> result = driver.findElements(locator);
        return result;
    }

    public static int getElementsCount(final By locator) {
        List<WebElement> elements = driver.findElements(locator);
        int result = elements.size();
        return result;
    }

    public static By createElementLocator(String element){
        String[] elements = ResolveName(element);
        switch(elements[0].toLowerCase()) {
            case "classname": return By.className(elements[1]);
            case "cssselector": return By.cssSelector(elements[1]);
            case "id": return By.id(elements[1]);
            case "linktext": return By.linkText(elements[1]);
            case "name": return By.name(elements[1]);
            case "partiallinktext": return By.partialLinkText(elements[1]);
            case "tagname": return By.tagName(elements[1]);
            case "xpath": return By.xpath(elements[1]);
            case "idorname": return new ByIdOrName(elements[1]);
            default:
                System.out.println("Unsupported locator type: " + elements[0] + " with value: " + elements[1]);
                throw new IllegalArgumentException(
                        "Unsupported locator type: " + elements[0] + " with value: " + elements[1]
                );
        }
    }
}
