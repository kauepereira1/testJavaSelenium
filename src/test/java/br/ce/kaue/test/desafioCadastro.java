package br.ce.kaue.test;

import br.ce.kaue.core.DSL;
import br.ce.kaue.core.baseTest;
import br.ce.kaue.page.campoTreinamentoPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static br.ce.kaue.core.driverFactory.getDriver;


public class desafioCadastro extends baseTest {

    private DSL dsl;
    private campoTreinamentoPage page;

    @Before
    public void inicializa() {
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado


        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html"); // Pegando o caminho de uma pagina pelo diretorio na onde que está em minha IDE
        dsl = new DSL();
        page = new campoTreinamentoPage();
    }

    @Test
    public void cadastroComSucesso() {

        page.setNome("Kaue");
        page.setSobrenome("Pereira");
        page.setSexoMasculino();
        page.setComidaPizza();
        page.setEscolaridade("Mestrado");
        page.setEsportes("Natacao");
        page.cadastrar();

        Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro()); // Verificação se o elemento é igual o informado
        Assert.assertEquals("Kaue", page.obterNomeCadastro()); // Verificação se o elemento é igual o informado
        Assert.assertEquals("Sobrenome: Pereira", dsl.obterTexto("descSobrenome"));
    }
}
