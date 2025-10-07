package br.com.fernando.chat_rabbitmq.controller;

import br.com.fernando.chat_rabbitmq.model.ChatMessage;
import br.com.fernando.chat_rabbitmq.producer.MessageProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Anotação que indica que esta classe manipula requisições HTTP REST [cite: 240]
@RequestMapping("/messages") // Define o caminho base para os endpoints deste controller [cite: 243]
public class ChatController {

    private final MessageProducer messageProducer;

    public ChatController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/broadcast") // Mapeia este método para requisições POST em /messages/broadcast
    public ResponseEntity<String> sendBroadcast(@RequestBody ChatMessage message) {
        // Chama o nosso produtor para enviar a mensagem recebida no corpo da requisição [cite: 233]
        messageProducer.sendBroadcastMessage(message);
        // Retorna uma resposta simples de sucesso para o cliente [cite: 235]
        return ResponseEntity.ok("Message sent to broadcast!");
    }
}