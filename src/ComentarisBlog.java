import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;

public class ComentarisBlog {
    private WebDriver driver;
    ChromeOptions options = new ChromeOptions();
    WebElement challengeStageInput = null;

    public ComentarisBlog() {
        options.addArguments("--detach");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        driver = new ChromeDriver(options);
    }
    @Given("I am on a news post page")
    public void iAmOnANewsPostPage() {
        driver.get("https://tracker.gg/r6siege/articles/trn-weekly-january-7-2024");
        try {
            Thread.sleep(3000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement ad = driver.findElement(By.id("closeIconHit"));
        ad.click();
        WebElement acceptTerms = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptTerms.click();
    }

    @When("I type the following text as a comment {string}")
    public void iTypeTheFollowingTextAsAComment(String arg0) {
        WebElement textareaElement = driver.findElement(By.className("comment-editor__field"));
        textareaElement.sendKeys(arg0);
    }

    @Then("I should not be able to post it")
    public void iShouldNotBeAbleToPostIt() {
        WebElement submitButton = driver.findElement(By.className("comment-editor__actions-submit"));
        if(!submitButton.isEnabled()) {
            System.out.println("Comportament correcte!");
        }
    }

    @Given("I am on a news post page identified as a user")
    public void iAmOnANewsPostPageIdentifiedAsAUser() {

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.open('https://thetrackernetwork.com/auth/login', '_blank')");

        // Wait for the new tab to load
        try {
            Thread.sleep(15000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Set<String> windowHandles = driver.getWindowHandles();

        // Convert the Set to an Array
        String[] windowHandleArray = windowHandles.toArray(new String[0]);

        // Get the handle for the new tab
        String newTabHandle = windowHandleArray[1];

        // Switch to the new tab
        driver.switchTo().window(newTabHandle);

        // Switch to the first frame
        driver.switchTo().frame(0);

        challengeStageInput = driver.findElement(By.xpath("//*[@id=\"challenge-stage\"]/div/label/input"));

        challengeStageInput.click();

        driver.switchTo().defaultContent();
        WebElement inputField = driver.findElement(By.name("UserName"));
        inputField.sendKeys("ProCatalanMoDzHD");

        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys("undostres4");

        try {
            Thread.sleep(2000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement button = driver.findElement(By.cssSelector("button.trn-button.trn-button--primary[type='submit']"));
        button.click();

        try {
            Thread.sleep(2000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement signInLink = driver.findElement(By.className("trn-game-bar-auth"));
        signInLink.click();
        try {
            Thread.sleep(5000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get("https://tracker.gg/r6siege/articles/trn-weekly-january-7-2024");
        try {
            Thread.sleep(4000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement ad = driver.findElement(By.id("closeIconHit"));
        ad.click();
        WebElement acceptTerms = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptTerms.click();
    }

    @Then("I should be able to post it")
    public void iShouldBeAbleToPostIt() {
        WebElement submitButton = driver.findElement(By.className("comment-editor__actions-submit"));
        if(submitButton.isEnabled()) {
            submitButton.click();
            System.out.println("Comentari penjat correctament!");
        }
    }

    @Then("I should not be able to post it and recieve the message {string}")
    public void iShouldNotBeAbleToPostItAndRecieveTheMessage(String arg0) {
        WebElement errorInfo = driver.findElement(By.className("comment-editor__actions-notice"));
        if(errorInfo.getText().equals("You need to be signed in to comment.")){
            System.out.println("Comportament correcte!");
        }
    }
}
