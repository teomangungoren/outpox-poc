package com.outbox.notification_api.service;

import com.outbox.notification_api.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final NotifyService notifyService;

    public SmsService(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    public void send(UserDto userDto) {
        try {
            System.out.println(userDto.getEmail() + " sent sms");
            notifyService.send(createNotificationMessage(userDto,"SUCCESS"));
        }catch (Exception ex){
          notifyService.send(createNotificationMessage(userDto,"FAILED"));
         }
    }

    private String createNotificationMessage(UserDto userDto, String status) {
        return String.format("{\"userId\": %d, \"status\": \"%s\"}", userDto.getId(), status);
    }

}
