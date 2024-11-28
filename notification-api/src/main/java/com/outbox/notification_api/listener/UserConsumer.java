package com.outbox.notification_api.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outbox.notification_api.dto.UserDto;
import com.outbox.notification_api.service.SmsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {

    private final ObjectMapper objectMapper;
    private final SmsService smsService;

    public UserConsumer(ObjectMapper objectMapper,SmsService smsService) {
        this.objectMapper = objectMapper;
        this.smsService = smsService;
    }


    @KafkaListener(topics = "${spring.kafka.topic.user-created}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload String message) {
        try {
            String object = objectMapper.readTree(message).get("payload").asText();
            UserDto userDto = objectMapper.readValue(object, UserDto.class);
            smsService.send(userDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
