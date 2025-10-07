package br.com.fernando.chat_rabbitmq.producer;

import br.com.fernando.chat_rabbitmq.config.RabbitMQConfig;
import br.com.fernando.chat_rabbitmq.model.ChatMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    // O RabbitTemplate é injetado automaticamente pelo Spring [cite: 206]
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendBroadcastMessage(ChatMessage message) {
        // Usa o método convertAndSend para publicar a mensagem [cite: 210]
        // 1º param: O nome da exchange. Usamos a constante da nossa classe de config.
        // 2º param: A routing key. Para Fanout, ela é ignorada, então enviamos uma string vazia[cite: 69].
        // 3º param: A mensagem em si.
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message);
    }
}