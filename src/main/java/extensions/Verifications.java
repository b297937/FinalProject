package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import utilities.CommonOps;

import java.util.List;

import static org.testng.Assert.*;

public class Verifications extends CommonOps {
    @Step("Verify Text In Element")
    public static void verifyTextInElement(WebElement elem, String expected) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getText(), expected);

    }

    @Step("Verify Number Of Elements")
    public static void numberOfElements(List<WebElement> elems, int expected) {
        wait.until(ExpectedConditions.visibilityOf(elems.get(elems.size() - 1)));
        assertEquals(elems.size(), expected);

    }

    @Step("Verify Visibility Of Elements (Soft-Assertion)")
    public static void visibilityOfElements(List<WebElement> elems) {
        for (WebElement elem : elems) {
            softAssert.assertTrue(elem.isDisplayed(), "Sorry" + elem.getText() + "not displayed");
        }
        softAssert.assertAll("Some elements where not displayed");
    }

    @Step("Verify Element Visually")
    public static void visualElement(String expectedImgName) {
        try {
            screen.find(getData("ImageRepo") + expectedImgName + ".png");
        } catch (FindFailed findFailed) {
            System.out.println("Error Comparing Image File: " + findFailed);
            fail("Error Comparing Image File: " + findFailed);
        }
    }

    @Step("verify Element Displayed")
    public static void verifyListTotal(List<WebElement> elements, int expectedNumber) {
        assertTrue(elements.size() == expectedNumber);
    }


    @Step("verify Text with Text")
    public static void verifyText(String actual, String expected) {
        assertEquals(actual, expected);
    }

    @Step("verify Number with Number")
    public static void verifyNumber(int actual, int expected) {
        assertEquals(actual, expected);
    }

}
