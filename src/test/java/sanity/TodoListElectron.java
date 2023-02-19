package sanity;//package sanity;

import com.google.common.util.concurrent.Uninterruptibles;
import extensions.Verifications;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import workflows.ApiFlows;
import workflows.ElectronFlows;

import java.util.concurrent.TimeUnit;

@Listeners(utilities.Listeners.class)
public class TodoListElectron extends CommonOps {

    @Test(description = "Test01: Add and Verify New Task")
    @Description("This Test adds a new task and verify it in list of tasks")
    public void test01_addAndVerifyNewTask() {
        ElectronFlows.addNewTask("Learn Java");
        Verifications.verifyNumber(ElectronFlows.getNumberOfTasks(), 1);
    }

    @Test(description = "Test02: Add and Verify New Task")
    @Description("This Test adds a new task and verify it in list of tasks")
    public void test02_addAndVerifyNewTask() {
        ElectronFlows.addNewTask("Learn Java");
        ElectronFlows.addNewTask("Learn c#");
        ElectronFlows.addNewTask("Learn Python");
        Verifications.verifyNumber(ElectronFlows.getNumberOfTasks(), 3);
    }
}
