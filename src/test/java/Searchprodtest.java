import com.gmail.baselayer.Base;
import com.gmail.pagelayer.Searchprod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class Searchprodtest  extends Base{
    Searchprod sp;
    String actualtitle="watches auction | eBay";
    int Indexno=1;

    public Searchprodtest()
    {
        super();
        sp=new Searchprod();
    }

    @BeforeMethod
    public void setup()
    {
       initialization();

    }
    @Test(priority = 1)
    public void checkpagetitle()
    {

        sp.searchincategories();
        String expectedtitle=driver.getTitle();
        Assert.assertEquals(actualtitle,expectedtitle);

    }
   @Test(priority = 2)
   public void getcategoriesname()
   {
      sp.searchincategories();

   }
    @Test(priority = 3)
    public void getnthproducttest()
    {
        sp.searchincategories();
        sp.getnthproduct(Indexno);
    }
    @Test(priority = 4)
    public void getallproductstest() {
        sp.searchincategories();
        List<WebElement> lstproducts =sp.getallproducts();
        for (int i = 0; i < lstproducts.size(); i++) {
            String text = driver.findElements(By.xpath("//*[@class='s-item__link']")).get(i).getText();
            System.out.println("index: "+i+" text"+" " +text);

        }
        System.out.println(lstproducts.size());

    }
    @Test(priority = 5)
    public void getallproductsviascrolldowntest() throws InterruptedException {
        sp.searchincategories();
       sp.getallproductsviascrolldown();
    }
    @Test(priority = 5)
    public void scrolldownwithjs()
    {
        sp.searchincategories();
      sp.scrolldownwithjs();


    }
    @AfterTest
    public void teardown()
    {
        driver.quit();
    }





}
