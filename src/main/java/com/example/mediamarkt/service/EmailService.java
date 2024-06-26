package com.example.mediamarkt.service;
import com.example.mediamarkt.model.Subscription;
import com.example.mediamarkt.pojo.EmailMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmailService {


    private final JavaMailSender mailSender;
    private final JavaMailSender emailSender;
    private final RabbitTemplate rabbitTemplate;

    public void sendEmail(String recipientEmail) {
        String confirmationMessage = "Уважаемый клиент,\n\n" +
                "Благодарим вас за подписку в нашем интернет-магазине техники! \n"+
                "Если у вас возникнут вопросы или потребуется помощь, пожалуйста, свяжитесь с нашей службой поддержки.\n\n" +
                "С уважением,\n" +
                "Команда интернет-магазина техники";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Подтверждение подписки");
        message.setText(confirmationMessage);

        emailSender.send(message);
    }


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
            rabbitTemplate.convertAndSend("MediaMarcket-exchange", "", new EmailMessage(subscription.getEmail(), subject, text));

        }

    }



}
