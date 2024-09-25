package com.example.application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.application.views.gridwithfilters.GridWithFiltersPO;
import com.example.application.views.login.LoginFormPO;
import com.vaadin.flow.component.html.testbench.AnchorElement;
import com.vaadin.flow.component.menubar.testbench.MenuBarElement;
import com.vaadin.testbench.AbstractBrowserDriverTestBase;

public class NavigationPO extends AbstractBrowserDriverTestBase {

    // these could be queried and parsed as well 
    // if for instance the routes are dynamic based on user roles
    public static enum Routes {
        HELLO_WORLD(""), 
        LOGIN("login"), 
        logout("logout"), 
        PERSON_FORM("/person-form"), 
        ADDRESS_FORM("/address-form"), 
        GRID("/grid-with-filters"); 

        private String route;

        Routes(String route) {
            this.route = route;
        }

        public String getRoute() {
            return route;
        }
    }
    

    public NavigationPO(WebDriver driver) {
        setDriver(driver);
    }

    public AbstractBrowserDriverTestBase navigateTo(Routes route) {
        

        switch (route) {
            case HELLO_WORLD:
                getDriver().get("http://localhost:8080" + route.getRoute());
                return null; //new HelloWorldPO(getDriver());
                //break;
            case GRID:
                getDriver().get("http://localhost:8080" + route.getRoute());
                return new GridWithFiltersPO(getDriver()); 
                //break;
            case LOGIN:
                //Very error prone, use id's instead.. also on mobile collapsed by default.. 
                $(AnchorElement.class).id("login-link").click();
                return new LoginFormPO(getDriver());
                //break;
            case logout:
                return null; //new LogoutPO(getDriver());
                //break;
            default:
                break;

        }

        return null; 
    }
    
}
