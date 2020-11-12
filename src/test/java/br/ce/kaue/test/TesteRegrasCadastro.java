package br.ce.kaue.test;

import br.ce.kaue.core.DSL;
import br.ce.kaue.core.baseTest;
import br.ce.kaue.page.campoTreinamentoPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static br.ce.kaue.core.driverFactory.getDriver;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends baseTest {

    @Parameterized.Parameter
    public String nome;
    @Parameterized.Parameter(value = 1)
    public String sobrenome;
    @Parameterized.Parameter(value = 2)
    public String sexo;
    @Parameterized.Parameter
            (value = 3)
    public List<String> comidas;
    @Parameterized.Parameter(value = 4)
    public String msg;
    private DSL dsl;
    private campoTreinamentoPage page;

    @Parameterized.Parameters
    public static Collection<Object[]> getCollection() {
        return Arrays.asList(new Object[][]{
                {"", "", "", Arrays.asList(), "Nome eh obrigatorio"},
                {"Kaue", "Pereira", "", Arrays.asList(), "Sexo eh obrigatorio"},
                {"Kaue", "Pereira", "Masculino", Arrays.asList("Carne", "Vegetariano"), "Tem certeza que voce eh vegetariano?"}
        });
    }

    @Before
    public void inicializa() {

        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html"); // Pegando o caminho de uma pagina pelo diretorio na onde que está em minha IDE
        page = new campoTreinamentoPage();
    }

    @Test
    public void deveValidarRegras() {
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if (sexo.equals("Masculino")) {
            page.setSexoMasculino();
        }
        if (sexo.equals("Feminino")) {
            page.setSexoFeminino();
        }
        if (comidas.contains("Carne")) page.setComidaCarne();
        if (comidas.contains("Pizza")) page.setComidaPizza();
        if (comidas.contains("Vegetariano")) page.setComidaVegetariana();
        page.cadastrar();
        Alert alert = getDriver().switchTo().alert();
        String msg = alert.getText();
        Assert.assertEquals(msg, msg);
    }
}

