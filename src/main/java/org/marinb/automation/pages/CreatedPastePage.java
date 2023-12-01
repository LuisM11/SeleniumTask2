package org.marinb.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public WebElement getHighlightingTag() {
        waitElementToBeVisible(highlightingTag);
        return highlightingTag;
    }
    public WebElement getParagraph() {
        waitElementToBeVisible(paragraph);
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
