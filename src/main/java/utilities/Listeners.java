package utilities;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class Listeners extends CommonOps implements ITestListener {
    public void onStart(ITestContext execution) {
        System.out.println("-------------------------Starting Execution-------------------------");
    }

    public void onFinish(ITestContext execution) {
        System.out.println("-------------------------Execution Ended" + "-------------------------");
    }

    public void onTestStart(ITestResult test) {

        try {
            MonteScreenRecorder.startRecord(test.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------Starting test: " + test.getName() + "-------------------------");
    }

    public void onTestSuccess(ITestResult test) {
        if (!platform.equalsIgnoreCase("api")) {
            try {
                MonteScreenRecorder.stopRecord();
            } catch (Exception e) {
                e.printStackTrace();
            }

            File file = new File("./test-recordings/" + test.getName() + ".avi");
            if (file.delete()) {
                System.out.println("Recorded Screen Cast File Deleted Successfully");
            } else {
                System.out.println("Failed to Delete the Recorded Screen Cast File");
            }

        }


        System.out.println("-------------------------Test: " + test.getName() + ": Passed :-) -------------------------");
    }

    public void onTestFailure(ITestResult test) {
        if (!platform.equalsIgnoreCase("api")) {
            try {
                MonteScreenRecorder.stopRecord();
            } catch (Exception e) {
                e.printStackTrace();
            }
            saveScreenshot();
        }

        System.out.println("-------------------------Test: " + test.getName() + ": Failed! :-( -------------------------");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult test) {
        // TODO Auto-generated method stub
    }

    public void onTestSkipped(ITestResult test) {
        System.out.println("-------------------------Starting test: " + test.getName() + " -------------------------");
    }


    @Attachment(value = "Page Screen-Shot", type = "image/png")
    public byte[] saveScreenshot() {
        if (!platform.equalsIgnoreCase("mobile"))
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        else
            return ((TakesScreenshot) mobileDriver).getScreenshotAs(OutputType.BYTES);
    }


}
