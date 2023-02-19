package pageObjects.grafana;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EditUserPage {

    @FindBy(css = "button[class='css-14lhqbx-button']>span[class='css-1mhnkuh']")
    public WebElement btn_deleteUser;
    @FindBy(xpath = "//button[@aria-label='Confirm Modal Danger Button']")
    public WebElement btn_confirmDeleteUser;
}
