package pageObjects.grafana;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class MainPage {

    @FindBy(xpath = "//h1[@class='css-1aanzv4']")
    public WebElement head_Dashboard;

    @FindBy(xpath = "//div[@class='css-1vjouat']")
    public List<WebElement> ListHelp;


}
