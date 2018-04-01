package com.objectpartners.plummer.springboot2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

@RestController("/api/interactions")
class InteractionController {

    @Autowired lateinit var repository: InteractionRepository

    @PostMapping("/type/{type}")
    fun create(@PathVariable("type") type: InteractionType): Mono<Interaction> {
        return repository.save(Interaction(type))
    }

    @GetMapping("/type/{type}")
    fun getByType(@PathVariable("type") type: InteractionType,
                  @RequestParam("limit", defaultValue = "10") limit: Long): Flux<Interaction> {
        return repository.findAllByType(type).take(limit)
    }

    @GetMapping("/type/{type}/next")
    fun watchNextByType(@PathVariable("type") type: InteractionType): Mono<Interaction> {
        return repository.findAllByType(type).take(1).toMono()
    }
}
