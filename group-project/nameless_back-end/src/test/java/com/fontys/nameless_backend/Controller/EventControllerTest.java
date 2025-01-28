package com.fontys.nameless_backend.Controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fontys.nameless_backend.Business.EventService;

import com.fontys.nameless_backend.Domain.event.MainEvent;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Test
    void testGetAllEvents_shouldReturn200WithEvents() throws Exception {
        // Arrange
        List<MainEvent> events = List.of(new MainEvent(), new MainEvent());
        when(eventService.GetAllEvents()).thenReturn(events);

        // Act
        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());

        // Assert
        verify(eventService).GetAllEvents();
    }

    @Test
    void testGetBrowserDistribution_shouldReturn200WithDistribution() throws Exception {
        // Arrange
        Map<String, Integer> browserDistribution = Map.of("Chrome", 200, "Firefox", 100);
        when(eventService.GetBrowserDistribution()).thenReturn(browserDistribution);

        // Act
        mockMvc.perform(get("/events/distribution/browser"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.browserDistribution.Chrome").value(200))
                .andExpect(jsonPath("$.browserDistribution.Firefox").value(100));

        // Assert
        verify(eventService).GetBrowserDistribution();
    }

    @Test
    void testGetCountryDistribution_shouldReturn200WithDistribution() throws Exception {
        // Arrange
        Map<String, Integer> countryDistribution = Map.of("USA", 10, "UK", 5);
        when(eventService.GetCountryDistribution()).thenReturn(countryDistribution);

        // Act
        mockMvc.perform(get("/events/distribution/country"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.countryDistribution.USA").value(10))
                .andExpect(jsonPath("$.countryDistribution.UK").value(5));

        // Assert
        verify(eventService).GetCountryDistribution();
    }

    @Test
    void testGetDeviceDistribution_shouldReturn200WithItemCount() throws Exception {
        // Arrange
        Map<String, Integer> deviceDistribution = Map.of("Mobile", 150, "Desktop", 50);
        when(eventService.GetDeviceCount()).thenReturn(deviceDistribution);

        // Act
        mockMvc.perform(get("/events/distribution/device"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.itemCount.Mobile").value(150))
                .andExpect(jsonPath("$.itemCount.Desktop").value(50));

        // Assert
        verify(eventService).GetDeviceCount();
    }

    @Test
    void testGetItemCount_shouldReturn200WithItemCount() throws Exception {
        // Arrange
        Map<String, Integer> itemCount = Map.of("Trucks", 120);
        when(eventService.GetItemCountTrucks()).thenReturn(itemCount);

        // Act
        mockMvc.perform(get("/events/items/types"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.itemCount.Trucks").value(120));

        // Assert
        verify(eventService).GetItemCountTrucks();
    }

    @Test
    void testGetItemCountBrands_shouldReturn200WithItemCount() throws Exception {
        // Arrange
        Map<String, Integer> itemCountBrands = Map.of("Volvo", 50);
        when(eventService.GetItemCountBrands()).thenReturn(itemCountBrands);

        // Act
        mockMvc.perform(get("/events/items/brands"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.itemCount.Volvo").value(50));

        // Assert
        verify(eventService).GetItemCountBrands();
    }

    @Test
    void testGetItemCountCategories_shouldReturn200WithItemCount() throws Exception {
        // Arrange
        Map<String, Integer> itemCountCategories = Map.of("Heavy", 30);
        when(eventService.GetItemCountCategories()).thenReturn(itemCountCategories);

        // Act
        mockMvc.perform(get("/events/items/categories"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.itemCount.Heavy").value(30));

        // Assert
        verify(eventService).GetItemCountCategories();
    }

    @Test
    void testGetLanguageDistribution_shouldReturn200WithDistribution() throws Exception {
        // Arrange
        Map<String, Integer> languageDistribution = Map.of("English", 100, "German", 50);
        when(eventService.GetLanguageDistribution()).thenReturn(languageDistribution);

        // Act
        mockMvc.perform(get("/events/distribution/language"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.languageDistribution.English").value(100))
                .andExpect(jsonPath("$.languageDistribution.German").value(50));

        // Assert
        verify(eventService).GetLanguageDistribution();
    }

    @Test
    void testGetMarketingSourceDistribution_shouldReturn200WithItemCount() throws Exception {
        // Arrange
        Map<String, Integer> marketingSourceDistribution = Map.of("Google", 500, "Facebook", 200);
        when(eventService.GetMarketingSourceCount()).thenReturn(marketingSourceDistribution);

        // Act
        mockMvc.perform(get("/events/marketing/source"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.itemCount.Google").value(500))
                .andExpect(jsonPath("$.itemCount.Facebook").value(200));

        // Assert
        verify(eventService).GetMarketingSourceCount();
    }

    @Test
    void testGetOperatingSystemDistribution_shouldReturn200WithDistribution() throws Exception {
        // Arrange
        Map<String, Integer> osDistribution = Map.of("Windows", 50, "Mac", 20);
        when(eventService.GetOperatingSystemDistribution()).thenReturn(osDistribution);

        // Act
        mockMvc.perform(get("/events/distribution/os"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.operatingSystemDistribution.Windows").value(50))
                .andExpect(jsonPath("$.operatingSystemDistribution.Mac").value(20));

        // Assert
        verify(eventService).GetOperatingSystemDistribution();
    }

}
