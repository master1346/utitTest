package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    public void identifyLocationByIdRussia() {
        Country expected = Country.RUSSIA;
        GeoService geoService = new GeoServiceImpl();

        Country actual = geoService.byIp(GeoServiceImpl.MOSCOW_IP).getCountry();
        assertEquals(expected, actual);
    }
}