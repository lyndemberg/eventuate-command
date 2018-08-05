package com.weba.service;

import com.weba.commands.EfetuarReservaCommand;
import com.weba.config.ProducerParameters;
import com.weba.domain.Filme;
import com.weba.domain.Status;
import com.weba.repository.FilmeRepository;
import io.eventuate.tram.commands.common.CommandReplyOutcome;
import io.eventuate.tram.commands.common.ReplyMessageHeaders;
import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.messaging.consumer.MessageConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;


@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private CommandProducer commandProducer;
    @Autowired
    private MessageConsumer messageConsumer;
    @Autowired
    private ProducerParameters producerParameters;


    @Transactional
    public Filme salvarFilme(Filme novo){
        novo.setStatus(Status.RESERVA_PENDENTE);
        Filme filme = filmeRepository.save(novo);

        EfetuarReservaCommand comando = new EfetuarReservaCommand(filme.getId(),
                filme.getArquivoFilme().getTamanho());

        messageConsumer.subscribe(producerParameters.getSubscriberId(),
                Collections.singleton(producerParameters.getReplyChannel()),
                this::finalizarReservaFilme);

        String commandId = commandProducer.send(producerParameters.getCommandChannel(),
               comando , producerParameters.getReplyChannel(), Collections.emptyMap());


        return filme;
    }

    @Transactional
    public void finalizarReservaFilme(Message reply){
        Long idFilme = Long.valueOf(reply.getHeader("reply-idFilme").get());
        Optional<String> optional = reply.getHeader("reply-idReserva");
        Filme filme = filmeRepository.findById(idFilme).get();
        if(optional.isPresent()){
            filme.setIdReserva(optional.get());
            filme.setStatus(Status.RESERVA_REALIZADA);
        }else{
            filme.setStatus(Status.RESERVA_REJEITADA);
        }
        atualizarFilme(filme);
    }


    public Filme buscarPorId(Long id){
        return filmeRepository.findById(id).get();
    }

    public void atualizarFilme(Filme atualizado){
        filmeRepository.save(atualizado);
    }


}
