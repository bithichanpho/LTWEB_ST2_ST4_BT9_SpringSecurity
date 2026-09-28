package com.webprogramming.controller;

import com.webprogramming.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products/list";
    }

    @GetMapping("/products/{id}")
    public String productDetail(@PathVariable String id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm: " + id)));
        return "products/detail";
    }
}
