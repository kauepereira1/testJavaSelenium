package br.ce.kaue.test;

import br.ce.kaue.page.campoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;

import static br.ce.kaue.core.driverFactory.getDriver;
import static br.ce.kaue.core.driverFactory.kilDriver;

public class desafioRegraDeNegocio {

    private campoTreinamentoPage page;
    private String nome;
    private String sobrenome;

    @Before
    public void inicializa() {
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado


        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html"); // Pegando o caminho de uma pagina pelo diretorio na onde que está em minha IDE
        page = new campoTreinamentoPage();
    }

    @After
    public void finalizar() {
        kilDriver();
    }

    @Test
    public void nomeObrigatorio() {

        page.cadastrar();
        getDriver().switchTo().alert();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Nome eh obrigatorio", msg);
        alert.accept();
        page.setNome("Kaue");
    }

    @Test
    public void sobrenomeObrigatorio() {

        page.setNome("Kaue");
        page.cadastrar();
        getDriver().switchTo().alert();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Sobrenome eh obrigatorio", msg);
        alert.accept();
        page.setSobrenome("Pereira");
    }

    @Test
    public void sexoObrigatorio() {

        page.setNome("Kaue");
        page.setSobrenome("Pereira");
        page.cadastrar();
        getDriver().switchTo().alert();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Sexo eh obrigatorio", msg);
        alert.accept();
        page.setSexoMasculino();
    }

    @Test
    public void validacaoVegetarianoOuNao() {

        page.setNome("Kaue");
        page.setSobrenome("Pereira");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setComidaVegetariana();
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", msg);
        alert.accept();
    }
}