package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Language;
import com.elitefolk.filmrentalservice.repositories.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language getLanguageById(Byte id) {
        return languageRepository.findById(id).orElse(null);
    }

    @Override
    public Language saveLanguage(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void deleteLanguage(Byte id) {
        languageRepository.deleteById(id);
    }

    @Override
    public List<Language> fetchLanguageByNameOrId(List<Language> languages){
        List<Language> persistedLanguages = new ArrayList<>();
        for (Language language : languages) {
            Language persistedLanguage;
            if(language.getId() == null) {
                persistedLanguage = languageRepository.findByNameIgnoreCase(language.getName())
                        .orElseGet(() -> languageRepository.save(language) );
            } else {
                persistedLanguage = languageRepository.findById(language.getId())
                        .orElseGet(() -> {
                            language.setId(null);
                            return languageRepository.save(language);
                        }); // Save new language if not present
            }
            persistedLanguages.add(persistedLanguage);
        }
        return persistedLanguages;
    }
}
