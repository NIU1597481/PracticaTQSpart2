import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.Set;

public class XarxesSocials {

    private WebDriver driver;
    private WebDriverWait wait;

    public XarxesSocials() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }
    @Given("I am on the main page")
    public void iAmOnTheMainPage() {
        driver.get("https://tracker.gg/");
    }

    @When("I click on the {string} logo")
    public void iClickOnTheLogo(String arg0) {
        String className = "trn-button--platform-" + arg0;
        String cssSelector = ".trn-button." + className;
        WebElement element = driver.findElement(By.cssSelector(cssSelector));
        element.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(driver.getWindowHandle())) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }

    @Then("I should be redirected to {string}")
    public void iShouldBeRedirectedTo(String arg0) {
        String url = driver.getCurrentUrl();
        if(arg0.equals(url)) {
            System.out.println("Redirecció correcte!");
        }
        else {
            System.out.println("Redirecció incorrecte!");
        }
    }
}
