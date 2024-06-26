package com.example.mediamarkt.controller;

import com.example.mediamarkt.model.Subscription;
import com.example.mediamarkt.service.EmailService;
import com.example.mediamarkt.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class SubscriptionController {

    private  final EmailService emailService;
    private final SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam("email") String email) {
        // Создать объект подписки
        Subscription subscription = new Subscription();
        subscription.setEmail(email);


        // Сохранить в базу данных
        subscriptionService.save(subscription);

        // Отправить подтверждение подписки
        emailService.sendEmail(email);


        return "redirect:/";
    }

    @PostMapping("/sendMessages")
    public String sendMessages(@RequestParam("message") String message,
                               RedirectAttributes redirectAttributes) {
        // Получить все подписки
        List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();

        // Отправить сообщение на все email в базе данных
        emailService.sendMassEmail("Важное сообщение", message, subscriptions);

        // Добавить атрибут для отображения на странице "Сообщения отправлены"
        redirectAttributes.addFlashAttribute("messageSent", true);

        // Перенаправить на страницу "Сообщения отправлены" или другую страницу
        return "redirect:/messagesSent";
    }

    // Этот метод будет обрабатывать перенаправление на страницу "Сообщения отправлены"
    @GetMapping("/messagesSent")
    public String messagesSentPage() {
        return "messages_sent";
    }
}
