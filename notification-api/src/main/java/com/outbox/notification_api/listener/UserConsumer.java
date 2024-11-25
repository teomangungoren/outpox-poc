package com.outbox.notification_api.listener;

import com.outbox.notification_api.dto.UserDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserConsumer {


    @KafkaListener(topics = "${kafka.topic.user-created}",groupId = "${kafka.group-id}")
    public void consume(@Payload UserDto userDto) {
        System.out.println(userDto.toString());
    }
}
