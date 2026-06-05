package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExemploSteps {

    private boolean ambienteConfigurado = false;
    private boolean testesExecutados = false;

    @Dado("que o ambiente de desenvolvimento está configurado")
    public void que_o_ambiente_de_desenvolvimento_esta_configurado() {
        ambienteConfigurado = true;
    }

    @Quando("eu executo a suite de testes")
    public void eu_executo_a_suite_de_testes() {
        if (ambienteConfigurado) {
            testesExecutados = true;
        }
    }

    @Então("o sistema deve retornar uma mensagem de sucesso")
    public void o_sistema_deve_retornar_uma_mensagem_de_sucesso() {
        assertTrue(testesExecutados, "O ambiente não processou os testes corretamente.");
    }
}