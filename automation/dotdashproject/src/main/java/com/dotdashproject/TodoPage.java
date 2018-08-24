package com.dotdashproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Author - Sam Mohammed
 * Date - 08/25/2018
 */

public class TodoPage {
    public WebDriver driver;
    public TodoPage(WebDriver driver){
        this.driver=driver;
    }

    By renameCategoryLink = By.xpath("//a[text()='Rename the category']");
    By addCategoryWithThisColorLink = By.xpath("//a[text()='Add the category with this colour']");
    By neverMindLink = By.xpath("//a[text()='Nevermind']");
    By warningMessage = By.xpath("/html/body/span[1]");

    public WebElement clickOnRenameCategoryLink(){
        return driver.findElement(renameCategoryLink);
    }

    public WebElement clickOnAddCategoryWithThisColorLink(){
        return driver.findElement(addCategoryWithThisColorLink);
    }

    public WebElement clickOnNeverMindLink(){
        return driver.findElement(neverMindLink);
    }

    public void waitForWarningMessage(){
        WebElement we = driver.findElement(warningMessage);
        for (int i=0; i<3; i++){
            if(we.isDisplayed())
                return;
        }
        System.out.println("Unable to find the web element"+we.getText());
    }
}
