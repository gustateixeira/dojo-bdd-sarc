package steps;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.*;
import cepes.itacademy.sarc.service.AlocacaoService;
import io.cucumber.java.pt.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AlocacaoSteps {

    private FuncionarioRepository funcionarioRepo;
    private RecursoRepository recursoRepo;
    private AlocacaoRepository alocacaoRepo;

    private AlocacaoService alocacaoService;

    private Funcionario funcionarioAtual;
    private Recurso recursoAtual;
    private LocalDate dataAlocacao;
    private Alocacao alocacaoConfirmada;
    private Exception excecaoLancada;

    @Dado("que existem recursos e funcionários cadastrados no sistema")
    public void que_existem_recursos_e_funcionarios_cadastrados_no_sistema() {
        funcionarioRepo = new InMemoryFuncionarioRepository();
        recursoRepo = new InMemoryRecursoRepository();
        alocacaoRepo = new InMemoryAlocacaoRepository();

        alocacaoService = new AlocacaoService(alocacaoRepo);
    }

    @Dado("que o funcionário {string} deseja alocar o notebook {string}")
    public void que_o_funcionario_deseja_alocar_o_notebook(String matricula, String patrimonio) {
        funcionarioAtual = funcionarioRepo.buscaPorMatricula(matricula).orElseThrow();
        recursoAtual = recursoRepo.buscaPorId(patrimonio).orElseThrow();
    }

    @Quando("ele solicita a reserva para a data {string}")
    public void ele_solicita_a_reserva_para_a_data(String data) {
        dataAlocacao = LocalDate.parse(data);

        try {
            alocacaoConfirmada = alocacaoService.alocarRecurso(funcionarioAtual, recursoAtual, dataAlocacao);
        } catch (Exception e) {
            excecaoLancada = e;
        }
    }

    @Então("o sistema deve confirmar a alocação com sucesso")
    public void o_sistema_deve_confirmar_a_alocacao_com_sucesso() {
        assertNotNull(alocacaoConfirmada, "A alocação deveria ter sido confirmada.");
        assertNull(excecaoLancada, "Nenhuma exceção deveria ter sido lançada.");
    }
}
