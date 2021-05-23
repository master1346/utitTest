package ru.netology.i18n;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.entity.Country.USA;

class LocalizationServiceImplTest {

    @Test
    public void localizationTextByCountry() {
        String expected = "Welcome";

        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(USA);
        assertEquals(expected, actual);
    }

}