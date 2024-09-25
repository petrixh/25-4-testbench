package com.example.application.views.gridwithfilters;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.combobox.testbench.ComboBoxElement;
import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.flow.component.textfield.testbench.TextFieldElement;
import com.vaadin.testbench.AbstractBrowserDriverTestBase;

public class GridWithFiltersPO extends AbstractBrowserDriverTestBase {

    public GridWithFiltersPO(WebDriver driver) {
        setDriver(driver);
    }

    // TODO if the filters are large/complex use a DTO to pass the values...
    public void setFilters(String name, String phone, String... occupation) {

        // Not strictly necessary here, but we can do scoped searches as well...
        WebElement filterLayoutComponent = getDriver().findElement(By.className("filter-layout"));
        TextFieldElement filterLayoutFirstTF = $(TextFieldElement.class).context(filterLayoutComponent).withCaption("Name").first();
        if(name != null){
            filterLayoutFirstTF.setValue(name);
        }

        // Or just find elements by id directly
        if(phone != null){
           $(TextFieldElement.class).id("phone-filter").setValue(phone);
        }

        if (occupation != null && occupation.length > 0) {

            // Or rely on us always wanting the first/last/n-th one... which is not a good
            // idea in general
            ComboBoxElement occupationCombo = $(ComboBoxElement.class).first();
            occupationCombo.openPopup();
            for (String item : occupation) {
                occupationCombo.selectByText(item);
            }

        }
        
        //Click the search button
        $(ButtonElement.class).context(filterLayoutComponent).id("search-button").click();

        // If this grows much more, should the GridFilter also be a PO? 
        // it's a class of its own already in the implementation...
    }

    public void resetFilter(){
        // Not strictly necessary here, but we can do scoped searches as well...
        WebElement filterLayoutComponent = getDriver().findElement(By.className("filter-layout"));
        $(ButtonElement.class).context(filterLayoutComponent).id("reset-button").click();
    }

    public int getGridRowCount() {
        return $(GridElement.class).first().getRowCount();
    }

    public String getTextOnGridRowAndCol(int row, int col) {
        return $(GridElement.class).first().getCell(row,col).getText();
    }

}
