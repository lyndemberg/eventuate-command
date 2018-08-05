package com.weba.commands;

import io.eventuate.tram.commands.common.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EfetuarReservaCommand implements Command {
    private Long idFilme;
    private Long tamanhoArquivo;

    public EfetuarReservaCommand() {
    }

    public EfetuarReservaCommand(Long idFilme, Long tamanhoArquivo) {
        this.idFilme = idFilme;
        this.tamanhoArquivo = tamanhoArquivo;
    }
}
