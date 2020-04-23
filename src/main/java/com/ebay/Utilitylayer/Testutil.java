package com.ebay.Utilitylayer;

import com.ebay.Baselayer.TestBase;
import org.openqa.selenium.JavascriptExecutor;

public class Testutil extends TestBase {
    public void scrolldownwithjs(int X,int Y)
    {
        String jsscript=String.format("window.scrollBy(%d,%d)",X,Y);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript(jsscript);
    }
}
