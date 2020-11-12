package br.ce.kaue.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static br.ce.kaue.core.driverFactory.getDriver;

public class DSL {


    public void escreve(String id_campo, String texto) {
        getDriver().findElement(By.id(id_campo)).sendKeys(texto); // Procurando o elemento pelo id e passando o valor para o mesmo
    }

    public String obterValorCampo(String id_campo) {
        return getDriver().findElement(By.id(id_campo)).getAttribute("value"); // Procurando o elemento pelo seu id e pegando o valor do seu atributo "value"
    }

    public void clicarRadio(By by) {
        getDriver().findElement(by).click(); // Clicando no Radio Button
    }

    public void clicarRadio(String id) {
        clicarRadio(By.id(id));
    }

    public boolean radioMarcado(String id) {
        return getDriver().findElement(By.id(id)).isSelected(); // Procurando o radio button pelo seu elemento e verificar se está selecionado
    }

    public void clicarCheckBox(String id) {
        getDriver().findElement(By.id(id)).click();
    }

    public boolean checkMarcado(String id) {
        return getDriver().findElement(By.id(id)).isSelected();
    }

    public void selecionarCombo(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id)); // Elemento de listox
        Select combo = new Select(element); // Instanciando e passando o argumento do Elemento encontrado
        combo.selectByVisibleText(valor); // Buscando o elemento pelo valor visivel em tela
    }

    public void selecionarComboPrime(String radical, String valor) {
        clicarRadio(By.xpath("//*[@id='" + radical + "_label']/../..//span"));
        clicarRadio(By.xpath("//*[@id='" + radical + "_items']//li[.='" + valor + "']"));
    }

    public String obterValorCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id)); // Elemento de listox
        Select combo = new Select(element); // Instanciando e passando o argumento do Elemento encontrado
        return combo.getFirstSelectedOption().getText(); // pegando o valor do Combo
    }

    public void clicarBotao(String id) {
        getDriver().findElement(By.id(id)).click();
    }

    public void clicarLink(String link) {
        getDriver().findElement(By.linkText(link)).click(); // Buscando o elemento e clicando no mesmo
    }

    public String obterValorLink(String id) {
        return getDriver().findElement(By.id(id)).getText(); // Pegando o valor texto do link
    }

    public String obterTexto(By by) {
        return getDriver().findElement(by).getText();
    }

    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    public void entrarFrame(String id) {
        getDriver().switchTo().frame("frame1"); // Interagindo com Frame
        getDriver().findElement(By.id("frameButton")).click();
        getDriver().switchTo().alert(); // Interagindo com o alert da tela
        Alert alert = getDriver().switchTo().alert();
    }

    public Object executarJS(String cmd, Object... param) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(cmd, param);

//        js.executeScript("alert('Testando js via selenium')");
//        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
//        js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
    }

    public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela) {

        //procurar coluna do registro
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);

        //encontrar a linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idColuna);

        //procurar coluna do botão
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

        //clicar no botão da celula encontrada
        WebElement celula = tabela.findElement(By.xpath(".//tr[" + idLinha + "]/td[" + idColunaBotao + "]"));
        celula.findElement(By.xpath(".//input")).click();

    }

    private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td[" + idColuna + "]"));
        int idLinha = -1;
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equals(valor)) {
                idLinha = i + 1;
                break;

            }
        }
        return idLinha;
    }

    private int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equals(coluna)) {
                idColuna = i + 1;
                break;
            }
        }
        return idColuna;
    }
}