package ebaytest;

import com.ebaysearch.baselayer.Base;
import com.ebaysearch.pagelayer.Addtocart;
import com.ebaysearch.utilitylayer.Testutil;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;


public class Addtocarttest extends Base {
    Testutil util;
    Addtocart cart;

    public Addtocarttest()
    {
        super();
        util=new Testutil();
        cart=new Addtocart(); }

    @BeforeMethod
    public void setup()

    {
        initialization();
    }

    @DataProvider
    public Object[][] gettestdata() throws IOException {

        Object[][] data = util.readexceldata();
        return data;
    }
    @Test(priority = 1, dataProvider = "gettestdata")
    public void searchincategories(String Test_desc, String Test_name, String Search_text, String Suggestionlist_item, String cartitem_no, String color, String quantity, String Bulk_savings) throws InterruptedException {
                 cart.searchincategories(   Search_text,  Suggestionlist_item,   color);
    }

    @AfterTest
    public void teardown()
    {
        driver.quit();
    }

}


