package com.example.mediamarkt.service;//package com.example.mediamarkt.service;
//
//import com.example.mediamarkt.model.Subscription;
//import com.example.mediamarkt.repository.SubscriptionRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class SubscriptionService {
//
//    private final SubscriptionRepository subscriptionRepository;
//
//
//
//    public Subscription save(Subscription email){
//        return subscriptionRepository.save(email);
//    }
//
//
//
//    public List<Subscription> getAllSubscriptions() {
//        return subscriptionRepository.findAll().stream().
//                filter(subscription -> subscription.getEmail()!=null&&subscription.getEmail().isEmpty()).collect(Collectors.toList());
//    }
//
//    // Метод отправки сообщения на все подписки
//    public void sendEmailToAllSubscriptions(String message) {
//        List<Subscription> subscriptions = getAllSubscriptions();
//        for (Subscription subscription : subscriptions) {
//            sendEmail(subscription.getEmail(), message);
//        }
//    }
//
//    // Пример метода для отправки email (здесь может потребоваться использование сторонней библиотеки или API)
//    private void sendEmail(String email, String message) {
//        // Ваш код для отправки сообщения на указанный email
//        System.out.println("Отправка сообщения на " + email + ": " + message);
//        // Пример: использование сторонней библиотеки или API для отправки сообщения
//    }
//


import com.example.mediamarkt.model.Subscription;
import com.example.mediamarkt.repository.SubscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public Subscription save(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll()
                .stream()
                .filter(subscription -> subscription.getEmail() != null && !subscription.getEmail().isEmpty())
                .collect(Collectors.toList());
    }



}
