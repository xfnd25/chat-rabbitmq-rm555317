package br.com.fernando.chat_rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    public static final String FANOUT_EXCHANGE = "chat.fanout";
    public static final String BROADCAST_QUEUE = "chat.broadcast.queue";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Queue broadcastQueue() {
        return new Queue(BROADCAST_QUEUE, false, false, true);
    }

    @Bean
    public Binding broadcastBinding(Queue broadcastQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(broadcastQueue).to(fanoutExchange);
    }

    // --- CÓDIGO MODIFICADO ---

    // 1. O bean do conversor JSON continua o mesmo.
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // 2. MODIFICADO: Agora customizamos o RabbitTemplate em vez de criá-lo.
    // O Spring Boot vai criar o RabbitTemplate padrão, e então aplicar esta customização.
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}