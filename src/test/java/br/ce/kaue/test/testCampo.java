package br.ce.kaue.test;

import br.ce.kaue.core.DSL;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static br.ce.kaue.core.driverFactory.getDriver;

public class testCampo {

    private WebDriver driver;
    private DSL dsl;

    @Before
    public void inicializa() {
//      driver.manage().window().setPosition(new Point(100,100)); // Definindo a posição do Browser
//      driver.manage().window().setSize(new Dimension(1200, 600)); // Definindo o tamnho do Browser
//      driver.manage().window().maximize(); // Definindo para o Browser ser maximizado

        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html"); // Pegando o caminho de uma pagina pelo diretorio na onde que está em minha IDE
        dsl = new DSL(); // Instanciando a classe DLS e passando o valor do driver
    }

    @After
    public void finalizar() {
//        kilDriver();
    }

    @Test
    public void testTextField() {
        dsl.escreve("elementosForm:nome", "Teste Escrita");
        Assert.assertEquals("Teste Escrita", dsl.obterValorCampo("elementosForm:nome"));  // pegando o valor que foi digitado no elemento
    }

    @Test
    public void deveInteragirComTextArea() {
        dsl.escreve("elementosForm:sugestoes", "Teste Area");
        String texto = dsl.obterValorCampo("elementosForm:sugestoes"); // pegando o valor que foi digitado no elemento e guardando na variavel texto
        Assert.assertEquals("Teste Area", texto); // Verificação se o valor que foi digiatado no elemento foi Teste Area
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assert.assertTrue(dsl.radioMarcado("elementosForm:sexo:0")); // Verificando se radio button foi selecionado
    }

    @Test
    public void deveInteragirComCheckBox() {
        dsl.clicarCheckBox("elementosForm:comidaFavorita:1");
        Assert.assertTrue(dsl.checkMarcado("elementosForm:comidaFavorita:1")); // Verificação do CheckBox está checado
    }

    @Test
    public void deveIntegirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
        Assert.assertEquals("Mestrado", dsl.obterValorCombo("elementosForm:escolaridade")); // Pegando o valor do Elemento e comparando se de fato é igual ao ultimo combo
    }

    @Test
    public void deveVerificarValoresCombo() {
        WebElement element = getDriver().findElement(By.id("elementosForm:escolaridade")); // Elemento de listox
        Select combo = new Select(element); // Instanciando e passando o argumento do Elemento encontrado
        List<WebElement> options = combo.getOptions(); // Lista de Elementos
        Assert.assertEquals(8, options.size()); // Verificando o tamanho da lista

        // Realizando uma condição se o valor de option for Mestrado então o encontrou deve retornar verdadeiro
        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) ;
            encontrou = true;
            break;
        }
    }

    @Test
    public void deveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        WebElement element = getDriver().findElement(By.id("elementosForm:esportes")); // Elemento de listox
        Select combo = new Select(element); // Instanciando e passando o argumento do Elemento encontrado

        // Pegando o valor da lista que foi preenchida acima com os 3 valores selecionados e validando se de fato foi 3
        List<WebElement> options = combo.getAllSelectedOptions();
        Assert.assertEquals(3, options.size()); /// Verificação se tem 3 elementos selecionados

        combo.deselectByVisibleText("Corrida"); // desselecionando Corrida
        options = combo.getAllSelectedOptions();
        Assert.assertEquals(2, options.size()); // Validando se no momento tem apenas 2 Elementos selecionado.
    }

    @Test
    public void deveIteragirComBotoes() {

        dsl.clicarBotao("buttonSimple");

        // Chamando a instância WebElement do retorno do find e passando o valor do mesmo para botao
        WebElement botao = getDriver().findElement(By.id("buttonSimple"));

        // Pegando o valor do Atributo value do botão e verificando se o valor do mesmo é igual a Obrigado
        Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveIteragirComLinks() {
        dsl.clicarLink("Voltar"); // Especionando o elemento de link traves do linkText
        Assert.assertEquals("Voltou!", dsl.obterValorLink("resultado")); // Verificação se o valor do texto do link é de fato Voltou
    }

    @Test
    public void deveBuscarTextosNaPagina() {

//          Verificando se de fato no Body tem o texto informado
//        Assert.assertTrue(driver.findElement(By.tagName("body"))
//                .getText().contains("Campo de Treinamento"));

//        Verificando se de fato na tag tem o texto informado
        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
    }

    @Test
    public void testJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        js.executeScript("alert('Testando js via selenium')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");

        WebElement element = getDriver().findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments", element);
    }

    @Test
    public void deveClicarBotaoTabela() {
        dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
    }
}