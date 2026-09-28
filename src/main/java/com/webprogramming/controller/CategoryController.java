package com.webprogramming.controller;

import com.webprogramming.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categories/list";
    }
}
