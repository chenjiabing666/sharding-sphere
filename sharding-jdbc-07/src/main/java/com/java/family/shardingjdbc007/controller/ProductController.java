package com.java.family.shardingjdbc007.controller;

import com.java.family.shardingjdbc007.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/insert")
    public String insertOrder(){
        productService.insertProduct();
        return "success";
    }
}
