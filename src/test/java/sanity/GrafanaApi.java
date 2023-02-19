package sanity;

import extensions.Verifications;
import io.qameta.allure.Description;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.ApiFlows;

@Listeners(utilities.Listeners.class)
public class GrafanaApi extends CommonOps {

    @Test(description = "Test01: Get Team From Grafana")
    @Description("This Test Verify team Property")
    public void test01_verifyTeam() {
        Verifications.verifyText(ApiFlows.getTeamProperty("teams[0].name"), "Example");
    }

    @Test(description = "Test02: Add Team And Verify")
    @Description("This Test Adds a Team to Grafana and Verify it")
    public void test02_addTeamAndVerify() {
        ApiFlows.postTeam("EldarTeam", "Eldar@team.com");
        Verifications.verifyText(ApiFlows.getTeamProperty("teams[0].name"), "EldarTeam");
    }

    @Test(description = "Test03: Update Team And Verify")
    @Description("This Test Update a Team in Grafana and Verify it")
    public void test03_updateTeamAndVerify() {
        String id = ApiFlows.getTeamProperty("teams[0].id");
        ApiFlows.updateTeam("EldarTeam", "Eldar@adi.com", id);
        Verifications.verifyText(ApiFlows.getTeamProperty("teams[0].email"), "Eldar@adi.com");
    }

    @Test(description = "Test03: Delete Data from Server")
    @Description("This Test Delete a Team in Grafana and Verify it")
    public void test04_deleteTeamAndVerify() {
        String id = ApiFlows.getTeamProperty("teams[0].id");
        ApiFlows.deleteTeam(id);
        Verifications.verifyText(ApiFlows.getTeamProperty("totalCount"), "1");
    }

}
