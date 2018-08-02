package com.webafilmes.config;

import com.webafilmes.consumer.CommandReplyHandler;
import com.webafilmes.service.FilmeService;
import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.commands.producer.CommandProducerImpl;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.Map;

@Configuration
@Import({TramCommandProducerConfiguration.class,TramJdbcKafkaConfiguration.class})
public class EventuateConfig {

    @Bean
    public CommandProducerParameters commandProducerParameters(){
        return new CommandProducerParameters();
    }

    @Bean
    public CommandReplyHandler commandConsumerHandler(CommandProducerParameters parameters,FilmeService service){
        return new CommandReplyHandler(parameters.getReplyChannel(), service);
    }

    @Bean
    public ChannelMapping channelMapping(CommandProducerParameters config) {
        return new DefaultChannelMapping(Collections.singletonMap("WebaAggregate", config.getCommandChannel()));
    }

}
