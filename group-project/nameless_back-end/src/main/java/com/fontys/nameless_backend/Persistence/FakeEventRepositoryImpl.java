package com.fontys.nameless_backend.Persistence;

import com.fontys.nameless_backend.Business.EventRepository;
import com.fontys.nameless_backend.Domain.event.ItemCount;
import com.fontys.nameless_backend.Domain.event.MainEvent;
import com.fontys.nameless_backend.Persistence.entities.event.CountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class FakeEventRepositoryImpl implements EventRepository {
    private List<MainEvent> events;

    @Override
    public void AddEvent(MainEvent event) {
        events.add(event);
    }

    @Override
    public List<MainEvent> GetAllEvents() {
        return events;
    }

    @Override
    public List<ItemCount> getItemCountTrucks() {
        return List.of();
    }

    @Override
    public List<ItemCount> getItemCountBrands() {
        return List.of();
    }

    @Override
    public List<ItemCount> getBrowserDistribution() {
        return List.of();
    }

    @Override
    public List<ItemCount> getLanguageDistribution() {
        return List.of();
    }

    @Override
    public List<ItemCount> GetOperatingSystemDistribution() {
        return List.of();
    }

    @Override
    public List<ItemCount> GetCountryDistribution() {
        return List.of();
    }

    @Override
    public List<ItemCount> GetItemCountCategories() {
        return List.of();
    }

    @Override
    public List<ItemCount> GetDevicesCount() {
        return List.of();
    }

    @Override
    public List<ItemCount> GetMarketingSourceCount() {
        return List.of();
    }

    @Override
    public CountEntity GetSessionsCount(int days) {
        return null;
    }

    @Override
    public CountEntity GetEventCount() {
        return null;
    }

    @Override
    public List<ItemCount> getTotalEventsByDays(int daysAgo) {
        return List.of();
    }
}
