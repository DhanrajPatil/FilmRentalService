package com.elitefolk.filmrentalservice.services;

import com.elitefolk.filmrentalservice.models.Language;

import java.util.List;

public interface LanguageService {

    List<Language> getAllLanguages();
    Language getLanguageById(Byte id);
    Language saveLanguage(Language language);
    void deleteLanguage(Byte id);
    List<Language> fetchLanguageByNameOrId(List<Language> languages);
}
