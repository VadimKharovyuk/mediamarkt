package com.example.mediamarkt.listener;
import com.example.mediamarkt.pojo.EmailMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailListener {

    private final JavaMailSender mailSender;

    @RabbitListener(queues = "emailSendMediaMarcket-queue")
    public void receiveMessage(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailMessage.getTo());
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getText());
        mailSender.send(message);
        System.out.println("Отработала emailSendMediaMarcket-queue "+emailMessage.getTo() );
    }
}
