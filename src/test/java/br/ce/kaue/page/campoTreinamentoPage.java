package br.ce.kaue.page;

import br.ce.kaue.core.basePage;
import org.openqa.selenium.By;

public class campoTreinamentoPage extends basePage {


    public void setNome(String nome) {
        dsl.escreve("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome) {
        dsl.escreve("elementosForm:sobrenome", sobrenome);
    }

    public void setSexoMasculino() {
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setSexoFeminino() {
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    public void setComidaPizza() {
        dsl.clicarCheckBox("elementosForm:comidaFavorita:2");
    }

    public void setComidaCarne() {
        dsl.clicarCheckBox("elementosForm:comidaFavorita:0");
    }

    public void setComidaVegetariana() {
        dsl.clicarCheckBox("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor) {
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }

    public void setEsportes(String valor) {
        dsl.selecionarCombo("elementosForm:esportes", valor);
    }

    public void cadastrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public String obterResultadoCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='resultado']/span"));
    }

    public String obterNomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descNome']/span"));
    }

    public String obterSobrenomeCadastro() {
        return dsl.obterTexto(By.xpath("//*[@id='descSobrenome']/span"));
    }
}
