package com.example.application.views.gridwithfilters;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.example.application.NavigationPO;
import com.example.application.NavigationPO.Routes;
import com.example.application.views.login.LoginFormPO;
import com.vaadin.testbench.AbstractBrowserDriverTestBase;
import com.vaadin.testbench.BrowserTest;
import com.vaadin.testbench.BrowserTestBase;

public class GridWithFiltersIT extends BrowserTestBase {

    @BeforeEach
    public void setup() throws Exception {
        // Open the application
        getDriver().get("http://localhost:8080/");
    }

    

    // Please note that since TestBench 9 test methods
    // must be annotated with helper @BrowserTest annotation.
    @BrowserTest
    public void testFilterGrid_applyFiltersSearchAndResetFilters() {
    
        //Setup
        LoginFormPO loginForm = (LoginFormPO) new NavigationPO(getDriver()).navigateTo(Routes.LOGIN); 
        NavigationPO navigationPO = loginForm.login(LoginFormPO.TEST_USER, LoginFormPO.TEST_PASSWORD);
        GridWithFiltersPO gridWithFiltersPO = (GridWithFiltersPO) navigationPO.navigateTo(Routes.GRID);

        //Verify that grid has data... 
        assertTrue(gridWithFiltersPO.getGridRowCount() > 0, "Expected grid to have data");

        //Test
        String text = gridWithFiltersPO.getTextOnGridRowAndCol(0, 0); 
        assertNotNull(text, "Expected text to be not null for first row first column");

        //Filter the grid so we have no data... 
        gridWithFiltersPO.setFilters("Foo", null);
        Assertions.assertEquals(0, gridWithFiltersPO.getGridRowCount());

        //Reset the filters and verifty that we have data again
        gridWithFiltersPO.resetFilter();
        assertTrue(gridWithFiltersPO.getGridRowCount() > 0, "Expected grid to have data");


    }

}

