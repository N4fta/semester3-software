package com.fontys.nameless_backend.Business.Impl;

import com.fontys.nameless_backend.Business.EventRepository;
import com.fontys.nameless_backend.Business.EventService;
import com.fontys.nameless_backend.Domain.event.ItemCount;
import com.fontys.nameless_backend.Domain.event.MainEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService {
    private EventRepository eventRepository;

    @Override
    public void AddEvent(MainEvent event) {
        eventRepository.AddEvent(event);
    }

    @Override
    public List<MainEvent> GetAllEvents() {
        return eventRepository.GetAllEvents();
    }

    @Override
    public Map<String, Integer> GetCountryDistribution() {
        return ConvertItemCountToMap(eventRepository.GetCountryDistribution());
    }

    @Override
    public Map<String, Integer> GetOperatingSystemDistribution() {
        return ConvertItemCountToMap(eventRepository.GetOperatingSystemDistribution());
    }

    @Override
    public Map<String, Integer> GetLanguageDistribution() {
        return ConvertItemCountToMap(eventRepository.getLanguageDistribution());
    }

    @Override
    public Map<String, Integer> GetBrowserDistribution() {
        return ConvertItemCountToMap(eventRepository.getBrowserDistribution());
    }

    @Override
    public Map<String, Integer> GetItemCountTrucks() {
        return ConvertItemCountToMap(eventRepository.getItemCountTrucks());
    }

    @Override
    public Map<String, Integer> GetItemCountBrands() {
        return ConvertItemCountToMap(eventRepository.getItemCountBrands());
    }

    @Override
    public Map<String, Integer> GetItemCountCategories() {
        return ConvertItemCountToMap(eventRepository.GetItemCountCategories());
    }

    @Override
    public Map<String, Integer> GetDeviceCount() {
        return ConvertItemCountToMap(eventRepository.GetDevicesCount());
    }

    @Override
    public Map<String, Integer> GetMarketingSourceCount() {
        return ConvertItemCountToMap(eventRepository.GetMarketingSourceCount());
    }

    @Override
    public Map<String, Integer> getTotalEventsByDays(int daysAgo) {
        Map<String, Integer> totalEventsByDays = new LinkedHashMap<>();
        for (int i = daysAgo - 1; i >= 0; i--) {
            totalEventsByDays.put(LocalDate.now().minusDays(i).toString(), 0);
        }
        for(ItemCount ic : eventRepository.getTotalEventsByDays(daysAgo)) {
            LocalDate date = Instant.from(DateTimeFormatter.ISO_INSTANT.parse(ic.getItem())).atZone(ZoneOffset.UTC).toLocalDate();
            Integer currentCount = totalEventsByDays.get(date.toString());
            if(currentCount == null) {
                continue;
            }
            int count =  currentCount + ic.getCount();
            totalEventsByDays.put(date.toString(), count);
        }

        return totalEventsByDays.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    @Override
    public int GetSessionsCount(int days) {
        return eventRepository.GetSessionsCount(days).getCount();
    }

    private Map<String, Integer> ConvertItemCountToMap(List<ItemCount> itemCounts) {
        Map<String, Integer> map = new HashMap<>();
        for (ItemCount itemCount : itemCounts) {
            if(!itemCount.getItem().isBlank()) {
                map.put(itemCount.getItem(), itemCount.getCount());
            }
        }
        return map;
    }
}
