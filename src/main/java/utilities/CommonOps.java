package utilities;//package utilities;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC_OSX;
import static org.junit.Assume.assumeTrue;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Screen;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import workflows.ElectronFlows;

import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class CommonOps extends Base {
    /*
    Method Name: get Data
    Method Description: This get the data from xml configuration file
    Method Parameters: String
    Method Return: String
     */
    public static String getData(String nodeName) {
        File fxmlFile;
        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            fxmlFile = new File("./Configuration/DataConfig.xml");
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fxmlFile);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            System.out.println("Error Reading file:" + e);
        } finally {
            return doc.getElementsByTagName(nodeName).item(0).getTextContent();
        }
    }
    /*
    Method Name: initials Platforms
    Method Description: This get the type from xml configuration file and open the browser dimension optimal
    Method Parameters: String
    Method Return: String
    */

    public static void initBrowser(String browserType) {
        if (browserType.equalsIgnoreCase("chrome"))
            driver = initChromeDriver();
        else if (browserType.equalsIgnoreCase("firefox"))
            driver = initFirefoxDriver();
        else if (browserType.equalsIgnoreCase("edge"))
            driver = initEdgeDriver();
        else if (browserType.equalsIgnoreCase("safari"))
            driver = initSafariDriver();
        else
            throw new RuntimeException("Invalid Browser Type ");

//        driver.manage().window().maximize();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(getData("TimeOut")));
        driver.get(getData("Url"));
        ManagePages.initGrafana();


    }
    /*
    Method Name: init Chrome browser
    Method Description: This init the driver browser
    Method Parameters: WebDriver
    Method Return: WebDriver
    */
    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }
    /*
    Method Name: init Firefox browser
    Method Description: This init the driver browser
    Method Parameters: WebDriver
    Method Return: WebDriver
    */
    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }
    /*
    Method Name: init Edge browser
    Method Description: This init the driver browser
    Method Parameters: WebDriver
    Method Return: WebDriver
    */
    public static WebDriver initEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        return driver;
    }
    /*
    Method Name: init Safari browser
    Method Description: This init the driver browser
    Method Parameters: WebDriver
    Method Return: WebDriver
    */
    public static WebDriver initSafariDriver() {
        assumeTrue(IS_OS_MAC_OSX);
        assumeTrue(IS_OS_MAC);
        driver = new SafariDriver();
        return driver;
    }
    /*
    Method Name: init Mobile browser
    Method Description: This init the Appium driver for android
    Method Parameters: String
    Method Return: void
    */
    public static void initMobile() {
        dc.setCapability(MobileCapabilityType.UDID, getData("UDID"));
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getData("AppPackage"));
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getData("AppActivity"));
        try {
            mobileDriver = new AndroidDriver<>(new URL(getData("AppiumServer")), dc);
        } catch (Exception e) {
            System.out.println("Can not connect to appium server, see details: " + e);
        }
        ManagePages.initMortgage();
        mobileDriver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
        wait = new WebDriverWait(mobileDriver, Long.parseLong(getData("TimeOut")));
    }
    /*
    Method Name: init API
    Method Description: This init the API
    Method Parameters: String
    Method Return: void
    */
    public static void initAPI() {
        RestAssured.baseURI = getData("urlAPI");
        httpRequest = RestAssured.given().auth().preemptive().basic(getData("UserName"), getData("Password"));
    }
    /*
    Method Name: init Electron app driver
    Method Description: This init the Electron App
    Method Parameters: String
    Method Return: void
    */
    public static void initElectron() {
        System.setProperty("webdriver.chrome.driver", getData("ElectronDriverPath"));
        ChromeOptions opt = new ChromeOptions();
        opt.setBinary(getData("ElectronAppPath"));
        dc.setCapability("chromeOptions", opt);
        dc.setBrowserName("chrome");
        driver = new ChromeDriver(dc);
        ManagePages.initToDoList();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Long.parseLong(getData("TimeOut")));
    }
    /*
    Method Name: init appium desktop driver
    Method Description: This init the Appium desktop driver for windows app
    Method Parameters: String
    Method Return: void
    */
    public static void initDesktop() {
        dc.setCapability("app", getData("CalculatorApp"));
        try {
            driver = new WindowsDriver(new URL(getData("AppiumServerDesktop")), dc);
        } catch (Exception e) {
            System.out.println("Can not Connect to Appium server, See Details: " + e);
        }
        driver.manage().timeouts().implicitlyWait(Long.parseLong(getData("TimeOut")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Long.parseLong(getData("TimeOut")));
        ManagePages.initCalculator();
    }

    /*
    Method Name: start Session
    Method Description: This get the type from xml configuration file and open the platform before suite
    Method Parameters: String
    Method Return: String
    */
    @BeforeSuite
    @Parameters({"PlatformName"})
    public void startSession(@Optional String PlatformName) {
        platform = getData("PlatformName");
        if (PlatformName != null) {
            platform = PlatformName;
        }

        if (platform.equalsIgnoreCase("web"))
            initBrowser(getData("BrowserName"));
        else if (platform.equalsIgnoreCase("mobile"))
            initMobile();
        else if (platform.equalsIgnoreCase("api"))
            initAPI();
        else if (platform.equalsIgnoreCase("electron"))
            initElectron();
        else if (platform.equalsIgnoreCase("desktop"))
            initDesktop();
        else
            throw new RuntimeException("Invalid platform name");

        softAssert = new SoftAssert();
        screen = new Screen();
        if (!platform.equalsIgnoreCase("api")) {
            actions = new Actions(driver);
        }
//        ManageDB.openConnection(getData("DBURL"), getData("DBUserName"), getData("DBPassword"));
    }
    /*
    Method Name: before Method
    Method Description: This is session start recording before all method
    Method Parameters: Method
    Method Return: void
    */
    @BeforeMethod
    public void beforeMethod(Method method) throws Exception {
        if (!platform.equalsIgnoreCase("api")) {
            MonteScreenRecorder.startRecord(method.getName());
        }
    }
    /*
    Method Name: after Method
    Method Description: This is session verify the platform and open it (if need it)
    Method Parameters: void
    Method Return: void
    */
    @AfterMethod
    public void afterMethod() {
        if (platform.equalsIgnoreCase("web"))
            driver.get(getData("Url"));
        else if (platform.equalsIgnoreCase("electron")) {
            ElectronFlows.emptyList();
        }
    }
    /*
    Method Name: after Method
    Method Description: This is session stop recording and delete if test is passed, close browser or apps after the tests end.
    Method Return: void
    Method Parameters: void
    */
    @AfterClass
    public void closeSession() {
//        ManageDB.closeConnection();
        if (!platform.equalsIgnoreCase("api")) {
            if (!platform.equalsIgnoreCase("mobile"))
                driver.quit();
            else
                mobileDriver.quit();
        }
    }

}
