package com.crni99.bookstore.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.crni99.bookstore.repository.CategoryRepository;

import org.springframework.ui.Model;
import java.util.List;

@ControllerAdvice
public class GlobalModelAttributes {

    @Autowired
    private CategoryRepository categoryRepository;

    @ModelAttribute
    public void addCategoriesToModel(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
    }
}
