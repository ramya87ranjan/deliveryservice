package com.eatza.delivery.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.eatza.delivery.dto.DeliveryRequestDto;
@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	@Value("${kafka.bootstrap.servers}")
	private String kafkaBootstrapServers;


	@Bean
	public ConsumerFactory<String, DeliveryRequestDto> consumerFactory(){
		
		
	    JsonDeserializer<DeliveryRequestDto> deserializer = new JsonDeserializer<>(DeliveryRequestDto.class,false);
		
		  deserializer.setRemoveTypeHeaders(false);
		  deserializer.addTrustedPackages("*");
		  deserializer.setUseTypeMapperForKey(true);
		 

	    Map<String, Object> config = new HashMap<>();

	    config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
	    config.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-test");
	    //config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	    config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
	    config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	    config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

	    return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, DeliveryRequestDto>> kafkaListenerContainerFactory(){
	    ConcurrentKafkaListenerContainerFactory<String, DeliveryRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	    
	   
	}

	
	
	




}
