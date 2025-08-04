package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.repositories.CategoryRepository;
import model.Category;

@Controller
@RequestMapping("/category/")
public class CategoryController {

    @Autowired
    CategoryRepository cr;

    @GetMapping("getCategoriesOverview")
    public String getCategoriesOverview(Model model) {
        List<Category> categories = cr.findAll();
        model.addAttribute("categories", categories);
        return "admin/categoriesOverview";
    }

}
