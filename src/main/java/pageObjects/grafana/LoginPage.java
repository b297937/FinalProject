package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {

    @FindBy(css = "input[aria-label='Username input field']")
    public WebElement txt_username;

    @FindBy(id = "current-password")
    public WebElement txt_password;

    @FindBy(xpath = "//span[@class='css-1mhnkuh']")
    public WebElement btn_login;

    @FindBy(xpath = "//button[@class='css-15h6w-button']/span[@class='css-1mhnkuh']")
    public WebElement btn_skip;
}
