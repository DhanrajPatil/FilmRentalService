package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
    @Override
    Optional<Category> findById(Byte aByte);
    Optional<Category> findByName(String name);
}
