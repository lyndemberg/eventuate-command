package com.webafilmes;

import com.webafilmes.config.CommandProducerParameters;
import io.eventuate.tram.commands.producer.CommandProducer;
import io.eventuate.tram.commands.producer.CommandProducerImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebaFilmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebaFilmsApplication.class, args);
	}


}
