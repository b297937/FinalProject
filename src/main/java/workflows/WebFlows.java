package workflows;

import extensions.DBActions;
import extensions.UIActions;
import extensions.Verifications;
import io.qameta.allure.Step;
import utilities.CommonOps;

import java.util.List;

public class WebFlows extends CommonOps {
    @Step("Business Flow: Login to Grafana")
    public static void login(String user, String password) {

        UIActions.updateText(grafanaLogin.txt_username, user);
        UIActions.updateText(grafanaLogin.txt_password, password);
        UIActions.click(grafanaLogin.btn_login);
        UIActions.click(grafanaLogin.btn_skip);
    }
    @Step("Business Flow: Login to Grafana with DB Credentials")
    public static void loginDB() {
        String query = "SELECT name, password FROM Employees WHERE id='3'";
        List<String> cred = DBActions.getCredentials(query);
        UIActions.updateText(grafanaLogin.txt_username, cred.get(0));
        UIActions.updateText(grafanaLogin.txt_password, cred.get(1));
        UIActions.click(grafanaLogin.btn_login);
        UIActions.click(grafanaLogin.btn_skip);
    }

    @Step("Business Flow: Create New User")
    public static void createNewUser(String name, String email, String userName, String password) {
        UIActions.click(grafanaServerAdminMain.btn_newUser);
        UIActions.updateText(grafanaAddNewUser.txt_name, name);
        UIActions.updateText(grafanaAddNewUser.txt_email, email);
        UIActions.updateText(grafanaAddNewUser.txt_userName, userName);
        UIActions.updateText(grafanaAddNewUser.txt_password, password);
        UIActions.click(grafanaAddNewUser.btn_create);
    }

    @Step("Business Flow: Delete Last User")
    public static void deleteLastUser() {
        UIActions.click(grafanaServerAdminMain.rows.get(grafanaServerAdminMain.rows.size() - 1));
        UIActions.click(grafanaEditUser.btn_deleteUser);
        UIActions.click(grafanaEditUser.btn_confirmDeleteUser);
    }

    @Step("Business Flow: Search and Verify User")
    public static void searchAndVerifyUser(String user, String shouldExists) {
        UIActions.updateTextHuman(grafanaServerAdminMain.txt_search, user);
        if (shouldExists.equalsIgnoreCase("exist"))
            Verifications.verifyListTotal(grafanaServerAdminMain.rows, 1);
        else if (shouldExists.equalsIgnoreCase("Not-exist"))
            Verifications.verifyListTotal(grafanaServerAdminMain.rows, 0);
        else
            throw new RuntimeException(("invalid Expected Option in Data Driven testing, should be exists or not-exist"));

    }

}
