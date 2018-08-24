package com.dotdashproject;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import static java.lang.Thread.sleep;

/**
 * Author - Sam Mohammed
 * Date - 08/25/2018
 */

public class IndexPageTest extends AbstractTestBase{
    @BeforeMethod
    public void initialize() throws IOException{
        driver = initializeDriver();
    }

    @Test
    public void addTODO_Item_To_TODOList() throws IOException, InterruptedException {
        driver.get(getProperties("URL"));
        IndexPage indexPage = new IndexPage(driver);
        indexPage.verifyTitle();
        String todo = getRandomItemName();
        indexPage.enterTodoItem().sendKeys(todo);
        indexPage.selectCategory("College");
        indexPage.clickAddButton().click();
        sleep(1000);
        indexPage.verify_If_TODO_Item_IsAdded(todo);
    }

    @Test
    public void addTODO_Item_To_TODOList_WithDueDate() throws IOException, InterruptedException {
        driver.get(getProperties("URL"));
        IndexPage indexPage = new IndexPage(driver);
        indexPage.verifyTitle();
        String todo = getRandomItemName();
        indexPage.enterTodoItem().sendKeys(todo);
        indexPage.selectCategory("College");
        indexPage.setDueDate(getRandomDate());
        indexPage.clickAddButton().click();
        sleep(1000);
        indexPage.verify_If_TODO_Item_IsAdded(todo);
    }

    @Test void addCategoryName_To_CategoryList() throws IOException, InterruptedException{
        driver.get(getProperties("URL"));
        IndexPage indexPage = new IndexPage(driver);
        indexPage.verifyTitle();
        String category = getRandomCategoryName();
        indexPage.enterCategoryTextBox().sendKeys(category);
        String color = randomSelectString(colorList);
        indexPage.selectColor(color);
        Boolean isPresent = indexPage.isColorAlreadyExist(color);
        if(isPresent) {
            if (color != "None") {
                indexPage.clickAddCategoryButton().click();
                TodoPage todoPage = new TodoPage(driver);
                sleep(1000);
                todoPage.clickOnAddCategoryWithThisColorLink().click();
            }
        }else
            indexPage.clickAddCategoryButton().click();
        indexPage.verify_If_CategoryName_IsAdded(category);
    }

    @Test
    public void delete_Selected_TODOItem_From_ItemList() throws IOException, InterruptedException{
        driver.get(getProperties("URL"));
        IndexPage indexPage = new IndexPage(driver);
        indexPage.verifyTitle();
        String deletedItem = indexPage.clickFirstTODOItemFromItemList();
        sleep(1000);
        indexPage.clickRemoveButton().click();
        indexPage.verify_If_ItemName_IsDeleted(deletedItem);

    }

    @Test
    public void delete_selected_Category_From_CategoryList() throws IOException, InterruptedException {
        driver.get(getProperties("URL"));
        IndexPage indexPage = new IndexPage(driver);
        indexPage.verifyTitle();
        String deletedCategory = indexPage.clickFirstCategoryFromCategoryList();
        sleep(1000);
        DelCatPage delCatPage = new DelCatPage(driver);
        delCatPage.clickOnYesLink().click();
        indexPage.verify_If_CategoryName_IsDeleted(deletedCategory);
    }

    @AfterMethod
    public void closeDriver(){
        driver.close();
    }
}
