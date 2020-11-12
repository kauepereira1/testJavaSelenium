package br.ce.kaue.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testXpath {
    private WebDriver driver;

    @Before
    public void inicializa(){
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado

        System.setProperty("webdriver.chrome.driver", "C:\\Temp\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html"); // Pegando o caminho de uma pagina pelo diretorio na onde que está em minha IDE

    }

    @After
    public void finalizar(){
//        driver.quit();
    }

    @Test
    public void test(){

        driver.findElement(By.xpath("//*[@id='elementosForm:nome']")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id='elementosForm:sobrenome']")).sendKeys("Test");
        driver.findElement(By.xpath("//input[@name='elementosForm:sexo' and @id='elementosForm:sexo:0']")).click();

    }
}
