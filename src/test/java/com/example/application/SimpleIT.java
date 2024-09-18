package com.example.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.BrowserTestBase;

public class SimpleIT extends BrowserTestBase {

    @BeforeEach
    public void setup() throws Exception {
        // Open the application
        getDriver().get("http://localhost:8080/");
    }

    // Please note that since TestBench 9 test methods
    // must be annotated with helper @BrowserTest annotation.
    @BrowserTest
    public void clickButton() {
        // Find the first button (<vaadin-button>) on the page
        ButtonElement button = $(ButtonElement.class).first();

        // Click it
        button.click();

        // Check that text of the button is "Clicked"
        Assertions.assertEquals("Say hello", button.getText());
    }

    @BrowserTest
    public void fillInAndClickButton() {

        TextFieldElement textField = $(TextFieldElement.class).first();
        textField.setValue("John Doe");

        // Find the first button (<vaadin-button>) on the page
        ButtonElement button = $(ButtonElement.class).first();

        // Click it
        button.click();

        // Check that text of the button is "Clicked"
        Assertions.assertEquals("Say hello", button.getText());

        waitUntil(ExpectedConditions.presenceOfElementLocated(By.tagName("vaadin-notification")));
        // Assert that a notification with the correct message is shown
        NotificationElement notificationElement = $(NotificationElement.class).first(); 
        //Assertions.assertTrue(notificationElement.isDisplayed());
        Assertions.assertEquals("Hello John Doe", notificationElement.getText());

    }

}