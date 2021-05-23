package ru.netology.sender;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static ru.netology.entity.Country.RUSSIA;
import static ru.netology.entity.Country.USA;
import static ru.netology.geo.GeoServiceImpl.MOSCOW_IP;
import static ru.netology.geo.GeoServiceImpl.NEW_YORK_IP;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTest extends TestCase {

    @Test
    public void MessageSenderImplRussianLanguage() {
       String expected = "Добро пожаловать";
       Location location = new Location("Moscow", RUSSIA, "Lenina", 15);

       GeoService geoService = Mockito.mock(GeoServiceImpl.class);
       Mockito.when(geoService.byIp(MOSCOW_IP)).thenReturn(location);

       LocalizationService localizationService =Mockito.mock(LocalizationServiceImpl.class);
       Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Добро пожаловать");

       MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

       Map<String, String> headers1 = new HashMap<String, String>();
       headers1.put(IP_ADDRESS_HEADER, MOSCOW_IP);

       String actual = messageSender.send(headers1);
       assertEquals(expected, actual);
    }

    @Test
    public void MessageSenderImplEnglandLanguage() {
        String expected = "WELCOME";
        Location location = new Location("LA", USA, null, 0);

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(NEW_YORK_IP)).thenReturn(location);

        LocalizationService localizationService =Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn("WELCOME");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers1 = new HashMap<String, String>();
        headers1.put(IP_ADDRESS_HEADER, NEW_YORK_IP);

        String actual = messageSender.send(headers1);
        assertEquals(expected, actual);
    }

}