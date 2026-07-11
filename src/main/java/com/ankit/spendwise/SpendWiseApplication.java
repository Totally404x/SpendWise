package com.ankit.spendwise;
import com.ankit.spendwise.service.CategoryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpendWiseApplication implements CommandLineRunner {

    @Autowired
    private CategoryService categoryService;

    public static void main(String[] args) {
        SpringApplication.run(SpendWiseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(categoryService.getCategory("Digitek Light for shooting"));
    }
}
