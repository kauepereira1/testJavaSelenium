package br.ce.kaue.test;

import br.ce.kaue.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testFramesEJanelas {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado

        System.setProperty("webdriver.chrome.driver", "C:\\Temp\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html"); // Pegando o caminho de uma pagina pelo diretorio na onde que está em minha IDE

    }

    @After
    public void finalizar() {
        driver.quit();
    }

    @Test
    public void deveIntegirComFrames() {

        driver.switchTo().frame("frame1"); // Interagindo com Frame
        driver.findElement(By.id("frameButton")).click();
        driver.switchTo().alert(); // Interagindo com o alert da tela
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText(); // Atribuindo o valor do alert para a variável msg
        Assert.assertEquals("Frame OK!", msg); // Verificação se de fato o alert tem a mensagem informada
        alert.accept(); // Clicando em OK do alert
        driver.switchTo().defaultContent(); // Voltando para o foco da pagina principal
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
    }

    @Test
    public void deveIntegirComJanelas() {

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup"); // Interagindo com popup
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");
        driver.close(); // Fechando o Popoup
        driver.switchTo().window(""); // Voltando para pagina principal do aplicação
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo");
    }

    @Test
    public void deveIntegirComJanelasSemTitulo() {

        driver.findElement(By.id("buttonPopUpEasy")).click();
//      System.out.println(driver.getWindowHandle());
//      System.out.println(driver.getWindowHandles()); // Achar os id dos frames que estão sem nome
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]); // Interagindo com o Popup sem nome
        driver.findElement(By.tagName("textarea")).sendKeys("deu certo");
        driver.close(); // Fechando o Popoup
        driver.switchTo().window("");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("deu certo");

    }

    @Test
    public void deveInteragirComFrameEscondio(){
    }
}