package com.weba.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Filme {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private ArquivoFilme arquivoFilme;
    private String idReserva;
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", arquivoFilme=" + arquivoFilme +
                ", idReserva=" + idReserva +
                ", status=" + status +
                '}';
    }
}
