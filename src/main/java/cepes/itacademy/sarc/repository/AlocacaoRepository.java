package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Alocacao;
import cepes.itacademy.sarc.domain.Funcionario;
import cepes.itacademy.sarc.domain.Recurso;

import java.time.LocalDate;
import java.util.List;

public interface AlocacaoRepository {
    void salvarAlocacao(Alocacao alocacao);

    boolean existeAlocacaoPorRecursoEData(Recurso recurso, LocalDate date);

    List<Alocacao> buscarPorFuncionarioEData(Funcionario funcionario, LocalDate date);

}
