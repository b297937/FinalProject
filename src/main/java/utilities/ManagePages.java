package utilities;

import org.openqa.selenium.support.PageFactory;
import pageObjects.grafana.AddNewUserPage;
import pageObjects.grafana.EditUserPage;
import pageObjects.grafana.ServerAdminMainPage;
import pageObjects.grafana.ServerAdminMenuPage;

public class ManagePages extends Base{
    public static void initGrafana(){
        grafanaLogin = PageFactory.initElements(driver,pageObjects.grafana.LoginPage.class);
        grafanaMain = PageFactory.initElements(driver,pageObjects.grafana.MainPage.class);
        grafanaLeftMenu = PageFactory.initElements(driver,pageObjects.grafana.LeftMenuPage.class);
        grafanaServerAdminMenu = PageFactory.initElements(driver, ServerAdminMenuPage.class);
        grafanaServerAdminMain = PageFactory.initElements(driver, ServerAdminMainPage.class);
        grafanaAddNewUser = PageFactory.initElements(driver, AddNewUserPage.class);
        grafanaEditUser = PageFactory.initElements(driver, EditUserPage.class);

    }

    public static void initMortgage(){
        mortgageMain = new pageObjects.mortgage.MainPage(mobileDriver);
    }

    public static void initToDoList(){
        todoMain = PageFactory.initElements(driver, pageObjects.todoList.MainPage.class);
    }
    public static void initCalculator(){
        calcMain = PageFactory.initElements(driver, pageObjects.Calculator.MainPage.class);
    }
}
