import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DemoStoreTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
    }

    @Test
    public void testDemo(){
        driver.get("https://www.google.com");

    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            takeScreenshot();
            driver.quit();
        }
    }

    private void takeScreenshot() {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(screenshot.toPath(), Paths.get("screenshots", "screenshot.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
