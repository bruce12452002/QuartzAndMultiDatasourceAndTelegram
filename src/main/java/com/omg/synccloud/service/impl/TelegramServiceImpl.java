package com.omg.synccloud.service.impl;

import com.omg.synccloud.service.TelegramService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Log4j2(topic = "fileLogger")
@Transactional(rollbackFor = {Exception.class}, transactionManager = "telegramTransactionManager")
public class TelegramServiceImpl implements TelegramService {
    @Value("${telegram-bot.token}")
    private String token;

    @Value("${telegram-bot.chat-id}")
    private Long chatId;

    @Override
    public void send(String header, String text) {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://api.telegram.org/bot" + token + "/sendMessage")
                    )
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString("chat_id=" + chatId +
                            "&text=" + this.getParameter(header, text) +
                            "&parse_mode=HTML"))
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error("TelegramServiceImpl => {}", e.getMessage());
        }
    }

    private String getParameter(String subject, String text) {
        return "<b>" + subject + "</b>" +
                "<code>" + text + "</code>";
    }
}