package com.elitefolk.filmrentalservice.dtos;

import com.elitefolk.filmrentalservice.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicCategoryDto {
    private Byte id;
    private String name;

    public BasicCategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }

    public static BasicCategoryDto fromCategory(Category category) {
        return new BasicCategoryDto(category);
    }

    public static List<BasicCategoryDto> fromCategories(List<Category> categories) {
        return categories.stream().map(BasicCategoryDto::fromCategory).toList();
    }

    public Category toCategory() {
        return new Category(id, name, null, new ArrayList<>());
    }
}
