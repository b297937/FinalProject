package workflows;

import com.google.common.util.concurrent.Uninterruptibles;
import extensions.UIActions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utilities.CommonOps;

import java.util.concurrent.TimeUnit;

public class ElectronFlows extends CommonOps {

    @Step("Add New Task To the List")
    public static void addNewTask(String taskName) {
        UIActions.updateText(todoMain.txt_create, taskName);
        UIActions.insertKeys(todoMain.txt_create, Keys.RETURN);
    }

    @Step("Count and Return number of Tasks in List")
    public static int getNumberOfTasks() {
        return todoMain.list_tasks.size();
    }

    @Step("Empty List From Tasks")
    public static void emptyList() {
        for(WebElement task : todoMain.list_tasks ){
            UIActions.mouseHover(task, todoMain.btn_delete);
        }


    }
}
