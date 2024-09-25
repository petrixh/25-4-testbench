package com.example.application.views.login;

import org.openqa.selenium.WebDriver;

import com.example.application.NavigationPO;
import com.vaadin.flow.component.login.testbench.LoginFormElement;
import com.vaadin.testbench.AbstractBrowserDriverTestBase;

public class LoginFormPO extends AbstractBrowserDriverTestBase{

    public static String TEST_USER  = "user";
    public static String TEST_PASSWORD  = "user";

    public LoginFormPO(WebDriver driver) {
        setDriver(driver);
    }

    public NavigationPO login(String username, String password) {
        LoginFormElement loginForm = $(LoginFormElement.class).first(); 
        loginForm.getUsernameField().setValue(username);
        loginForm.getPasswordField().setValue(password);
        loginForm.getSubmitButton().click();

        return new NavigationPO(getDriver());

    }
    
}
