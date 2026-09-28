package com.webprogramming.controller;

import com.webprogramming.dto.UserDTO;
import com.webprogramming.security.CustomUserDetails;
import com.webprogramming.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        UserDTO user = userService.findById(principal.getId());
        model.addAttribute("user", user);
        return "home";
    }
}
