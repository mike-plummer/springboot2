package com.objectpartners.plummer.springboot2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.WebSocketMessage
import org.springframework.web.reactive.socket.WebSocketSession
import reactor.core.publisher.Mono

@Component
class EventSocketHandler: WebSocketHandler {

    private companion object {
        val DATA_BUFFER_FACTORY = DefaultDataBufferFactory()
    }

    @Autowired lateinit var repository: InteractionRepository

    override fun handle(session: WebSocketSession): Mono<Void> {
        val flux = repository.findAll()
                .map({ event -> event.toString() })
                .map({ event -> WebSocketMessage(WebSocketMessage.Type.TEXT, DATA_BUFFER_FACTORY.wrap(event.toByteArray())) })
        return session.send(flux)
    }
}
