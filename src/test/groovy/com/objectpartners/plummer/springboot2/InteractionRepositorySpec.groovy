package com.objectpartners.plummer.springboot2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import spock.lang.Specification

@SpringBootTest
class InteractionRepositorySpec extends Specification {

    @Autowired InteractionRepository repository

    def setup() {
        Flux.concat(
                repository.save(new Interaction(InteractionType.CLICK)),
                repository.save(new Interaction(InteractionType.CLICK))
        ).blockLast()
    }

    def 'testFindByType'() {
        when:
        Flux<Interaction> result = repository.findAllByType(InteractionType.CLICK)

        then:
        result.count().block() == 2

        when:
        result = repository.findAllByType(InteractionType.ACCESS)

        then:
        result.count().block() == 0

        when:
        repository.save(new Interaction(InteractionType.ACCESS)).block()

        then:
        result.count().block() == 1
    }
}
