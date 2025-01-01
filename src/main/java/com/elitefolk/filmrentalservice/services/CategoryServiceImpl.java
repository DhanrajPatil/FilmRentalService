package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Category;
import com.elitefolk.filmrentalservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Byte id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Byte id) {

    }

    @Override
    public List<Category> fetchOrCreateCategories(List<Category> categories) {
        List<Category> persistedCategories = new ArrayList<>();
        for (Category category : categories) {
            Category persistedCategory;
            if(category.getId() == null) {
                persistedCategory = categoryRepository.findByName(category.getName())
                        .orElseGet(() -> categoryRepository.save(category) );
            } else {
                persistedCategory = categoryRepository.findById(category.getId())
                        .orElseGet(() -> {
                            category.setId(null);
                            return categoryRepository.save(category);
                        }); // Save new category if not present
            }
            persistedCategories.add(persistedCategory);
        }
        return persistedCategories;
    }
}
