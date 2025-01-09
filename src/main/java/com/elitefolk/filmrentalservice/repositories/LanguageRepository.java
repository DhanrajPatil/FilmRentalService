package com.elitefolk.filmrentalservice.repositories;

import com.elitefolk.filmrentalservice.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Byte> {
    Optional<Language> findByNameIgnoreCase(String name);
    List<Language> findByNameIgnoreCaseIn(List<String> names);
    List<Language> findByIdIn(List<Byte> ids);
}
