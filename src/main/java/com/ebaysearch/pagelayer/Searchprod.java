package com.ebaysearch.pagelayer;

import com.ebaysearch.baselayer.Base;
import com.ebaysearch.utilitylayer.Testutil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Searchprod extends Base {

    //Web elements using Page factory
    @FindBy(xpath = "//*[@class='gh-tb ui-autocomplete-input']")
    WebElement searchbar;
    @FindBy(xpath = "//*[@id='gh-cat']")
    WebElement categorydrpdown;
    Testutil util = new Testutil();

    public Searchprod() {

        //initializing elements using constructor
        PageFactory.initElements(driver, this);
    }

    public void searchincategories() {

        //importing select class to select from dropdown
        Select s = new Select(categorydrpdown);
        s.selectByVisibleText("All Categories");
        searchbar.sendKeys("watches");

        //Getting all webelements in a list and iterating
        List<WebElement> lstcategories = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']"));
        for (int i = 0; i < lstcategories.size(); i++) {
            try {
                //since,the DOM structure will be changed,
                //webelements are again iterated inside the for loop to avoid stalelement exception

                WebElement element = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']")).get(i);
                String text = element.getText();
            //iteartes each element in the list and checks its text with i/p text,if so element is clicked
                if (text.equalsIgnoreCase("watches women")) {
                    System.out.println("text is "+text);
                    element.click();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception-Element not found");
            }
        }
    }
    //get nth product from the list which is a dynamic value
    public void getnthproduct(int index) {

        String nthprod = driver.findElements(By.xpath("//*[@class='s-item__link']")).get(index).getText();
        System.out.println("nthprod is" + nthprod);
    }

    public List<WebElement> getallproducts() {

        List<WebElement> lstproducts = driver.findElements(By.xpath("//*[@class='s-item__link']"));
        return lstproducts;
    }

    //Scrolling down the page using Action class
    public void getallproductsviascrolldown() throws InterruptedException {
        List<WebElement> lstproducts = getallproducts();
        Actions act = new Actions(driver);
        for (int i = 0; i < lstproducts.size(); i++) {
            WebElement element = driver.findElements(By.xpath("//*[@class='s-item__link']")).get(i);
            String text = element.getText();
            act.moveToElement(element).build().perform();
            System.out.println("index: " + i + " text" + " " + text);
            Thread.sleep(1000);
        }
    }



    //scrolling down the page using javascript and prints last product in the list
    public void scrolldownwithjs() {

        List<WebElement> lstproducts = getallproducts();
        for (int i = 0; i < lstproducts.size(); i++) {
            WebElement element = driver.findElements(By.xpath("//*[@class='s-item__link']")).get(i);
            int prodno = (lstproducts.size()) - 1;
            int X = element.getLocation().x;
            int Y = element.getLocation().y;
            util.scrolldownwithjs(X, Y);//calling the scroll down function
            if (i == prodno) {
                System.out.println("Last product" + i + " " + element.getText());
            }
        } }
}

