package org.marinb.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public abstract class AbstractPage {
    public static final String BASE_URL = "https://www.pastebin.com";
    private static final Logger logger = LogManager.getLogger(AbstractPage.class);
    protected final int WAIT_TIMEOUT_SECONDS = 5;
    protected WebDriver driver;
    @FindBy(id = "hideSlideBanner")
    protected WebElement closeButtonSmartBanner;

    protected abstract AbstractPage openPage();
    protected abstract AbstractPage closeBanner();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    protected FluentWait<WebDriver> createWait() {
        return new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECONDS)).
                pollingEvery(Duration.ofMillis(250)).
                ignoring(NotFoundException.class);
    }
    protected void waitElementToBeClickable(WebElement element) {
        createWait().until(ExpectedConditions.elementToBeClickable(element));
    }
    protected void waitElementToBeVisible(WebElement element) {
       createWait().until(ExpectedConditions.visibilityOf(element));
    }

    protected void click(WebElement element) {
        String elementString = "";
        try{
            elementString = String.format("with tag '%s' and name '%s'", element.getTagName(), element.getAccessibleName());
            logger.info("Clicking on element " + elementString);
            waitElementToBeVisible(element);
            waitElementToBeClickable(element);
            element.click();
            logger.info("Element " +  elementString + " was clicked");

        }
        catch (TimeoutException e) {
            logger.error("Timeout exceeded: " + e.getMessage());
        }
        catch (NoSuchElementException | StaleElementReferenceException e){
            logger.error("Element not Found: " + e.getMessage());
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }






}
