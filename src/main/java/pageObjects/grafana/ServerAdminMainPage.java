package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ServerAdminMainPage {
    @FindBy(css = "td[class='width-4 text-center link-td']>a")
    public List<WebElement> rows;

    @FindBy(css = "a[href='admin/users/create']")
    public WebElement btn_newUser;

    @FindBy(className = "css-1haxx2a-input-input")
    public WebElement txt_search;

}
