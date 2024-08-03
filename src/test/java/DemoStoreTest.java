import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DemoStoreTest {

    private WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("-no-sandbox");
        options.addArguments("--disabel-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testDemo(){
        driver.get("https://www.google.com");
    }

    @After
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
            Files.copy(screenshot.toPath(),Paths.get("screenshots", "screenshot.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
