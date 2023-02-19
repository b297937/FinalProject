package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ServerAdminMenuPage {

    @FindBy(xpath = "//a[@href='/admin/users']")
    public WebElement link_users;

    @FindBy(xpath = "//a[@href='/admin/orgs']")
    public WebElement link_organization;

    @FindBy(xpath = "//a[@href='/admin/settings']")
    public WebElement link_settings;

    @FindBy(xpath = "//a[@href='/admin/upgrading']")
    public WebElement link_Stats_and_license;
}
