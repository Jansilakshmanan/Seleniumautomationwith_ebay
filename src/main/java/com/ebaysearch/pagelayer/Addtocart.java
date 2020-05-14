package com.ebaysearch.pagelayer;

import com.ebaysearch.baselayer.Base;
import com.ebaysearch.utilitylayer.Testutil;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class Addtocart extends Base {

    @FindBy(xpath = "//*[@class='gh-tb ui-autocomplete-input']")
    WebElement searchbar;
    @FindBy(xpath = "//*[@id='gh-cat']")
    WebElement categorydrpdown;
    @FindBy(xpath = "//*[@class='ui-menu-item ghAC_visible']")
    List<WebElement> lstcategories;
    @FindBy(xpath = "//*[@class='s-item__link']")
    List<WebElement> lstproducts;

   Testutil util = new Testutil();


    public Addtocart() {


        PageFactory.initElements(driver, this);
    }


    public void searchincategories( String Search_text, String Suggestionlist_item,  String color) throws InterruptedException {


        Select s = new Select(categorydrpdown);
        s.selectByVisibleText("All Categories");
        //gets search_text parameter from dataprovider
        searchbar.sendKeys(Search_text);
        List<WebElement> lstcategories = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']"));
        for (int i = 0; i < lstcategories.size(); i++) {
            try {

                WebElement element = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']")).get(i);
                //suggestionlist_item parameter is from dataprovider
                if ((element.getText()).equalsIgnoreCase(Suggestionlist_item)) {
                    element.click();
                    break;
                }
            } catch (Exception e) {

            }
        }


        List<WebElement> lst = driver.findElements(By.xpath("//*[@class='s-item__link']"));
        int noofprod = lst.size();
        int ran_no = util.randomnogenerator(noofprod);
        WebElement element = driver.findElements(By.xpath("//*[@class='s-item__link']")).get(ran_no);
        System.out.println("Product added to cart " + element.getText());
        element.click();


        Boolean isPlacebidPopulated = false;
        try {
            WebElement placebid = driver.findElement(By.xpath("//*[@id='bidBtn_btn']"));
            if (placebid.isDisplayed()) {
                driver.findElement(By.xpath("//*[@id='bidBtn_btn']"));
                driver.navigate().to("https://www.ebay.com/");

                isPlacebidPopulated = true;
            }
        } catch (NoSuchElementException e1) {
            System.out.println("handled no suchelement exception for placebid");

        }

        if (!isPlacebidPopulated) {
            try {

                WebElement e = driver.findElement(By.id("msku-sel-1"));
                Select s1 = new Select(e);
                int intcolor= Integer.parseInt(color);
                s1.selectByIndex(intcolor);


                try {

                    WebElement bulk = driver.findElement(By.xpath("//*[@id='vi-vpqp-pills-2']"));
                    bulk.click();
                    Thread.sleep(3000);

                } catch (NoSuchElementException e2) {
                    System.out.println("handled no suchelement exception for bulk savings");
                }


            } catch (NoSuchElementException e) {
                System.out.println("handled no suchelement exception");
            } finally {
                driver.findElement(By.id("atcRedesignId_btn")).click();
            }
        }
    }
}



