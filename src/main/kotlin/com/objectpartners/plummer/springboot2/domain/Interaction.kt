package com.objectpartners.plummer.springboot2.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.annotation.Generated

@Document
data class Interaction(val type: InteractionType) {
    @Id @Generated lateinit var id: String
}
