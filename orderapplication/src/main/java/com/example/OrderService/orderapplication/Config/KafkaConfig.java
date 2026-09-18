package com.example.OrderService.orderapplication.Config;

//#kafka-configuration
//spring.kafka.bootstrap-servers=localhost:9091,localhost:9092,localhost:9093
//spring.kafka.producer.key-serializer = org.apache.kafka.common.serialization.StringSerializer
//spring.kafka.producer.value-serializer = org.springframework.kafka.support.serializer.JacksonJsonDeserializer
//spring.kafka.producer.acks=all
//spring.kafka.producer.properties.delivery.timeout.ms=120000
//spring.kafka.producer.properties.linger.ms=0
//spring.kafka.producer.properties.request.timeout.ms=30000
//spring.kafka.producer.properties.enable.idempotence=true
//spring.kafka.producer.properties.max.in.flight.requests.per.connection=5
//spring.kafka.consumer.group-id=order-ms
//spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
//spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JacksonJsonDeserializer
//orders.events.topic.name=orders-events

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaConfig {
    @Autowired
    Environment environment;

    Map<String,Object> Configuration(){
        Map<String,Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,environment.getProperty("spring.kafka.bootstrap-servers"));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,environment.getProperty("spring.kafka.producer.key-serializer"));
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,environment.getProperty("spring.kafka.producer.value-serializer"));
                config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,environment.getProperty("spring.kafka.producer.properties.max.in.flight.requests.per.connection"));
        config.put(ProducerConfig.RETRIES_CONFIG,environment.getProperty("spring.kafka.producer.retries"));
        config.put(ProducerConfig.ACKS_CONFIG,environment.getProperty("spring.kafka.producer.acks"));
        config.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG,environment.getProperty("spring.kafka.producer.properties.delivery.timeout.ms"));
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,environment.getProperty("spring.kafka.producer.properties.enable.idempotence"));
                        return config;
    }
//    orders.events.topic.name=orders-events
    @Bean
    ProducerFactory producerFactory(){
        return new DefaultKafkaProducerFactory(Configuration());
    }
    @Bean
    public KafkaTemplate<String,Object>kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
    @Bean
    NewTopic createNewTopic(){
        return TopicBuilder.name(environment.getProperty("orders.events.topic.name"))
                .replicas(3).partitions(3).configs(Map.of("min-insync.replicas","2")).build();
    }
}
