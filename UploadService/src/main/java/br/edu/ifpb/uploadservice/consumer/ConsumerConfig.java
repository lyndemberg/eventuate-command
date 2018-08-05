package br.edu.ifpb.uploadservice.consumer;

import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class ConsumerConfig {

    @Bean
    public CommandConsumerHandler commandConsumerHandler(ConsumerParameters parameters){
        return new CommandConsumerHandler(parameters.getCommandChannel());
    }

    @Bean
    public CommandDispatcher commandDispatcher(ConsumerParameters parameters, CommandConsumerHandler handler) {
        return new CommandDispatcher(parameters.getDispatcherId(), handler.getCommandHandlers());
    }

    @Bean
    public ConsumerParameters commandConsumerParameters() {
        return new ConsumerParameters();
    }

    @Bean
    public ChannelMapping channelMapping(ConsumerParameters config) {
        return new DefaultChannelMapping(Collections.singletonMap("WebaAggregate", config.getCommandChannel()));
    }

}
