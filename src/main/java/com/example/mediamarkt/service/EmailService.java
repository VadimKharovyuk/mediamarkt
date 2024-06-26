package com.example.mediamarkt.service;
import com.example.mediamarkt.model.Subscription;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendEmail(String recipientEmail) {
        String confirmationMessage = "Уважаемый,\n\n" +
                "Ожидаем вас и желаем приятного времяпровождения в нашем салоне!\n\n" +
                "С уважением,\n" +
                "Команда салона красоты";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Подтверждение записи");
        message.setText(confirmationMessage);

        emailSender.send(message);
    }
    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendMassEmail(String subject, String text, List<Subscription> subscriptions) {
        for (Subscription subscription : subscriptions) {
            sendEmail(subscription.getEmail(), subject, text);
        }
    }



}
