package com.fontys.nameless_backend.Business;

import com.fontys.nameless_backend.Domain.event.MainEvent;

import java.util.List;
import java.util.Map;

public interface EventService {
    void AddEvent(MainEvent event);
    List<MainEvent> GetAllEvents();
    Map<String, Integer> GetCountryDistribution();
    Map<String, Integer> GetOperatingSystemDistribution();
    Map<String, Integer> GetLanguageDistribution();
    Map<String, Integer> GetBrowserDistribution();
    Map<String, Integer> GetItemCountTrucks();
    Map<String, Integer> GetItemCountBrands();
    Map<String, Integer> GetItemCountCategories();
    Map<String, Integer> GetDeviceCount();
    Map<String, Integer> GetMarketingSourceCount();
    Map<String, Integer> getTotalEventsByDays(int daysAgo);
    int GetSessionsCount(int days);
}
