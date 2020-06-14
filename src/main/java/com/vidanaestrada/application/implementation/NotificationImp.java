package com.vidanaestrada.application.implementation;


import com.google.firebase.messaging.*;
import com.vidanaestrada.application.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import com.google.firebase.messaging.Notification;

@Service
public class NotificationImp implements NotificationService {

    private final Set<String> tokenRegistry = new CopyOnWriteArraySet<>();

    @Autowired
    private FirebaseMessaging firebaseMessaging;


    public void addToken(String token) {
        this.tokenRegistry.add(token);
    }

    public void removeToken(String token) {
        this.tokenRegistry.remove(token);
    }

    @Scheduled(fixedDelay = 120_000)
    private void sendPushMessages() {
        for (String token : this.tokenRegistry) {
            System.out.println("Sending personal message to: " + token);
            Map<String, String> data = new HashMap<>();
            data.put("text", String.valueOf(Math.random() * 1000));

            try {
                sendPersonalMessage(token, data);
            }
            catch (InterruptedException | ExecutionException ignored) {

            }
        }
    }


    private void sendPersonalMessage(String clientToken, Map<String, String> data)
            throws InterruptedException, ExecutionException {
        AndroidConfig androidConfig = AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey("personal")
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag("personal").build())
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(Aps.builder().setCategory("personal").setThreadId("personal").build())
                .build();

        Message message = Message.builder().putAllData(data).setToken(clientToken)
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(new Notification("Personal Message", "A Personal Messag"))
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        System.out.println("Sent message: " + response);
    }

}
