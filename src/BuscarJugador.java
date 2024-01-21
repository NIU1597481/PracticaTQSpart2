import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BuscarJugador {

    private WebDriver driver;

    public BuscarJugador() {
        driver = new ChromeDriver();
    }
    @Given("I am on the game tracking page")
    public void iAmOnTheGameTrackingPage() {
        driver.get("https://r6.tracker.network/");
        WebElement acceptTerms = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptTerms.click();
    }

    @When("I enter the player nickname {string}")
    public void iEnterThePlayerNickname(String arg0) {
        WebElement inputField = driver.findElement(By.name("name"));
        inputField.sendKeys(arg0);
    }

    @And("I select the platform {string} where he plays")
    public void iSelectThePlatformWhereHePlays(String arg0) {
        String platform = "ion ion-" + arg0;
        WebElement platformClickable = driver.findElement(By.cssSelector(".hp-search-form__platforms .ion.ion-" + arg0));
        platformClickable.click();
        WebElement button = driver.findElement(By.className("hp-search-form__button"));
        button.click();
    }

    @Then("I should be able to see all the information related to his profile in the game")
    public void iShouldBeAbleToSeeAllTheInformationRelatedToHisProfileInTheGame() {
        try {
            WebElement profile = driver.findElement(By.className("trn-profile-header__name"));
        } catch (NoSuchElementException e) {
            System.out.println("Càrrega de perfil correcte!");
        }
    }

    @Then("I should be informed with the following error {string}")
    public void iShouldBeInformedWithTheFollowingError(String arg0) {
        WebElement error = driver.findElement(By.className("trn-card__content"));
        if (error.getText().equals(arg0)) {
            System.out.println("Missatge d'error correcte!");
        }
    }
}
