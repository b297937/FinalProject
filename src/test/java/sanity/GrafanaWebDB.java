package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.WebFlows;

@Listeners(utilities.Listeners.class)
public class GrafanaWebDB extends CommonOps {

    @Test(description = "test01 - Login to Grafana with DB Credentials")
    @Description("This Test login with DB Credentials and verifies header")
    public void test01_loginDBANDverifyHeader() {
        WebFlows.loginDB();
        Verifications.verifyTextInElement(grafanaMain.head_Dashboard, "Welcome to Grafana");

    }
}
