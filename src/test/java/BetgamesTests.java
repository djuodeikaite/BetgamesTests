import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class BetgamesTests {
    public static WebDriver driver;

    @BeforeClass
    public void before() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void contactUsPositiveTest() throws InterruptedException {
        driver.get("https://demo.betgames.tv/");
        driver.findElement(By.id("message")).sendKeys("Abcdefghijklmnopqrstuvwxyz1234567890`~!@#$%^&*()_-+=[]{}|;:',./<>?");
        driver.findElement(By.id("email")).sendKeys("Vardenis.pavardenis0123456789@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"contact\"]/button")).click();
        Thread.sleep(5000);
        String txt = "";
        try {
            txt = driver.findElement(By.xpath("//*[@id=\"send\"]")).getText();
        }
        catch (Exception e) {
        }
        Assert.assertEquals(txt, "Your message is sent.");
    }

    @Test
    public void contactUsBlankMessageTest() throws InterruptedException {
        driver.get("https://demo.betgames.tv/");
        driver.findElement(By.id("email")).sendKeys("Vardenis.pavardenis0123456789@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"contact\"]/button")).click();
        Thread.sleep(5000);
        String styleAttribute = "";
        try {
            styleAttribute = driver.findElement(By.id("message")).getAttribute("style");
            }
        catch (Exception e) {
        }
        Assert.assertTrue(styleAttribute.contains("border: 1px solid red"), "Message box border is not red.");
    }

    @Test
    public void contactUsBlankEmailTest() throws InterruptedException {
        driver.get("https://demo.betgames.tv/");
        driver.findElement(By.id("message")).sendKeys("Abcdefghijklmnopqrstuvwxyz1234567890`~!@#$%^&*()_-+=[]{}|;:',./<>?");
        driver.findElement(By.xpath("//*[@id=\"contact\"]/button")).click();
        Thread.sleep(5000);
        String styleAttribute = "";
        try {
            styleAttribute = driver.findElement(By.id("email")).getAttribute("style");
        }
        catch (Exception e) {
        }
        Assert.assertTrue(styleAttribute.contains("border: 1px solid red"), "Email box border is not red.");
    }

    @Test
    public void contactUsBlankMessageAndEmailTest() throws InterruptedException {
        driver.get("https://demo.betgames.tv/");
        driver.findElement(By.xpath("//*[@id=\"contact\"]/button")).click();
        Thread.sleep(5000);
        String styleAttributeMessage = "";
        String styleAttributeEmail = "";
        try {
            styleAttributeMessage = driver.findElement(By.id("message")).getAttribute("style");
            styleAttributeEmail = driver.findElement(By.id("email")).getAttribute("style");
        }
        catch (Exception e) {
        }
        Assert.assertTrue(styleAttributeMessage.contains("border: 1px solid red"), "Message box border is not red.");
        Assert.assertTrue(styleAttributeEmail.contains("border: 1px solid red"), "Email box border is not red.");
    }

    @AfterClass
    public void after() {
        driver.quit();
    }
}
