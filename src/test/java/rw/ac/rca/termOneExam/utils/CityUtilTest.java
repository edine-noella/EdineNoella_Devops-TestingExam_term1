package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.service.CityService;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityUtilTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private InputValidity validator;


    @Test
    public void allCities_WeatherLessThan40(){

        when(cityService.getAll()).thenReturn(Arrays.asList(new City(108, "Huye", 29.0),
                new City(109, "Nyabihu",40.0)));
        assertEquals(0,validator.lessThan40Validity(cityService.getAll()));

    }

    @Test
    public void allCitiesWeather_GreaterThan10(){

            new City(109, "Nyabihu",40.0);

        when(cityService.getAll()).thenReturn(Arrays.asList(new City(109, "Huye", 24.0),
                new City(109, "Nyabihu",40.0)));
        assertEquals(0,validator.greaterThan10Validity(cityService.getAll()));
    }

    @Test
    public void musanzeAndKigaliContainedInCites(){
        when(cityService.getAll()).thenReturn(Arrays.asList(
                new City(109, "Musanze", 24.0),
                new City(110, "Kigali", 24.0),
                new City(111, "Nyabihu",40.0)));

        assertEquals(true,validator.containsMusanzeAndKigali(cityService.getAll()));
    }
    @Test
    public void testMocking() {
        List<City> mockedList = Mockito.mock(ArrayList.class);
        City city = new City("Musanze", 18);
        mockedList.add(city);
        Mockito.verify(mockedList).add(city);

        assertEquals(0, mockedList.size());
    }

    @Test
    public void testSpying() {
        List<City> spyList = Mockito.spy(ArrayList.class);
        City city = new City("Musanze", 18);
        spyList.add(city);
        Mockito.verify(spyList).add(city);

        assertEquals(1, spyList.size());
    }
}