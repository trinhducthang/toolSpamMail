package com.ducthang.spamEmail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {
    @GetMapping("/email")
    public String email() {
        return "email";
    }
}
