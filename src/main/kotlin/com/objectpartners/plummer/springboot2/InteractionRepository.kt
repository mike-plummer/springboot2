package com.objectpartners.plummer.springboot2

import com.objectpartners.plummer.springboot2.domain.Interaction
import com.objectpartners.plummer.springboot2.domain.InteractionType
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import reactor.core.publisher.Flux

interface InteractionRepository: ReactiveSortingRepository<Interaction, Int> {
    fun findAllByType(type: InteractionType): Flux<Interaction>
}