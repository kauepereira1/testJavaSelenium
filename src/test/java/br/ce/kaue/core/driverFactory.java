package br.ce.kaue.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driverFactory {
    private static WebDriver driver;

    private driverFactory (){ }

    public static WebDriver getDriver(){
        if(driver == null){
            System.setProperty("webdriver.chrome.driver", "C:\\Temp\\drivers\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }
    public static void kilDriver(){
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
