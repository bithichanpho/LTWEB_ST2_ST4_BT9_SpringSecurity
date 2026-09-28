package com.webprogramming.controller;

import com.webprogramming.dto.UserDTO;
import com.webprogramming.security.CustomUserDetails;
import com.webprogramming.service.UserService;
import com.webprogramming.repository.CategoryRepository;
import com.webprogramming.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @GetMapping("/")
    public String home(Authentication authentication, Model model) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        UserDTO user = userService.findById(principal.getId());

        model.addAttribute("user", user);
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "home";
    }
}
