import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

public class ChromeHeadlessTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testPageTitle() {
        driver.get("https://google.com");
        String title = driver.getTitle();
        assertEquals("Example Domain", title);
    }

    @Test
    public void testLoginSuccess() {
        driver.get("https://www.saucedemo.com/");

        // Gebruikersnaam en wachtwoord invullen
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("standard_user");
        passwordField.sendKeys("secret_sauce");
        loginButton.click();

        // Controle: zijn we op de productenpagina?
        boolean isOnProductsPage = driver.getCurrentUrl().contains("inventory.html");
        assertTrue("Gebruiker is op de productenpagina terechtgekomen.", isOnProductsPage);
    }

    @Test
    public void TestLogin() {
        driver.get("http://localhost:3000/login"); // Vervang door de URL van je inlogpagina
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Wacht tot de pagina geladen is
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));

        usernameField.sendKeys("testuser"); // Vervang door een geldige gebruikersnaam
        try {
            Thread.sleep(3000); // Wacht 3 seconden
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordField.sendKeys("testpassword"); // Vervang door een geldig wachtwoord
        try {
            Thread.sleep(3000); // Wacht 3 seconden
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginButton.click();
        try {
            Thread.sleep(3000); // Wacht 3 seconden
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        // Controleer of de gebruiker is ingelogd door te kijken naar een element dat alleen zichtbaar is na inloggen
        WebElement errorElement = driver.findElement(By.xpath("//p[@class='error']"));
        assertTrue(errorElement.isDisplayed()); // Controleer of het foutbericht zichtbaar is
    }

    @Test
    public void TestGoogleSearch() {
        driver.get("https://www.google.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Wacht tot de pagina geladen is
        
        WebElement rejectButton = driver.findElement(By.xpath("//div[text()='Alles afwijzen']"));
// wait for the rejectButton to be visible

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Wacht tot de knop zichtbaar is

        rejectButton.click(); // Klik op de knop om cookies te weigeren
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Wacht tot de knop zichtbaar is
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver" + "\n");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Wacht tot de resultaten geladen zijn
        WebElement firstResult = driver.findElement(By.cssSelector("h3"));
        String firstResultText = firstResult.getText();
        assertTrue("De eerste zoekresultaat bevat 'Selenium'", firstResultText.contains("Selenium"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}