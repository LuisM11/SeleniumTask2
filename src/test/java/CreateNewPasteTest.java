import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.marinb.automation.config.WebDriverFactory;
import org.marinb.automation.pages.CreatedPastePage;
import org.marinb.automation.pages.HomePage;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CreateNewPasteTest {
    private static final Logger logger = LogManager.getLogger(CreateNewPasteTest.class);
    WebDriver driver;
    Properties properties = new Properties();

    @BeforeEach
    public void setUp() {
        try{
            properties.load(new FileReader("src/main/resources/context.properties"));
        } catch (IOException exception) {
            logger.error("Properties weren't set");
        }
        driver = WebDriverFactory.createDriver(properties.getProperty("browser"));
        logger.info("Driver was created and set up");
    }

    @AfterEach
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    @Test
    public void createNewPaste() {
        String text = "git config --global user.name  \"New Sheriff in Town\"\n" + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" + "git push origin master --force";
        String title = "how to gain dominance among developers";
        String highlight = "Bash";

        HomePage homePage = new HomePage(driver);
        logger.info("Creating new paste");

        CreatedPastePage createdPastePage = homePage.openPage()
            .writeText(text)
            .openDropdown()
            .selectExpiration("10 Minutes")
            .writeTitle(title)
            .openHighlightDropdown()
            .selectHighlighting(highlight)
            .createNewPaste();


        logger.debug("Verifying that highlighting tag is " + highlight);
        Assertions.assertEquals(highlight, createdPastePage.getHighlightingTag().getText());
        logger.debug("Verifying that webpage title is "+ title);
        Assertions.assertEquals(title, driver.getTitle().split(" - ")[0]);
        logger.debug("Verifying that text is " + text);
        Assertions.assertEquals(text,createdPastePage.getParagraph().getText());

    }




}
