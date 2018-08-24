package com.dotdashproject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import java.util.List;

/**
 * Author - Sam Mohammed
 * Date - 08/25/2018
 */

public class IndexPage {
    public WebDriver driver;
    public IndexPage (WebDriver driver){
        this.driver = driver;
    }

    By titleName = By.xpath("//*[@id=\"label-first\"]/b");
    By removeButton = By.xpath("//input[@value='Remove']");
    By completeButton = By.xpath("//input[@value='Complete']");
    By allCheckBox = By.xpath("//input[@name='allbox']");
    By advanced = By.xpath("//a[text()='[Advanced]']");
    By todoTextBox = By.xpath("//input[@name='data']");
    By addButton = By.xpath("//input[@value='Add']");
    By categoryDropDown = By.xpath("//select[@name='category']");
    By dueDateDayDropDown = By.xpath("//select[@name='due_day']");
    By dueDateMonthDropDown = By.xpath("//select[@name='due_month']");
    By dueDateYearDropDown = By.xpath("//select[@name='due_year']");
    By categoryTextBox = By.xpath("//input[@name='categorydata']");
    By addCategoryButton = By.xpath("//input[@value='Add category']");
    By colorDropdown = By.xpath("//select[@name='colour']");


    public WebElement clickRemoveButton(){
        return driver.findElement(removeButton);
    }

    public WebElement getTitle(){
        return driver.findElement(titleName);
    }

    public WebElement clickCompleteButton(){
        return driver.findElement(completeButton);
    }
    public  void selectAllCheckBox(){
        driver.findElement(allCheckBox).click();
        Boolean isSelected = driver.findElement(allCheckBox).isSelected();
        if(isSelected)
            System.out.println("Toggle All checkbox is selected");
        else
            Assert.fail("Toggle All checkbox is not selected");
    }

    public WebElement clickAdvancedLink(){
        return driver.findElement(advanced);
    }

    public WebElement enterTodoItem(){
        return driver.findElement(todoTextBox);
    }

    public WebElement clickAddButton(){
        return driver.findElement(addButton);
    }

    public void selectCategory(String category){
        Select s = new Select(driver.findElement(categoryDropDown));
        s.selectByVisibleText(category);
    }

    public void selectDueDateDay(String dueDateDay){
        Select s = new Select(driver.findElement(dueDateDayDropDown));
        s.selectByVisibleText(dueDateDay);
    }

    public void selectDueDateMonth(String dueDateMonth){
        Select s = new Select(driver.findElement(dueDateMonthDropDown));
        s.selectByVisibleText(dueDateMonth);
    }

    public void selectDueDateYear(String dueDateYear){
        Select s = new Select(driver.findElement(dueDateYearDropDown));
    }

    public WebElement enterCategoryTextBox(){
        return driver.findElement(categoryTextBox);
    }

    public WebElement clickAddCategoryButton(){
        return driver.findElement(addCategoryButton);
    }

    public void setDueDate(String inputDate){
        String[] splitter = inputDate.split("/");
        String day = splitter[0];
        String month = splitter[1];
        String year = splitter[2];
        selectDueDateDay(day);
        selectDueDateMonth(month);
        selectDueDateYear(year);
    }

    public void selectColor(String color){
        Select s = new Select(driver.findElement(colorDropdown));
        s.selectByVisibleText(color);
        System.out.println("Color selected");
    }

    public void verifyTitle(){
        String actual = getTitle().getText();
        if(actual.equals("NSS-TODO List v 1.1"))
            System.out.println("Page Title matches to Expected value");
        else
            Assert.fail("Page Title doesn't match to expected value");
    }

    public void verify_If_TODO_Item_IsAdded(String itemName){
        List<WebElement> itemNames = driver.findElements(By.xpath("//div[@id='todos-content']//li"));
        for(WebElement element : itemNames){
            if(element.getText().toLowerCase().contains(itemName.toLowerCase())) {
                System.out.println("Item Name: " + itemName + " is present in TODO list");
                return;
            }
        }
        Assert.fail("Item Name: "+itemName+" is not in TODO list");
    }

    public void verify_If_CategoryName_IsAdded(String categoryName){
        List<WebElement> categoryNames = driver.findElements(By.xpath("//div[@class='controls']/a"));
        for(WebElement element : categoryNames){
            if(element.getText().toLowerCase().contains(categoryName.toLowerCase())) {
                System.out.println("Category Name: " + categoryName + " is present in category list");
                return;
            }
        }
        Assert.fail("Category Name: "+categoryName+" is not in the category list");
    }
    public void verify_If_CategoryName_IsDeleted(String categoryName){
        List<WebElement> categoryNames = driver.findElements(By.xpath("//div[@class='controls']/a"));
        for(WebElement element : categoryNames){
            if(element.getText().toLowerCase().contains(categoryName.toLowerCase())) {
                Assert.fail("Category Name: "+categoryName+" is not deleted ");
            }
        }
        System.out.println("Category Name: "+categoryName+" is deleted successfully");
    }

    public Boolean isColorAlreadyExist(String color){
        String colorCode;
        switch (color){
            case "Green": colorCode = "rgb(0, 255, 0)";
                break;
            case "Blue": colorCode = "rgb(0, 0, 255)";
                break;
            case "Cyan": colorCode = "rgb(0, 255, 255)";
                break;
            case "Yellow": colorCode = "rgb(255, 255, 0)";
                break;
            case "Purple": colorCode = "rgb(255, 0, 255)";
                break;
            case "Gray": colorCode = "rgb(192, 192, 192)";
                break;
            case "Burnt Orange": colorCode = "rgb(191, 87, 0)";
                break;
            default: colorCode = "someThing";
                break;
        }
        System.out.println("color -"+color+" and Color Code -"+colorCode);

        List<WebElement> categoryNames = driver.findElements(By.xpath("//div[@class='controls']/a"));
        for(WebElement element : categoryNames){
            String style = element.getAttribute("style").toString();
            if(style.contains(colorCode)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public String clickFirstCategoryFromCategoryList(){
        int i=0;
        String text=null;
        List<WebElement> categoryItems = driver.findElements(By.xpath("//div[@class='controls']/a"));
        for(WebElement element : categoryItems){
            if (i==1){
                text = element.getText();
                element.click();
                System.out.println("clicked on first category:-"+text);
                return text;
            }
            i++;
        }
        return text;
    }

    public String clickFirstTODOItemFromItemList(){
        int i=0;
        String text=null;
        List<WebElement> categoryItems = driver.findElements(By.xpath("//div[@id='todos-content']//li/input"));
        for(WebElement element : categoryItems){
            if (i==0){
                text = driver.findElement(By.xpath("//*[@id=\"todos-content\"]/form/ul/li[1]/span[1]")).getText();
                element.click();
                System.out.println("clicked on first Item:-"+text);
                return text;
            }
            i++;
        }
        return text;
    }

    public void verify_If_ItemName_IsDeleted(String itemName){
        List<WebElement> itemNames = driver.findElements(By.xpath("//div[@id='todos-content']//li"));
        for(WebElement element : itemNames){
            if(element.getText().toLowerCase().contains(itemName.toLowerCase())) {
                Assert.fail("Item Name: "+itemName+" is not deleted ");
            }
        }
        System.out.println("Item Name: "+itemName+" is deleted successfully");
    }
}
