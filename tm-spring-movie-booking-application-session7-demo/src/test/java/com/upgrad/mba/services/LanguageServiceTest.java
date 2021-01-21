package com.upgrad.mba.services;

import com.upgrad.mba.dao.LanguageDao;
import com.upgrad.mba.entities.Language;
import com.upgrad.mba.exceptions.LanguageDetailsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class LanguageServiceTest {

    @Autowired
    //private LanguageService languageService;
    @Mock
    private LanguageDao languageDao;
    @InjectMocks
    private LanguageServiceImpl languageService;
    @BeforeEach
    public void setupMockito()
    {
        Mockito.when(languageDao.save(new Language("save language"))).thenReturn(new Language(1,"save language"));
        Mockito.when(languageDao.findById(1)).thenReturn(Optional.of(new Language(1,"save language")));
        Mockito.when(languageDao.save(new Language(1, "update language"))).thenReturn(new Language(1, "update language"));

        Mockito.when(languageDao.save(new Language("get language"))).thenReturn(new Language(1, "get language"));
        Mockito.when(languageDao.findByLanguageName("get language")).thenReturn(Optional.of(new Language(1, "get language")));
    }
    @Test
    public void testAcceptLanguageDetails() {
        Language language = new Language();
        language.setLanguageName("Language 1");
        language.setLanguageName("save language");
        Language savedLanguage = languageService.acceptLanguageDetails(language);

        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("Language 1", savedLanguage.getLanguageName());
        Assertions.assertEquals("save language",savedLanguage.getLanguageName());
    }

    @Test
    public void testGetLanguageDetails() throws LanguageDetailsNotFoundException {
        Language language = new Language();
        language.setLanguageName("Language 2");
        language = languageService.acceptLanguageDetails(language);

        Language savedLanguage = languageService.getLanguageDetails(language.getLanguageId());
        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("Language 2", savedLanguage.getLanguageName());
    }

    @Test
    public void testGetLanguageDetailsByLanguageName() throws LanguageDetailsNotFoundException {
        Language language = new Language();
        language.setLanguageName("Language 3");
        languageService.acceptLanguageDetails(language);

        Language savedLanguage = languageService.getLanguageDetailsByLanguageName("Language 3");
        Assertions.assertNotNull(savedLanguage);
        Assertions.assertTrue(savedLanguage.getLanguageId() != 0);
        Assertions.assertEquals("Language 3", savedLanguage.getLanguageName());
    }
}
