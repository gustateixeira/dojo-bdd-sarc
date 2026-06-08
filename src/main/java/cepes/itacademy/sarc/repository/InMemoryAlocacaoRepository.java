package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Alocacao;
import cepes.itacademy.sarc.domain.Funcionario;
import cepes.itacademy.sarc.domain.Recurso;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryAlocacaoRepository implements AlocacaoRepository {
    private final List<Alocacao> alocaocoes = new ArrayList<>();

    @Override
    public void salvarAlocacao(Alocacao alocacao) {
        alocaocoes.add(alocacao);
    }

    @Override
    public boolean existeAlocacaoPorRecursoEData(Recurso recurso, LocalDate data) {
        return alocaocoes.stream()
                .anyMatch(a -> a.getRecurso().getId().equalsIgnoreCase(recurso.getId())
                        && a.getData().equals(data));
    }

    @Override
    public List<Alocacao> buscarPorFuncionarioEData(Funcionario funcionario, LocalDate data) {
        return alocaocoes.stream()
                .filter(a -> a.getFuncionario().getMatricula().equalsIgnoreCase(funcionario.getMatricula())
                        && a.getData().equals(data))
                .collect(Collectors.toList());
    }
}
