package cepes.itacademy.sarc.repository;

import cepes.itacademy.sarc.domain.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRecursoRepository implements RecursoRepository {
    private final List<Recurso> recursos = new ArrayList<>();

    public InMemoryRecursoRepository() {
        recursos.add(new Notebook("CEPES1983", LocalDate.of(2023, 6, 1), "Dell Latitude"));
        recursos.add(new Notebook("CEPES1995", LocalDate.of(2023, 6, 1), "Dell Inspiron"));
        recursos.add(new Notebook("CEPES2017", LocalDate.of(2024, 2, 15), "Dell Alienware"));


        recursos.add(new Sala("512", 30, false));
        recursos.add(new Sala("511", 35, true));
        recursos.add(new Sala("401", 40, true));


        recursos.add(new Laboratorio("411", 30, "O que o BlueJ ja viu nesse lab o copilot nao tem armazenado"));
        recursos.add(new Laboratorio("312", 30, "Maquinas formatadas em janeiro de 2026, dual boot"));
        recursos.add(new Laboratorio("LAPRO", 60, "Duvidas peguntar para Giba"));
    }

    @Override
    public Optional<Recurso> buscaPorId(String id) {
        return recursos.stream()
                .filter(r -> r.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}
