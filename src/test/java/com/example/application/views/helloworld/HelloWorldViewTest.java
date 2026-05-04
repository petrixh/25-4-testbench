package com.example.application.views.helloworld;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.vaadin.browserless.SpringBrowserlessTest;
import com.vaadin.flow.component.notification.Notification;

@SpringBootTest
public class HelloWorldViewTest extends SpringBrowserlessTest {

    @Test
    public void setText_clickButton_notificationIsShown() {
        final HelloWorldView helloView = navigate(HelloWorldView.class);

        // TextField and Button are available as package protected in the view
        // So we can use those from there
        test(helloView.name).setValue("Test");
        test(helloView.sayHello).click();

        // Notification isn't referenced in the view so we need to use the component
        // query API to find the notification that opened
        Notification notification = $(Notification.class).single();
        Assertions.assertEquals("Hello Test", test(notification).getText());
    }

}