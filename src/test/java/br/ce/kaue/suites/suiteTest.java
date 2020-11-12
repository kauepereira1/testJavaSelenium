package br.ce.kaue.suites;

import br.ce.kaue.test.desafioCadastro;
import br.ce.kaue.test.desafioRegraDeNegocio;
import br.ce.kaue.test.testCampo;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// Suite de teste, escolhendo qual test quero executar
@RunWith(Suite.class)
@Suite.SuiteClasses({
        desafioCadastro.class,
        desafioRegraDeNegocio.class,
        testCampo.class
})
public class suiteTest {

}
