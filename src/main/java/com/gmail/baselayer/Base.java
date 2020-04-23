package com.gmail.baselayer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    String path="C:\\Users\\user\\Desktop\\backup 12112019\\jansi_javafiles_2\\selenium_assignment\\src\\main\\java\\com\\gmail\\configlayer\\config.properties";
   public static  Properties prop;
    public  static WebDriver driver;

public Base()
{
try {

    FileInputStream ip=new FileInputStream(path);
     prop=new Properties();
    prop.load(ip);
}
catch( IOException e)
{
    e.printStackTrace();
}
if(prop.getProperty("browser").equals("chrome"))
{
    System.setProperty("webdriver.chrome.driver", "C:\\jansi_webdrivers\\chromedriver_win32\\chromedriver.exe");
     driver=new ChromeDriver();
}



}

    public  static void initialization()
{
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
    driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();
    driver.get("https://www.ebay.com/");


}






}
