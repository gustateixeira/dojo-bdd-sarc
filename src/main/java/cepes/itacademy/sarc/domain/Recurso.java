package cepes.itacademy.sarc.domain;

public abstract class Recurso {
    private final String id;

    public Recurso(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
