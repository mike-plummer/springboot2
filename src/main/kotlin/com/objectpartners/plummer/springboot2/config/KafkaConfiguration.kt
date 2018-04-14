package com.objectpartners.plummer.springboot2.config

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean


@Configuration
@EnableKafka
class KafkaConfiguration {

    @KafkaListener(topics = ["events"])
    fun listen(cr: ConsumerRecord<String, String>) {
        println("Record with ID ${cr.value()} created")
    }

    @Bean
    fun sbv2(): NewTopic {
        return NewTopic("sbv2", 10, 2.toShort())
    }
}
