package com.omg.synccloud.service;

@FunctionalInterface
public interface SendService {
    void send(String subject, String content);
}
