import commons.App;
import commons.helpers.Driver;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@CommonsLog
public class BaseTest extends TestListener {

    protected App app;
    protected SoftAssert softAssert;
    protected Logger logger;

    @BeforeClass
    public void setUp() {
        try {
            HttpURLConnection connection;
            connection = (HttpURLConnection) new URL(App.STAGING_BASE_URL).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.error(connection.getResponseMessage() + connection.getErrorStream());
                connection.disconnect();
                Driver.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Driver.initDriver();
        //setupAllureReports();
        app = new App();
        softAssert = new SoftAssert();
        logger = LogManager.getLogger("");
        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }

    @AfterMethod
    public void clearCookie() {
        Driver.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        Driver.clearCookies();
        Driver.close();
    }

//    static void setupAllureReports() {
//        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
//                .screenshots(true)
//                .savePageSource(true)
//        );
//    }
}
