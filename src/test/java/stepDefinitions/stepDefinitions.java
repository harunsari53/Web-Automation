package stepDefinitions;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class stepDefinitions {

    private WebDriver driver;

    private WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(stepDefinitions.class);

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10); // Maximum wait time of 10 seconds

        driver.manage().window().maximize();
        driver.get("https://metropolcard.com/");
    }

    @Given("user is on the MetropolCard homepage")
    public void userIsOnMetropolCardHomepage() {
        logger.info("This is an info message");
    }

    @When("user click to Kullanım Noktaları")
    public void userSearchesFor() {
        WebElement kullanimNoktalari = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu-item-2426\"]")));
        kullanimNoktalari.click();
    }

    @And("user click İlçe Seçin")
    public void userClickDistrictBox() {
        WebElement districtBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@class=\"form-control\" and @name=\"district\"]")));
        Select dropdown = new Select(districtBox);
        dropdown.selectByVisibleText("Ümraniye");
        WebElement firstAddress = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"merchants\"]/tbody/tr[1]/td[2]")));
        String firstAdressInfo = firstAddress.getText().trim();
        System.out.println(firstAdressInfo);
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

}
