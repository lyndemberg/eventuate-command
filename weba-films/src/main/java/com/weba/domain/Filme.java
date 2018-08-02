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
    private ArquivoDisco arquivoDisco;
    private Status status;

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", arquivoDisco=" + arquivoDisco +
                ", status=" + status +
                '}';
    }
}
