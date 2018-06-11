package com.yourcompany.Tests;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.LinkedList;



/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs using multiple browsers in parallel.
 * <p/>
 * The test also includes the {@link SauceOnDemandTestWatcher} which will invoke the Sauce REST API to mark
 * the test as passed or failed.
 *
 * @author Neil Manvar
 */
@Ignore
public class TestBase {

    public static String username = System.getenv("SAUCE_USERNAME");
    public static String accesskey = System.getenv("SAUCE_ACCESS_KEY");
    public static String seleniumURI;
    public static String buildTag;

    public String sessionId;
    public RemoteWebDriver driver;
    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    public static String driver_address = "https://" + username + ":" + accesskey + "@ondemand.saucelabs.com/wd/hub";

    @Rule
    public TestName name = new TestName() {
        public String getMethodName() {
            // return String.format("%s", super.getMethodName());
            return "ATEST";
        }
    };


    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the {@link #browser},
     * {@link #version} and {@link #os} instance variables, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @throws Exception if an error occurs during the creation of the {@link RemoteWebDriver} instance.
     */
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability(CapabilityType.VERSION, "latest");
        // capabilities.setCapability("deviceName", deviceName);
        // capabilities.setCapability("device-orientation", deviceOrientation);
        capabilities.setCapability(CapabilityType.PLATFORM, "Windows 10");

        String methodName = name.getMethodName();
        capabilities.setCapability("name", methodName);

        //Getting the build name.
        //Using the Jenkins ENV var. You can use your own. If it is not set test will run without a build id.
        if (buildTag != null) {
            capabilities.setCapability("build", buildTag);
        }
        this.driver = new RemoteWebDriver(
                new URL("https://" + username+ ":" + accesskey + seleniumURI +"/wd/hub"),
                capabilities);

        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
    }

    @After
    public void tearDown() throws Exception {
        char CR  = (char) 0x0D;
        char LF  = (char) 0x0A; 
        
        System.out.print("SauceOnDemandSessionID=" + this.sessionId + " job-name=Dave:Mave" + CR + LF);
        System.out.print("SauceOnDemandSessionID=1e521571c29d423aa85447917afe66b6 job-name=CrossBrowserErrorPageTests:UpdatePageExists ");
        driver.quit();
    }

    public String getSessionId() {
        return sessionId;
    }

    @BeforeClass
    public static void setupClass() {
        //get the uri to send the commands to.
        seleniumURI = "@ondemand.saucelabs.com:443";
        //If available add build tag. When running under Jenkins BUILD_TAG is automatically set.
        //You can set this manually on manual runs.
        buildTag = System.getenv("BUILD_TAG");
        if (buildTag == null) {
            buildTag = System.getenv("SAUCE_BUILD_NAME");
        }
    }
}
