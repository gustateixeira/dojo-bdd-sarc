package cepes.itacademy.sarc.service;

import cepes.itacademy.sarc.domain.*;
import cepes.itacademy.sarc.repository.AlocacaoRepository;
import java.time.LocalDate;

public class AlocacaoService {
    private final AlocacaoRepository repository;

    public AlocacaoService(AlocacaoRepository repository) {
        this.repository = repository;
    }

    public Alocacao alocarRecurso(Funcionario funcionario, Recurso recurso, LocalDate data) {
        Alocacao novaAlocacao = new Alocacao(funcionario, recurso, data);
        repository.salvarAlocacao(novaAlocacao);
        return novaAlocacao;
    }
}
