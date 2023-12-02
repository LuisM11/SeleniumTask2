package org.marinb.automation.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.marinb.automation.config.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{
    private static final Logger logger = LogManager.getLogger(HomePage.class);
    @FindBy(id = "postform-text")
    private WebElement textArea;
    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationDropdownLabel;
    @FindBy(id = "select2-postform-expiration-results")
    private WebElement expirationDropdownList;
    @FindBy(id = "postform-name")
    private WebElement pasteTitle;
    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createNewPasteButton;
    @FindBy(id ="select2-postform-format-container")
    private WebElement highlightDropdownLabel;
    @FindBy(css = "#select2-postform-format-results ul.select2-results__options.select2-results__options--nested")
    private WebElement highlightDropdownList;


    public HomePage(WebDriver driver) {
        super(driver);
    }


    @Override
    public HomePage openPage() {
        logger.info("Opening home page");
        driver.get(BASE_URL);
//        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return this;
    }

    @Override
    public HomePage closeBanner() {
        logger.info("Closing banner");
        click(closeButtonSmartBanner);
        return  this;
    }

    public HomePage writeText(String text) {
        click(textArea);
        textArea.sendKeys(text);
        return this;
    }

    public HomePage openDropdown(){
        click(expirationDropdownLabel);
        return this;
    }

    public HomePage selectExpiration(String expiration){
        WebElement expirationOption = expirationDropdownList.findElement(By.xpath("//li[text()='" + expiration + "']"));
        click(expirationOption);
        return this;
    }

    public HomePage writeTitle(String title) {
        click(pasteTitle);
        pasteTitle.sendKeys(title);
        return this;
    }

    public HomePage openHighlightDropdown() {
        waitElementToBeClickable(highlightDropdownLabel);
        click(highlightDropdownLabel);
        return this;
    }

    public HomePage selectHighlighting(String language) {
        WebElement languageOption = highlightDropdownList.findElement(By.xpath("//li[text()='" + language + "']"));
        click(languageOption);
        return this;
    }

    public CreatedPastePage createNewPaste()  {
        scrollToElement(createNewPasteButton);
        click(createNewPasteButton);
        return new CreatedPastePage(driver);
    }








}
