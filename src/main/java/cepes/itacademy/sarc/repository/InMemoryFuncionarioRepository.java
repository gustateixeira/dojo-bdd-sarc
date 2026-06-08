package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Funcionario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryFuncionarioRepository implements FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();

    public InMemoryFuncionarioRepository() {
        funcionarios.add(new Funcionario("10002020", "Andre Oliveira", "Analista Administrativo", LocalDate.of(2020, 1, 1)));
        funcionarios.add(new Funcionario("20002026", "Gabriel Velloso", "Aluno de Mestrado", LocalDate.of(2025, 6, 12)));
        funcionarios.add(new Funcionario("20002024", "Natalya Goelzer", "Aluna de Doutorado", LocalDate.of(2024, 3, 1)));
    }

    @Override
    public Optional<Funcionario> buscaPorMatricula(String matricula) {
        return funcionarios.stream()
                .filter(f -> f.getMatricula().equalsIgnoreCase(matricula))
                .findFirst();
    }
}
