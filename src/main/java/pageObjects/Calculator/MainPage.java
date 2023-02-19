package pageObjects.Calculator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage{

    @FindBy(name = "Clear")
     public WebElement btn_clear;
    @FindBy(name = "One")
    public WebElement btn_one;
    @FindBy(name = "Eight")
    public WebElement btn_eight;
    @FindBy(name = "Plus")
    public WebElement btn_plus;
    @FindBy(name = "Equals")
    public WebElement btn_equals;
    @FindBy(xpath = "//*[@AutomationId='CalculatorResults']")
    public WebElement field_result;
}
