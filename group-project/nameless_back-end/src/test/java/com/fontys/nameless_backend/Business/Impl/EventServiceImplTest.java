package com.fontys.nameless_backend.Business.Impl;

import com.fontys.nameless_backend.Business.EventRepository;
import com.fontys.nameless_backend.Domain.event.ItemCount;
import com.fontys.nameless_backend.Domain.event.MainEvent;
import com.fontys.nameless_backend.Persistence.entities.event.CountEntity;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @Test
    void testAddEvent_shouldCallRepository() {

        // Arrange
        MainEvent event = new MainEvent();

        // Act
        eventService.AddEvent(event);

        // Assert
        verify(eventRepository).AddEvent(event);
    }

    @Test
    void testAddEvent_shouldThrowExceptionWhenRepositoryFails() {
        // Arrange
        MainEvent event = new MainEvent();
        doThrow(new RuntimeException("Repository error")).when(eventRepository).AddEvent(event);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> eventService.AddEvent(event));
        verify(eventRepository).AddEvent(event);
    }

    @Test
    void testGetAllEvents_shouldReturnAllEvents() {

        // Arrange
        MainEvent event1 = new MainEvent();
        MainEvent event2 = new MainEvent();
        List<MainEvent> mockEvents = Arrays.asList(event1, event2);
        when(eventRepository.GetAllEvents()).thenReturn(mockEvents);

        // Act
        List<MainEvent> result = eventService.GetAllEvents();

        // Assert
        assertEquals(mockEvents, result);
        verify(eventRepository).GetAllEvents();
    }

    @Test
    void testGetBrowserDistribution() {

        // Arrange
        List<ItemCount> mockData = Arrays.asList(
                new ItemCount("Chrome", 200),
                new ItemCount("Firefox", 50));
        when(eventRepository.getBrowserDistribution()).thenReturn(mockData);

        // Act
        Map<String, Integer> browserDistribution = eventService.GetBrowserDistribution();

        // Assert
        assertEquals(2, browserDistribution.size());
        assertEquals(200, browserDistribution.get("Chrome"));
        assertEquals(50, browserDistribution.get("Firefox"));
        verify(eventRepository).getBrowserDistribution();
    }

    @Test
    void testGetCountryDistribution_shouldReturnCountryDistributionMap() {

        // Arrange
        List<ItemCount> mockItemCounts = List.of(
                new ItemCount("USA", 10),
                new ItemCount("Netherlands", 20));
        when(eventRepository.GetCountryDistribution()).thenReturn(mockItemCounts);

        // Act
        Map<String, Integer> countryDistribution = eventService.GetCountryDistribution();

        // Assert
        assertEquals(2, countryDistribution.size());
        assertEquals(10, countryDistribution.get("USA"));
        assertEquals(20, countryDistribution.get("Netherlands"));
        verify(eventRepository).GetCountryDistribution();
    }

    @Test
    void testGetDeviceCount() {
        // Arrange
        List<ItemCount> mockDevicesCounts = List.of(
                new ItemCount("laptop", 200),
                new ItemCount("mobile", 50),
                new ItemCount("tablet", 100));
        when(eventRepository.GetDevicesCount()).thenReturn(mockDevicesCounts);

        // Act
        Map<String, Integer> countDevices = eventService.GetDeviceCount();

        // Assert
        assertEquals(3, countDevices.size());
        assertEquals(200, countDevices.get("laptop"));
        assertEquals(50, countDevices.get("mobile"));
        assertEquals(100, countDevices.get("tablet"));
        verify(eventRepository).GetDevicesCount();
    }

    @Test
    void testGetItemCountBrands() {
        // Arrange
        List<ItemCount> mockBrandsCounts = List.of(
                new ItemCount("Volvo", 30),
                new ItemCount("Iveco", 15),
                new ItemCount("DAF", 42),
                new ItemCount("MAN", 50));
        when(eventRepository.getItemCountBrands()).thenReturn(mockBrandsCounts);

        // Act
        Map<String, Integer> countBrands = eventService.GetItemCountBrands();

        // Assert
        assertEquals(4, countBrands.size());
        assertEquals(30, countBrands.get("Volvo"));
        assertEquals(15, countBrands.get("Iveco"));
        assertEquals(42, countBrands.get("DAF"));
        assertEquals(50, countBrands.get("MAN"));
        verify(eventRepository).getItemCountBrands();
    }

    @Test
    void testGetItemCountCategories() {
        // Arrange
        ItemCount itemCount1 = new ItemCount("Category1", 5);
        ItemCount itemCount2 = new ItemCount("Category2", 10);
        when(eventRepository.GetItemCountCategories()).thenReturn(Arrays.asList(itemCount1, itemCount2));

        // Act
        Map<String, Integer> result = eventService.GetItemCountCategories();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(5, result.get("Category1"));
        assertEquals(10, result.get("Category2"));
    }

    @Test
    void testGetItemCountTrucks() {

        // Arrange
        List<ItemCount> mockData = Arrays.asList(
                new ItemCount("Truck A", 300),
                new ItemCount("Truck B", 150));
        when(eventRepository.getItemCountTrucks()).thenReturn(mockData);

        // Act
        Map<String, Integer> countTrucks = eventService.GetItemCountTrucks();

        // Assert
        assertEquals(2, countTrucks.size());
        assertEquals(300, countTrucks.get("Truck A"));
        assertEquals(150, countTrucks.get("Truck B"));
        verify(eventRepository).getItemCountTrucks();
    }

    @Test
    void testGetLanguageDistribution() {

        // Arrange
        List<ItemCount> mockData = Arrays.asList(
                new ItemCount("English", 120),
                new ItemCount("Spanish", 80));
        when(eventRepository.getLanguageDistribution()).thenReturn(mockData);

        // Act
        Map<String, Integer> languageDistribution = eventService.GetLanguageDistribution();

        // Assert
        assertEquals(2, languageDistribution.size());
        assertEquals(120, languageDistribution.get("English"));
        assertEquals(80, languageDistribution.get("Spanish"));
        verify(eventRepository).getLanguageDistribution();
    }

    @Test
    void testGetMarketingSourceCount() {
        // Arrange
        List<ItemCount> mockItemCounts = List.of(
                new ItemCount("Facebook", 5),
                new ItemCount("LinkedIn", 10),
                new ItemCount("YouTube", 15));
        when(eventRepository.GetMarketingSourceCount()).thenReturn(mockItemCounts);

        // Act
        Map<String, Integer> result = eventService.GetMarketingSourceCount();

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(5), result.get("Facebook"));
        assertEquals(Integer.valueOf(10), result.get("LinkedIn"));
        assertEquals(Integer.valueOf(15), result.get("YouTube"));
    }

    @Test
    void testGetOperatingSystemDistribution_shouldReturnOSDistributionMap() {

        // Arrange
        List<ItemCount> mockItemCounts = List.of(
                new ItemCount("Windows", 15),
                new ItemCount("Linux", 5));
        when(eventRepository.GetOperatingSystemDistribution()).thenReturn(mockItemCounts);

        // Act
        Map<String, Integer> osDistribution = eventService.GetOperatingSystemDistribution();

        // Assert
        assertEquals(2, osDistribution.size());
        assertEquals(15, osDistribution.get("Windows"));
        assertEquals(5, osDistribution.get("Linux"));
        verify(eventRepository).GetOperatingSystemDistribution();
    }

    @Test
    void testGetSessionsCount_shouldReturnSessionCount() {
        // Arrange
        CountEntity mockCountEntity = new CountEntity();
        mockCountEntity.setCount(42);
        when(eventRepository.GetSessionsCount(7)).thenReturn(mockCountEntity);

        // Act
        int sessionCount = eventService.GetSessionsCount(7);

        // Assert
        assertEquals(42, sessionCount);
        verify(eventRepository).GetSessionsCount(7);
    }

    // !! You need to use todays date for the test to pass
    @Test
    void testGetTotalEventsByDays_withMultipleDays_shouldReturnEventCountByDay() {
        // Arrange
        String todaysDate = LocalDate.now().toString(); // yyyy-MM-dd format
        String twoDaysBeforeToday = LocalDate.now().minusDays(2).toString(); // yyyy-MM-dd format

        List<ItemCount> mockData = List.of(
                new ItemCount(todaysDate + "T00:00:00Z", 5),
                new ItemCount(twoDaysBeforeToday + "T00:00:00Z", 3));
        when(eventRepository.getTotalEventsByDays(anyInt())).thenReturn(mockData);

        // Act
        Map<String, Integer> result = eventService.getTotalEventsByDays(3);
        Map<String, Integer> expected = Map.of(
                twoDaysBeforeToday, 3, // Two days ago
                LocalDate.now().minusDays(1).toString(), 0, // Yesterday
                todaysDate, 5 // Today
        );

        // Assert
        assertEquals(expected, result);
        verify(eventRepository).getTotalEventsByDays(anyInt());
    }

    @Test
    void testGetTotalEventsByDays_withNoEventsForSomeDays_shouldReturnZeroCountForMissingDays() {
        // Arrange
        String todaysDate = LocalDateTime.now().atZone(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT)
                .substring(0, 10);
        String oneDayBeforeToday = LocalDate.now().minusDays(1).toString(); // yyyy-MM-dd format
        String twoDaysBeforeToday = LocalDate.now().minusDays(2).toString(); // yyyy-MM-dd format

        List<ItemCount> mockData = List.of(
                new ItemCount(todaysDate + "T00:00:00Z", 5));
        when(eventRepository.getTotalEventsByDays(anyInt())).thenReturn(mockData);

        // Act
        Map<String, Integer> result = eventService.getTotalEventsByDays(3);
        Map<String, Integer> expected = Map.of(
                twoDaysBeforeToday, 0, // Two days ago
                oneDayBeforeToday, 0, // Yesterday
                todaysDate, 5 // Today
        );

        // Assert
        assertEquals(expected, result);
        verify(eventRepository).getTotalEventsByDays(anyInt());
    }

    @Test
    void testConvertItemCountToMap_shouldIgnoreBlankItems() {
        // Arrange
        List<ItemCount> mockItemCounts = Arrays.asList(
                new ItemCount("Valid", 10),
                new ItemCount("", 5));
        when(eventRepository.GetCountryDistribution()).thenReturn(mockItemCounts);

        // Act
        Map<String, Integer> countryDistribution = eventService.GetCountryDistribution();

        // Assert
        assertEquals(1, countryDistribution.size());
        assertEquals(10, countryDistribution.get("Valid"));
        verify(eventRepository).GetCountryDistribution();
    }

}
