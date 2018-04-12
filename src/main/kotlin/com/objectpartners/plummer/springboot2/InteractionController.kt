package com.objectpartners.plummer.springboot2

import com.objectpartners.plummer.springboot2.domain.Interaction
import com.objectpartners.plummer.springboot2.domain.InteractionType
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/interactions")
class InteractionController {

    @Autowired lateinit var repository: InteractionRepository

    @PostMapping
    fun createAsync(@RequestBody stream: Publisher<Interaction>): Mono<Void> {
        return repository.saveAll(stream).then()
    }

    @GetMapping
    fun getAll(): Flux<Interaction> {
        return repository.findAll()
    }

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
        return Mono.from(repository.findAllByType(type))
    }
}
