package com.weba.config;

import com.weba.consumer.CommandReplyHandler;
import io.eventuate.jdbckafka.TramJdbcKafkaConfiguration;
import io.eventuate.tram.commands.common.ChannelMapping;
import io.eventuate.tram.commands.common.DefaultChannelMapping;
import io.eventuate.tram.commands.producer.TramCommandProducerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Collections;

@Configuration
@Import({TramCommandProducerConfiguration.class,TramJdbcKafkaConfiguration.class})
public class EventuateConfig {

    @Bean
    public CommandProducerParameters commandProducerParameters(){
        return new CommandProducerParameters();
    }

    @Bean
    public CommandReplyHandler commandConsumerHandler(CommandProducerParameters parameters){
        return new CommandReplyHandler(parameters.getReplyChannel());
    }

    @Bean
    public ChannelMapping channelMapping(CommandProducerParameters config) {
        return new DefaultChannelMapping(Collections.singletonMap("WebaAggregate", config.getCommandChannel()));
    }

}
