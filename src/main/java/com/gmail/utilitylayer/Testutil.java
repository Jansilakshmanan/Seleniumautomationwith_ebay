package com.gmail.utilitylayer;

import com.gmail.baselayer.Base;
import org.openqa.selenium.JavascriptExecutor;

public class Testutil extends Base {

    public void scrolldownwithjs(int X,int Y)
    {
         String jsscript=String.format("window.scrollBy(%d,%d)",X,Y);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript(jsscript);
    }
}
