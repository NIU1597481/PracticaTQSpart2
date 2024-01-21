import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class DescarregaAplicacio {
    private WebDriver driver;

    public DescarregaAplicacio()  {
        driver = new ChromeDriver();
    }
    @Given("I am on the page where the download is available")
    public void iAmOnThePageWhereTheDownloadIsAvailable() {
        driver.get("https://tracker.gg/r6siege/app");
    }

    @When("I click on the download button")
    public void iClickOnTheDownloadButton() {
        WebElement downloadButton = driver.findElement(By.className("trn-button--large"));
        downloadButton.click();
    }

    @Then("I should get the file downloaded on my computer")
    public void iShouldGetTheFileDownloadedOnMyComputer() {
        String downloadDirectory = "C:\\Users\\messi\\Downloads";

        String fileName = "Rainbow 6 Siege Tracker - Installer.exe";

        Path filePath = Paths.get(downloadDirectory, fileName);
        if (Files.exists(filePath)) {
            System.out.println("L'arxiu s'ha descarregat satisfactoriament!");
        }
    }
}
