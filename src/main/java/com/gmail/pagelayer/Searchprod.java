package com.gmail.pagelayer;

import com.gmail.baselayer.Base;
import com.gmail.utilitylayer.Testutil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Searchprod extends Base {


    @FindBy(xpath = "//*[@class='gh-tb ui-autocomplete-input']")
    WebElement searchbar;
    @FindBy(xpath = "//*[@id='gh-cat']")
    WebElement categorydrpdown;
    Testutil util = new Testutil();

    public Searchprod() {

        PageFactory.initElements(driver, this);
    }

    public void searchincategories() {

        Select s = new Select(categorydrpdown);
        s.selectByVisibleText("All Categories");
        searchbar.sendKeys("watches");
        List<WebElement> lstcategories = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']"));
        for (int i = 0; i < lstcategories.size(); i++) {
            try {
                WebElement element = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']")).get(i);
                String text = element.getText();

                if (text.equalsIgnoreCase("watches women")) {
                    System.out.println(text);
                    element.click();
                    break;
                }
            } catch (Exception e) {
                System.out.println("Exception-Element not found");
            }
        }
    }
    public void getnthproduct(int index) {

        String nthprod = driver.findElements(By.xpath("//*[@class='s-item__link']")).get(index).getText();
        System.out.println("nthprod is" + nthprod);
    }

    public List<WebElement> getallproducts() {

        List<WebElement> lstproducts = driver.findElements(By.xpath("//*[@class='s-item__link']"));
        return lstproducts;
    }

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

    public void scrolldownwithjs() {

        List<WebElement> lstproducts = getallproducts();
        for (int i = 0; i < lstproducts.size(); i++) {
            WebElement element = driver.findElements(By.xpath("//*[@class='s-item__link']")).get(i);
            int prodno = (lstproducts.size()) - 1;
            int X = element.getLocation().x;
            int Y = element.getLocation().y;
            util.scrolldownwithjs(X, Y);
            if (i == prodno) {
                System.out.println("Last product" + i + " " + element.getText());
            }
        } }
}

