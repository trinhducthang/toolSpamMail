package com.ducthang.spamEmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;

@Service
public class EmailService {

    private final EmailAccountsConfig emailAccountsConfig;
    private static int totalSentEmails = 0; // Biến tĩnh để lưu số email đã gửi
    private int currentAccountIndex = 0; // Biến để theo dõi tài khoản hiện tại

    @Autowired
    public EmailService(EmailAccountsConfig emailAccountsConfig) {
        this.emailAccountsConfig = emailAccountsConfig;
    }

    public void sendEmails(String to, String subject, String body, int count) {
        List<EmailAccountsConfig.EmailAccount> accounts = emailAccountsConfig.getAccounts();
        int accountCount = accounts.size();

        for (int i = 0; i < count; i++) {
            EmailAccountsConfig.EmailAccount account = accounts.get(currentAccountIndex);
            sendEmail(to, subject + " " + (i + 1), body, account);
            totalSentEmails++; // Tăng biến đếm
            currentAccountIndex = (currentAccountIndex + 1) % accountCount; // Luân phiên qua các tài khoản
        }
    }

    public static int getTotalSentEmails() {
        return totalSentEmails; // Phương thức để lấy số email đã gửi
    }

    private void sendEmail(String to, String subject, String body, EmailAccountsConfig.EmailAccount account) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(account.getUsername());
        mailSender.setPassword(account.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(account.getUsername());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
