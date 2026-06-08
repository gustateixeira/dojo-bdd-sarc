package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Funcionario;
import java.util.Optional;

public interface FuncionarioRepository {
    Optional<Funcionario> buscaPorMatricula(String matricula);
}
