package com.ducthang.spamEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String body,
                            @RequestParam int count) {
        new Thread(() -> {
            emailService.sendEmails(to, subject, body, count);
        }).start();

        return "Emails are being sent to " + to + "!";
    }

    @GetMapping("/sentCount")
    public int getSentCount() {
        return EmailService.getTotalSentEmails(); // Trả về số lượng email đã gửi
    }
}
