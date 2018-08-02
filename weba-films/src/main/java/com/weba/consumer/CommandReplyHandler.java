package com.weba.consumer;

import com.weba.commands.EfetuarReservaCommand;
import com.weba.domain.Filme;
import com.weba.domain.Status;
import com.weba.service.FilmeService;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandHandlersBuilder;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.commands.consumer.PathVariables;
import io.eventuate.tram.messaging.common.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class CommandReplyHandler {

    private Logger log = LoggerFactory.getLogger(CommandReplyHandler.class);

    private String commandChannel;

    @Autowired
    private FilmeService filmeService;

    public CommandReplyHandler(String commandChannel) {
        this.commandChannel = commandChannel;
    }

    public CommandHandlers getCommandHandlers() {
        return CommandHandlersBuilder.
                fromChannel(commandChannel)
                .onMessage(EfetuarReservaCommand.class, this::finalizarStatusFilme)
                .build();
    }

    public Message finalizarStatusFilme(CommandMessage<EfetuarReservaCommand> cm, PathVariables pv) {
        Filme filme = filmeService.buscarPorId(cm.getCommand().getIdFilme());
        filme.setStatus(Status.valueOf(cm.getCommand().getStatus()));
        filmeService.atualizarFilme(filme);

        log.info("Filme %s - Status da reserva: %s",filme.getNome(),filme.getStatus());

        return withSuccess();
    }



}
