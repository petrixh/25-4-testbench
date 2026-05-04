package com.example.application.views.login;

import org.openqa.selenium.WebDriver;

import com.example.application.NavigationPO;
import com.vaadin.flow.component.login.testbench.LoginOverlayElement;
import com.vaadin.testbench.AbstractBrowserDriverTestBase;

public class LoginFormPO extends AbstractBrowserDriverTestBase{

    public static String TEST_USER  = "user";
    public static String TEST_PASSWORD  = "user";

    public LoginFormPO(WebDriver driver) {
        setDriver(driver);
    }

    public NavigationPO login(String username, String password) {
        LoginOverlayElement loginOverlay = $(LoginOverlayElement.class).first();
        loginOverlay.getUsernameField().setValue(username);
        loginOverlay.getPasswordField().setValue(password);
        loginOverlay.getSubmitButton().click();

        return new NavigationPO(getDriver());

    }
    
}
