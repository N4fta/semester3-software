package com.fontys.nameless_backend.Business;

import com.fontys.nameless_backend.Domain.event.ItemCount;
import com.fontys.nameless_backend.Domain.event.MainEvent;
import com.fontys.nameless_backend.Persistence.entities.event.CountEntity;

import java.util.List;

public interface EventRepository {
    void AddEvent(MainEvent event);
    List<MainEvent> GetAllEvents();
    List<ItemCount> getItemCountTrucks();
    List<ItemCount> getItemCountBrands();
    List<ItemCount> getBrowserDistribution();
    List<ItemCount> getLanguageDistribution();
    List<ItemCount> GetOperatingSystemDistribution();
    List<ItemCount> GetCountryDistribution();
    List<ItemCount> GetItemCountCategories();
    List<ItemCount> GetDevicesCount();
    List<ItemCount> GetMarketingSourceCount();
    CountEntity GetSessionsCount(int days);
    CountEntity GetEventCount();
    List<ItemCount> getTotalEventsByDays(int daysAgo);
}
