import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class testingbot {
    public static final String KEY = "c8ee8af13e3778e1a50ee1f4c64c8082";
    public static final String SECRET = "1c0644d2e2bbe55f1ebb57457675656a";
    public static final String HUB_URL = "http://" + KEY + ":" + SECRET + "@hub.testingbot.com/wd/hub";
    private WebDriver driver;
    private DesiredCapabilities caps;
    @Parameters({"browser"})
    @BeforeClass

    public void setup(String browser) throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browser);
        cap.setCapability("name", "Selenium Testing Bot Test 1");

        URL url = new URL(HUB_URL);

        driver = new RemoteWebDriver(url, cap);

        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();
        driver.findElement(By.cssSelector("[formcontrolname=\"username\"]")).sendKeys("nigeria_tenant_admin");
        driver.findElement(By.cssSelector("[formcontrolname=\"password\"]")).sendKeys("TnvLOl54WxR75vylop2A");
        driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]")).click();

    }

    @Test
    public void test() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@class='cc-btn cc-dismiss']")).click();
        driver.findElement(By.cssSelector("fuse-navigation .group-items > :nth-child(4)")).click();
        driver.findElement(By.cssSelector(".group-items > :nth-child(4) > .children > :nth-child(1)")).click();
        driver.findElement(By.xpath("//span[text()='Attestations']")).click();
        driver.findElement(By.cssSelector("[data-icon='plus']")).click();
        String name= "smyr";
        driver.findElement(By.xpath("//ms-dialog-content//ms-text-field[@placeholder='GENERAL.FIELD.NAME']//input")).sendKeys(name);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Save']")).click();

        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Save']/parent::span/parent::button")).click();
    }

    @Test(dataProvider = "capabilitiesProvider")

    public void test(String platform, String browserName, String browserVersion) throws MalformedURLException {

        caps.setCapability("platform", platform);
        caps.setCapability("browserName", browserName);
        caps.setCapability("version", browserVersion);
        URL url = new URL(HUB_URL);
        driver = new RemoteWebDriver(url, caps);

    }
    @DataProvider
    public Object[][] capabilitiesProvider(){
        return new Object[][]{
                {"WIN10", "chrome", "79"},
                {"WIN10", "firefox", "42"},
                {"SIERRA", "chrome", "79"},
        };
    }
    @AfterClass
    public void quit() {
        driver.quit();
    }

}
