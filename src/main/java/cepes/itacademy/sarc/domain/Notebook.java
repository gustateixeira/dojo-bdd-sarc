package cepes.itacademy.sarc.domain;

import java.time.LocalDate;

public class Notebook extends Recurso {

    private final LocalDate aquisicao;
    private String descricao;

    public Notebook(String patrimonio, LocalDate aquisicao, String descricao) {
        super(patrimonio);
        this.aquisicao = aquisicao;
        this.descricao = descricao;
    }

    public LocalDate getAquisicao() {return aquisicao;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}
}
