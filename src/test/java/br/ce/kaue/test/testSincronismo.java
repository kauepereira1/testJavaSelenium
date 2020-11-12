package br.ce.kaue.test;

import br.ce.kaue.core.DSL;
import br.ce.kaue.page.campoTreinamentoPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static br.ce.kaue.core.driverFactory.getDriver;

public class testSincronismo {

    private DSL dsl;
    private campoTreinamentoPage page;

    @Before
    public void inicializa() {
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado


        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html"); // Pegando o caminho de uma pagina pelo diretorio na onde que está em minha IDE
        dsl = new DSL(); // Instanciando a classe DLS e passando o valor do driver
        page = new campoTreinamentoPage();
    }

    @After
    public void finalizar() {
//        kilDriver();
    }

    @Test
    public void deveUtilizarEsperaFixa() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        Thread.sleep(5000);
        dsl.escreve("novoCampo", "Deu certo?");
    }

    @Test
    public void deveUtilizarEsperaImplicita() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dsl.clicarBotao("buttonDelay");
        dsl.escreve("novoCampo", "Deu certo?");
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @Test
    public void deveUtilizarEsperaExplicita() throws InterruptedException {
        dsl.clicarBotao("buttonDelay");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo"))); // Vai apenas esperar por esse campo
        dsl.escreve("novoCampo", "Deu certo?");
    }
}