package pageObjects.todoList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage {

    @FindBy(css = "input[placeholder='Create a task']")
    public WebElement txt_create;

    @FindBy(className = "Task__content_3sOR8")
    public List<WebElement> list_tasks;

    @FindBy(css =  "svg[class='Icon__icon_1EC2e Task__trashIcon_1o29E']")
    public WebElement btn_delete;



}
