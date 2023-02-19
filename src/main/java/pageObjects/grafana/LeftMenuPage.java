package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LeftMenuPage {

    @FindBy(xpath = "//li[@class='css-1inbft0']")
    public WebElement btn_search;

    @FindBy(xpath = "//li[@class='css-16buu1m'][5]")
    public WebElement btn_server;

}
