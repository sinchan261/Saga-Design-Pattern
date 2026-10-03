package com.example.productsservice.products.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {
    @Value("${product.events.topic.name}")
    private String productEventsTopicName;
    @Bean
    KafkaTemplate<String,Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory){
        return new KafkaTemplate<>(producerFactory);
    }
    NewTopic createProductEventsTopic(){
        return TopicBuilder.name(productEventsTopicName)
                .partitions(3).replicas(3).build();

    }
}
