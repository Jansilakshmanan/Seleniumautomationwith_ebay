package com.gmail.pagelayer;

import com.gmail.baselayer.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class Googlesuggestion extends Base {
    public Googlesuggestion()
    {
        initialization();
        driver.get("https://www.google.ca/");
    }

    @Test
    public void googlesearch()
    {
        driver.findElement(By.xpath("//*[@class='gLFyf gsfi']")).sendKeys("amazon");
        List<WebElement> lstsuggestion =driver.findElements(By.xpath("//*[@class='sbct']"));

        for( WebElement e:lstsuggestion)
        {

           if(e.getText().equalsIgnoreCase("amazon music"))
           {
               e.click();
               break;


           }
            System.out.println(e.getText());
        }


    }

}
