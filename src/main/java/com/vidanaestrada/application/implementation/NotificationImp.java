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
    private Map<String, String> notificationMessageContent =  new HashMap<>();
    private int sequence = 1;

    @Autowired
    private FirebaseMessaging firebaseMessaging;


    public void addToken(String token) {
        this.tokenRegistry.add(token);
    }

    public void removeToken(String token) {
        this.tokenRegistry.remove(token);
    }

    @Scheduled(fixedDelay = 120_000)
    private void sendNotifications() {
        for (String token : this.tokenRegistry) {

            setMessage();
            try {
                sendPersonalMessage(token);
            }
            catch (InterruptedException | ExecutionException ignored) {
                        /// logar
            }
        }
    }

    private void setMessage() {
        if((this.sequence % 2) == 0 ) {
            notificationMessageContent.put("content", "Que tal uma parada para se alimentar ? Siga seu plano de cuidados e acumule pontos");
        } else {
            notificationMessageContent.put("content", "Muito tempo digirindo não é ? Um alongamento seria uma boa ideia para evitar dores, no Plano de Cuidados reservamos algumas dicas para você");
        }

    }


    private void sendPersonalMessage(String clientToken)
            throws InterruptedException, ExecutionException {
        AndroidConfig androidConfig = AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey("personal")
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setTag("personal").build())
                .build();

        ApnsConfig apnsConfig = ApnsConfig.builder()
                .setAps(Aps.builder().setCategory("personal").setThreadId("personal").build())
                .build();

        Message message = Message.builder().putAllData(this.notificationMessageContent).setToken(clientToken)
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig)
                .setNotification(new Notification("Personal Message", "A Personal Messag"))
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();
        System.out.println("Sent message: " + response);
        this.sequence++;
    }

}
