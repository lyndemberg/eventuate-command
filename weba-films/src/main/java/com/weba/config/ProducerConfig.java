package com.weba.config;

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
public class ProducerConfig {

    @Bean
    public ProducerParameters commandProducerParameters(){
        return new ProducerParameters();
    }

    @Bean
    public ChannelMapping channelMapping(ProducerParameters config) {
        return new DefaultChannelMapping(Collections.singletonMap("WebaAggregate", config.getCommandChannel()));
    }

}
