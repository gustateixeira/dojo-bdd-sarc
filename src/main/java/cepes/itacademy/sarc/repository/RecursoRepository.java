package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.Recurso;
import java.util.Optional;

public interface RecursoRepository {
    Optional<Recurso> buscaPorId(String id);
}
