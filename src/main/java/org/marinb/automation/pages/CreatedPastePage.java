package org.marinb.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class CreatedPastePage extends AbstractPage{
    private static final Logger logger = LogManager.getLogger(CreatedPastePage.class);
    @FindBy(css = "div.left a:first-child")
    private WebElement highlightingTag;
    @FindBy(css = "div.de1")
    protected WebElement paragraph;

    public CreatedPastePage(WebDriver driver) {
        super(driver);
    }
    private void waitElementHandlingException(WebElement element){
        try{
            waitElementToBeVisible(element);
        }catch (TimeoutException e) {
            logger.error("Timeout exceeded: Element is most likely not present" + e.getMessage());
        }

        try{
            element.isDisplayed();
        }
        catch (NoSuchElementException | StaleElementReferenceException e){
            logger.error("Element not Found: " + e.getMessage() );
        }
    }

    public WebElement getHighlightingTag() {
        waitElementHandlingException(highlightingTag);
        return highlightingTag;
    }
    public WebElement getParagraph() {
        waitElementHandlingException(paragraph);
        return paragraph;
    }


    @Override
    protected AbstractPage openPage() {
        return null;
    }

    @Override
    public CreatedPastePage closeBanner() {
        logger.info("Closing banner");
        click(closeButtonSmartBanner);
        return this;
    }
}
