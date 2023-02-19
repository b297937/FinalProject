package sanity;

import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.WebFlows;

@Listeners(utilities.Listeners.class)
public class GrafanaWeb extends CommonOps {

    @BeforeClass
    public void startSession(){
        WebFlows.login(getData("UserName"),getData("Password"));
    }

    @Test(description = "test01 - verifyHeader")
    @Description("This Test login and verifies header")
    public void test01_verifyHeader() {

        Verifications.verifyTextInElement(grafanaMain.head_Dashboard, "Welcome to Grafana");

    }

    @Test(description = "test02 - verify Default Users")
    @Description("This Test verifies the default users")
    public void test02_verifyDefaultUsers() {
        UIActions.mouseHover(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);
        Verifications.numberOfElements(grafanaServerAdminMain.rows, 1);
    }

    @Test(description = "test03 - verify New User")
    @Description("This Test verifies a new user has been added")
    public void test03_verifyNewUser() {
        UIActions.mouseHover(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);
        WebFlows.createNewUser("Eldar", "Eldar@gmail.com", "eldaradi7", "Eldar12345");
        Verifications.numberOfElements(grafanaServerAdminMain.rows, 2);
    }

    @Test(description = "test04 - verify User Deletion")
    @Description("This Test verifies the deletion of a user")
    public void test04_verifyUserDeletion() {
        UIActions.mouseHover(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);
        WebFlows.deleteLastUser();
        Verifications.numberOfElements(grafanaServerAdminMain.rows, 1);
    }

    @Test(description = "test05 - Verify List Help")
    @Description("This Test verifies the default progress step are displayed(using soft assertion)")
    public void test05_VerifyListHelp() {
        Verifications.visibilityOfElements(grafanaMain.ListHelp);
    }


    @Test(description = "test06 - verify Avatar Icon")
    @Description("This Test verify The Avatar using Sikuli tool")
    public void test06_verifyAvatarIcon() {
        Verifications.visualElement("GrafanaAvatar");
    }

    @Test(description = "test07 - search and Verify User", dataProvider = "data-provider", dataProviderClass = utilities.ManageDDT.class)
    @Description("This Test Searched Users in table using DDT")
    public static void test07_searchAndVerifyUser(String user, String shouldExist) {
        UIActions.mouseHover(grafanaLeftMenu.btn_server, grafanaServerAdminMenu.link_users);
        WebFlows.searchAndVerifyUser(user, shouldExist);
    }

}
