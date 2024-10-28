package com.ducthang.spamEmail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "email")
public class EmailAccountsConfig {

    private List<EmailAccount> accounts;

    public List<EmailAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<EmailAccount> accounts) {
        this.accounts = accounts;
    }

    public static class EmailAccount {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
