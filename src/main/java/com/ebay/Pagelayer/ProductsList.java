package com.ebay.Pagelayer;

import com.ebay.Baselayer.TestBase;
import com.ebay.Utilitylayer.Testutil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsList extends TestBase {
    @FindBy(xpath="//*[@class='gh-tb ui-autocomplete-input']")
    WebElement searchbar;
    @FindBy(xpath ="//*[@id='gh-cat']")
    WebElement categorydrpdown;
    @FindBy(xpath = "//*[@class='ui-menu-item ghAC_visible']")
    List<WebElement> lstcategories;
    @FindBy (xpath="//*[@class='s-item__link']")
    List<WebElement> lstproducts;
    Testutil util=new Testutil();
    public ProductsList()
    {
         super();
        PageFactory.initElements(driver,this);
    }
    @BeforeMethod
    public void setup()
    {
        initialization();
    }


    public  void searchincategories()
    {

        Select s=new Select(categorydrpdown);
        s.selectByVisibleText("All Categories");
        searchbar.sendKeys("watches");

            //*[@class='ghAC_sugg ui-corner-all']

        List<WebElement> lstcategories = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']"));
        //System.out.println("no.of categories"+lstcategories.size());


        for(int i=0;i<lstcategories.size();i++)
        {
            try {
               //String  text = driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']")).get(i).getText();
                WebElement element=driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']")).get(i);
                System.out.println("current list item : " + i + " " + element.getText());
//watches - Smart Watches
                if ((element.getText()).equalsIgnoreCase("watches for kids")) {
                    System.out.println(element.getText());
                   //WebElement element=driver.findElements(By.xpath("//*[@class='ui-menu-item ghAC_visible']")).get(i);

                    element.click();
                    break;
                }
            }
            catch(Exception e)
            {
                System.out.println("Exception-Element# " + i + " not found");
            }



            }


        }
        @Test(priority = 1)
        public void getcategoriesname()
        {
            searchincategories();

        }
        @Test(priority = 2)
    public void getnthproduct()
        {
            searchincategories();
           String nthprod=driver.findElements(By.xpath("//*[@class='s-item__link']")).get(0).getText();
           System.out.println("nthprod is"+nthprod);
        }
        @Test (priority = 3)
    public void getallproducts()
    {
        searchincategories();
        List<WebElement> lstproducts=driver.findElements(By.xpath("//*[@class='s-item__link']"));
        for(int i=0;i<lstproducts.size();i++)
        {
            String text=driver.findElements(By.xpath("//*[@class='s-item__link']")).get(i).getText();
            System.out.println("index: "+i+" text"+" " +text);
            System.out.println(lstproducts.size());

        }


    }
    @Test(priority = 4)
    public void scrolldownviajs()
    {
        searchincategories();
        List<WebElement> lstproducts=driver.findElements(By.xpath("//*[@class='s-item__link']"));
    }

@Test(priority = 5)
    public void scrolldownwithjs()
{
    searchincategories();
    List<WebElement> lstproducts=driver.findElements(By.xpath("//*[@class='s-item__link']"));
    for(int i=0;i< lstproducts.size();i++)
    {
       WebElement element= driver.findElements(By.xpath("//*[@class='s-item__link']")).get(i);
       int X=element.getLocation().x;
       int Y=element.getLocation().y;
        util.scrolldownwithjs(X,Y);
        int prodno=(lstproducts.size())-1;
        if(i==prodno)
        {
            System.out.println("Last product" +i + " "+element.getText());
        }


    }

}
}






