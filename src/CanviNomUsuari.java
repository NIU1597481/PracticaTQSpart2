import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Set;

public class CanviNomUsuari {
    private WebDriver driver;
    ChromeOptions options = new ChromeOptions();
    WebElement challengeStageInput = null;

    public CanviNomUsuari() {
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

    @Given("I am on the username changing page")
    public void iAmOnTheUsernameChangingPage() {
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
        WebElement acceptTerms = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptTerms.click();
        driver.get("https://thetrackernetwork.com/manage/username");

    }

    @When("I enter the new username {string}")
    public void iEnterTheNewUsername(String arg0) {
        WebElement acceptTerms = driver.findElement(By.className("trn-input"));
        acceptTerms.sendKeys(arg0);
    }

    @Then("I should not be able to change the username receiving the following message {string}")
    public void iShouldNotBeAbleToChangeTheUsernameReceivingTheFollowingMessage(String arg0) {
        WebElement submitButton = driver.findElement(By.className("trn-button--primary"));
        submitButton.click();
        WebElement error = driver.findElement(By.className("validation-summary-errors"));
        if(error.getText().equals(arg0)) {
            System.out.println("Missatge d'error correcte!");
        }
    }

    @Then("I should be able to change the username")
    public void iShouldBeAbleToChangeTheUsername() {
        WebElement submitButton = driver.findElement(By.className("trn-button--primary"));
        submitButton.click();
        WebElement succes = driver.findElement(By.className("trn-alert-banner--success"));
        if(succes.getText() != null) {
            System.out.println("Canvi de nom correcte!");
        }
        driver.get("https://thetrackernetwork.com/manage/username");
        WebElement acceptTerms = driver.findElement(By.className("trn-input"));
        acceptTerms.sendKeys("ProCatalanMoDzHD");
        WebElement submitButton2 = driver.findElement(By.className("trn-button--primary"));
        submitButton2.click();
    }
}
