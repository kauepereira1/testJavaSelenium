package br.ce.kaue.test;

import br.ce.kaue.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static br.ce.kaue.core.driverFactory.*;

public class testAjax {

    private DSL dsl;


    @Before
    public void inicializa() {
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado


        getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
        dsl = new DSL();
    }

    @After
    public void finalizar() {
        kilDriver();
    }

    @Test
    public void interagirAjax() {

        dsl.escreve("j_idt727:name", "test");
        dsl.clicarBotao("j_idt727:j_idt730");
        WebDriverWait wait = new WebDriverWait(getDriver(), 30); // Utilizando o metodo de espera Explicita
        wait.until(ExpectedConditions.textToBe(By.id("j_idt727:display"), "test")); // // Vai apenas esperar por esse campo
        Assert.assertEquals("test", dsl.obterTexto("j_idt727:display"));

    }
}

