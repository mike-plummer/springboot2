package com.objectpartners.plummer.springboot2

import org.springframework.data.repository.reactive.ReactiveSortingRepository
import reactor.core.publisher.Flux

interface InteractionRepository: ReactiveSortingRepository<Interaction, Int> {
    fun findAllByType(type: InteractionType): Flux<Interaction>
}