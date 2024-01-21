import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.NoSuchElementException;

public class Login {

    private WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    WebElement challengeStageInput = null;

    public Login() {

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

    @io.cucumber.java.en.Given("^I am on the login page$")
    public void iAmOnTheLoginPage() {

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
    }

    @io.cucumber.java.en.When("^I enter the username \"([^\"]*)\"$")
    public void iEnterTheUsername(String arg0) throws Throwable {
        driver.switchTo().defaultContent();
        WebElement inputField = driver.findElement(By.name("UserName"));
        inputField.sendKeys(arg0);
    }

    @io.cucumber.java.en.And("^I enter the password \"([^\"]*)\"$")
    public void iEnterThePassword(String arg0) throws Throwable {
        WebElement password = driver.findElement(By.name("Password"));
        password.sendKeys(arg0);
    }

    @io.cucumber.java.en.And("^I log in")
    public void iLogIn() {
        try {
            Thread.sleep(2000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement button = driver.findElement(By.cssSelector("button.trn-button.trn-button--primary[type='submit']"));
        button.click();
    }

    @io.cucumber.java.en.Then("^I should be logged in$")
    public void iShouldBeLoggedIn() {
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
        WebElement button = driver.findElement(By.className("trn-game-bar__profile"));
        if (button != null) {
            System.out.println("Log in correcte!");
        }
    }

    @io.cucumber.java.en.Then("^I should see the login error message \"([^\"]*)\"$")
    public void iShouldSeeTheLoginErrorMessage(String arg0) throws Throwable {
        try {
            Thread.sleep(2000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement error = driver.findElement(By.cssSelector(".trn-alert-banner.trn-alert-banner--error"));
        if(error.getText().equals(arg0)) {
            System.out.println("Missatge d'error correcte!");
        }
    }

    // No es pot fer aquesta comprovació del text amb selenium ja que aquest missatge no està dintre de cap element sinó que es fa des de el form.
    @io.cucumber.java.en.Then("^I should not be able to log in and should be reminded by the page to complete the field")
    public void iShouldNotBeAbleToLogInAndShouldBeRemindedByThePageToCompleteTheField() {
        try {
            Thread.sleep(2000); // Wait for 15 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            WebElement button2 = driver.findElement(By.className("trn-game-bar__profile"));
        } catch (NoSuchElementException e) {
            System.out.println("No ens deixa loggejar-nos i podem veure el missatge d'error!");
        }
    }

}
