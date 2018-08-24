package com.dotdashproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Author - Sam Mohammed
 * Date - 08/24/2018
 */

public class AbstractTestBase {
    public static WebDriver driver;
    public static Properties properties;

    protected String [] colorList = {
        "None",
        "Green",
        "Blue",
        "Cyan",
        "Yellow",
        "Purple",
        "Gray",
        "Burnt Orange"
    };

    protected String[] monthList = {
            "Jan",
            "Feb",
            "Mar",
            "Apr",
            "May",
            "Jun",
            "Jul",
            "Aug",
            "Sep",
            "Oct",
            "Nov",
            "Dec"
    };
    //method that reads properties from property file
    public String getProperties(String propertyName) throws IOException {
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\config.properties");
        properties.load(fileInputStream);
        return properties.getProperty(propertyName);
    }

    public WebDriver initializeDriver() throws IOException{
        String browserName = getProperties("browser");
        if (browserName.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return  driver;
    }
    public String getRandomItemName(){
        int randomValue = ThreadLocalRandom.current().nextInt(1111, 9999);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Item - ");
        stringBuilder.append(randomValue);
        return stringBuilder.toString();
    }

    public String getRandomCategoryName(){
        int randomValue = ThreadLocalRandom.current().nextInt(111, 9999);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Category ");
        stringBuilder.append(randomValue);
        return stringBuilder.toString();
    }

    public static String randomSelectString(String [] arrayOfStrings) {
        Random random = new Random();
        int selector = random.nextInt(arrayOfStrings.length);
        return arrayOfStrings[selector];
    }

    public String getRandomDate(){
        int randDay = ThreadLocalRandom.current().nextInt(1, 28);
        String randMonth = randomSelectString(monthList);
        int randYear = ThreadLocalRandom.current().nextInt(2018, 2019);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(randDay);
        stringBuilder.append("/");
        stringBuilder.append(randMonth);
        stringBuilder.append("/");
        stringBuilder.append(randYear);
        return stringBuilder.toString();
    }
}
