package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddNewUserPage {
    @FindBy(id = "name-input")
    public WebElement txt_name;

    @FindBy(xpath = "//input[@id='email-input']")
    public WebElement txt_email;

    @FindBy(id = "username-input")
    public WebElement txt_userName;

    @FindBy(id = "password-input")
    public WebElement txt_password;

    @FindBy(css = "button[class='css-1e07s1o-button']>span[class='css-1mhnkuh']")
    public WebElement btn_create;
}
