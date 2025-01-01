package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Byte id);
    Category saveCategory(Category category);
    void deleteCategory(Byte id);
    List<Category> fetchOrCreateCategories(List<Category> categories);
}
