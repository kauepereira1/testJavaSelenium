package br.ce.kaue.core;

import org.junit.After;

import static br.ce.kaue.core.driverFactory.kilDriver;

public class baseTest {
    @After
    public void finalizar(){
        kilDriver(); // chamando um metodo que foi criado em outra classe para fechar o navegador com quit
    }
}
