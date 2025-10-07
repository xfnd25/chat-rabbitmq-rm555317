package br.com.fernando.chat_rabbitmq.consumer;

import br.com.fernando.chat_rabbitmq.config.RabbitMQConfig;
import br.com.fernando.chat_rabbitmq.model.ChatMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component // Anotação que torna esta classe um bean gerenciado pelo Spring
public class MessageConsumer {

    // A anotação @RabbitListener configura este método para ouvir uma fila específica
    @RabbitListener(queues = RabbitMQConfig.BROADCAST_QUEUE)
    public void receiveBroadcastMessage(ChatMessage message) {
        // Esta é a lógica que executa quando uma mensagem é recebida.
        // Por enquanto, vamos apenas imprimir no console para ver a mágica acontecer.
        System.out.println("BROADCAST RECEIVED: From '" + message.sender() + "' - '" + message.content() + "'");
    }
}