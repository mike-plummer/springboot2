package com.objectpartners.plummer.springboot2.beans

import com.objectpartners.plummer.springboot2.InteractionRepository
import com.objectpartners.plummer.springboot2.domain.Interaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component


@Component
class DatabaseListener: AbstractMongoEventListener<Interaction>() {
    @Autowired lateinit var kafkaTemplate: KafkaTemplate<String, String>

    override fun onAfterSave(event: AfterSaveEvent<Interaction>) {
        kafkaTemplate.send("events", event.source.id)
    }
}
