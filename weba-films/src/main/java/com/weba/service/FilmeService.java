package com.weba.service;

import com.weba.commands.EfetuarReservaCommand;
import com.weba.config.CommandProducerParameters;
import com.weba.domain.Filme;
import com.weba.domain.Status;
import com.weba.repository.FilmeRepository;
import io.eventuate.tram.commands.producer.CommandProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class FilmeService {
    @Autowired
    private FilmeRepository filmeRepository;
    @Autowired
    private CommandProducer commandProducer;
    @Autowired
    private CommandProducerParameters producerParameters;


    public Filme salvarFilme(Filme novo){
        novo.setStatus(Status.RESERVA_PENDENTE);
        Filme filme = filmeRepository.save(novo);

        EfetuarReservaCommand comando = new EfetuarReservaCommand(filme.getId(),
                                        filme.getArquivoDisco().getTamanho(),
                                        filme.getStatus().toString());

        String commandId = commandProducer.send(producerParameters.getCommandChannel(),
               comando , producerParameters.getReplyChannel(), Collections.emptyMap());
        return filme;
    }

    public Filme buscarPorId(Long id){
        return filmeRepository.findById(id).get();
    }

    public void atualizarFilme(Filme atualizado){
        filmeRepository.save(atualizado);
    }


}
