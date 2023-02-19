package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonOps;

import java.util.concurrent.TimeUnit;

public class UIActions extends CommonOps {

    @Step("Click on Element")
    public static void click(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    @Step("Update Test Element")
    public static void updateText(WebElement elem, String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        elem.sendKeys(text);
    }

    @Step("Update Test Element as human")
    public static void updateTextHuman(WebElement elem, String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        for (char ch : text.toCharArray()) {
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
            elem.sendKeys(ch + "");
        }
    }

    @Step("Insert Key")
    public static void insertKeys(WebElement elem, Keys value) {
        elem.sendKeys(value.RETURN);
    }

    @Step("Update DropDown Element")
    public static void updateDropDown(WebElement elem, String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        Select dropDown = new Select(elem);
        dropDown.selectByVisibleText(text);
    }

    @Step("Mouse Hover Element")
    public static void mouseHover(WebElement elem1, WebElement elem2) {
        actions.moveToElement(elem1).moveToElement(elem2).click().build().perform();
    }


}
