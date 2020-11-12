package br.ce.kaue.test;

import br.ce.kaue.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static br.ce.kaue.core.driverFactory.getDriver;
import static br.ce.kaue.core.driverFactory.kilDriver;

public class testPrime {

    private DSL dsl;


    @Before
    public void inicializa() {
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado

        dsl = new DSL();
    }

    @After
    public void finalizar() {
        kilDriver();
    }

    @Test
    public void deveInteragirComRadioPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
        dsl.clicarRadio(By.xpath("//input[@id='j_idt728:console:0']/../..//span"));
        Assert.assertTrue(dsl.radioMarcado("j_idt728:console:0")); // Verificando se o radio está marcado
        dsl.clicarRadio(By.xpath("//label[.='PS4']/..//span"));
    }

    @Test
    public void deveInteragirComSelectPrime() {
        getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
        dsl.selecionarComboPrime("j_idt728:console", "PS4");
        Assert.assertEquals("PS4", dsl.obterTexto("j_idt728:console_label"));
    }
}
