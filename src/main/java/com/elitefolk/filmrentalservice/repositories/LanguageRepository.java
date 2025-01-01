package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Byte> {
    Optional<Language> findByNameIgnoreCase(String name);
    Optional<Language> findById(Byte id);
    List<Language> findByNameIgnoreCaseIn(List<String> names);
    List<Language> findByIdIn(List<Byte> ids);
}
