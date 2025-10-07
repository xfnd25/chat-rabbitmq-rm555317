package br.com.fernando.chat_rabbitmq.model;

// Este record representa o conteúdo da mensagem que será enviada. [cite: 137]
public record ChatMessage(
        String sender,
        String content,
        long timestamp
) {}