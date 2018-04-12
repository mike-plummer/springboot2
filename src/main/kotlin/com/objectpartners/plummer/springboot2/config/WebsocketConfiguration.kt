package com.objectpartners.plummer.springboot2.config

import com.objectpartners.plummer.springboot2.EventSocketHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.HandlerMapping
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping
import org.springframework.web.reactive.socket.WebSocketHandler
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter
import java.util.*

@Configuration
class WebsocketConfiguration {
    @Autowired lateinit var eventSocketHandler: EventSocketHandler

    @Bean
    fun webSocketMapping(): HandlerMapping {
        val map = HashMap<String, WebSocketHandler>()
        map["/events"] = eventSocketHandler

        val mapping = SimpleUrlHandlerMapping()
        mapping.urlMap = map
        return mapping
    }

    @Bean
    fun handlerAdapter(): WebSocketHandlerAdapter {
        return WebSocketHandlerAdapter()
    }
}