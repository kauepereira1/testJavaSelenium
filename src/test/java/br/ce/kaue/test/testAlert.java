package br.ce.kaue.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static br.ce.kaue.core.driverFactory.getDriver;
import static br.ce.kaue.core.driverFactory.kilDriver;

public class testAlert {
    @Test
    public void deveInteragirComAlertSimples() {

        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");
        getDriver().findElement(By.id("alert")).click();

        Alert alert = getDriver().switchTo().alert(); // Interagindo com alert
        String texto = alert.getText(); // Atribuindo o valor do alert para a variavel texto
        Assert.assertEquals("Alert Simples", texto); // Verificando se de fato o alert tem esse texo
        alert.accept(); // Clicando no OK do alert
        getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto); // Passando o valor do que foi atribuido ao texto para o elemento mencionado
        kilDriver();
    }

    @Test
    public void deveInteragirComAlertConfirm() {

        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        getDriver().findElement(By.id("confirm")).click();
        Alert alert = getDriver().switchTo().alert(); // Variavel alert recebendo o valor do elemento
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.accept();
        Assert.assertEquals("Confirmado", alert.getText());
        alert.accept();

        getDriver().findElement(By.id("confirm")).click();
        alert = getDriver().switchTo().alert(); // Variavel alert recebendo o valor do elemento
        Assert.assertEquals("Confirm Simples", alert.getText());
        alert.dismiss(); // Cancelar o alert
        Assert.assertEquals("Negado", alert.getText());
        alert.accept();
        kilDriver();

    }

    @Test
    public void deveInteragirComAlertPrompt() {

        getDriver().get("file:///" + System.getProperty("user.dir") + "\\src\\main\\resources\\componentes.html");

        getDriver().findElement(By.id("prompt")).click();
        Alert alert = getDriver().switchTo().alert();
        Assert.assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("18"); // Escrevendo no AlertPrompt
        alert.accept();
        Assert.assertEquals("Era 18?", alert.getText()); // Verificação do texto que está no alert
        alert.accept();
        Assert.assertEquals(":D", alert.getText());
        alert.accept();
        kilDriver();

    }
}
