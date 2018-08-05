package br.edu.ifpb.uploadservice.consumer;

import br.edu.ifpb.uploadservice.domain.ReservaEspaco;
import br.edu.ifpb.uploadservice.service.ReservaEspacoService;
import br.edu.ifpb.uploadservice.service.erros.NenhumaUnidadeComEspacoDisponivelException;
import com.weba.commands.EfetuarReservaCommand;
import io.eventuate.tram.commands.common.CommandReplyOutcome;
import io.eventuate.tram.commands.common.ReplyMessageHeaders;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandHandlersBuilder;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.commands.consumer.PathVariables;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.producer.MessageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class CommandConsumerHandler {

    @Autowired
    private ReservaEspacoService reservaEspacoService;

    private Logger log = LoggerFactory.getLogger(CommandConsumerHandler.class);

    private String commandChannel;

    public CommandConsumerHandler(String commandChannel) {
        this.commandChannel = commandChannel;
    }

    public CommandHandlers getCommandHandlers() {
        return CommandHandlersBuilder.
                fromChannel(commandChannel).
                onMessage(EfetuarReservaCommand.class, this::efetuaReservaEspaco)
                .build();
    }

    public Message efetuaReservaEspaco(CommandMessage<EfetuarReservaCommand> cm, PathVariables pv) {
        MessageBuilder builder = MessageBuilder.withPayload("");
        Map<String,String> extraHeaders = new HashMap<>();
        extraHeaders.put("idFilme",cm.getCommand().getIdFilme().toString());
        try {
            log.info("Solicitacao de reserva de espaço recebida para {} bytes",cm.getCommand().getTamanhoArquivo());
            ReservaEspaco reservaEspaco = reservaEspacoService.efetuarReservaDeEspaco(cm.getCommand().getTamanhoArquivo());
            extraHeaders.put("idReserva",reservaEspaco.getCodigoReserva());
            builder.withHeader(ReplyMessageHeaders.REPLY_OUTCOME,
                                CommandReplyOutcome.SUCCESS.toString());
        } catch (NenhumaUnidadeComEspacoDisponivelException e) {
            builder.withHeader(ReplyMessageHeaders.REPLY_OUTCOME,
                    CommandReplyOutcome.FAILURE.toString());
        }
        return builder.withExtraHeaders("reply-",extraHeaders).build();

    }



}


