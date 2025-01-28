package com.fontys.nameless_backend.Controller;

import com.fontys.nameless_backend.Business.EventService;
import com.fontys.nameless_backend.Controller.Requests.event.MainEventRequest;
import com.fontys.nameless_backend.Controller.Responses.event.*;
import com.fontys.nameless_backend.Domain.event.MainEvent;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

    private EventService eventService;
    private ModelMapper modelMapper;
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping
    public List<MainEvent> GetAllEvents() {
        return eventService.GetAllEvents();
    }

    @GetMapping("/distribution/country")
    public CountryDistributionResponse GetCountryDistribution() {
        return CountryDistributionResponse.builder()
                .countryDistribution(eventService.GetCountryDistribution())
                .build();
    }

    @GetMapping("/distribution/os")
    public OperatingSystemDistributionResponse GetOperatingSystemDistribution() {
        return OperatingSystemDistributionResponse.builder()
                .operatingSystemDistribution(eventService.GetOperatingSystemDistribution())
                .build();
    }

    @GetMapping("/distribution/language")
    public LanguageDistributionResponse GetLanguageDistribution() {
        return LanguageDistributionResponse.builder()
                .languageDistribution(eventService.GetLanguageDistribution())
                .build();
    }

    @GetMapping("/distribution/browser")
    public BrowserDistributionResponse GetBrowserDistribution() {
        return BrowserDistributionResponse.builder()
                .browserDistribution(eventService.GetBrowserDistribution())
                .build();
    }

    @GetMapping("/distribution/device")
    public ItemCountResponse GetDeviceDistribution() {
        return ItemCountResponse.builder()
                .itemCount(eventService.GetDeviceCount())
                .build();
    }

    @GetMapping("/marketing/source")
    public ItemCountResponse GetMarketingSourceDistribution() {
        return ItemCountResponse.builder()
                .itemCount(eventService.GetMarketingSourceCount())
                .build();
    }

    @GetMapping("/items/types")
    public ItemCountResponse GetItemCount() {
        return ItemCountResponse.builder()
                .itemCount(eventService.GetItemCountTrucks())
                .build();
    }

    @GetMapping("/items/brands")
    public ItemCountResponse GetItemCountBrands() {
        return ItemCountResponse.builder()
                .itemCount(eventService.GetItemCountBrands())
                .build();
    }

    @GetMapping("/items/categories")
    public ItemCountResponse GetItemCountCategories() {
        return ItemCountResponse.builder()
                .itemCount(eventService.GetItemCountCategories())
                .build();
    }

    @GetMapping("/session/{day}")
    public int GetSessionATimePeriod(@PathVariable int day) {
        return eventService.GetSessionsCount(day);
    }

    @GetMapping("/by/{day}")
    public ItemCountResponse GetEventsByDay(@PathVariable int day) {
        return ItemCountResponse.builder()
                .itemCount(eventService.getTotalEventsByDays(day))
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> CreateEvent(@RequestBody @Valid MainEventRequest eventRequest) {
        if (!eventRequest.getEventPayload().getContext().getClient().getBrowser().isDoNotTrackEnabled()) {
            MainEvent newEvent = convertToDomain(eventRequest);
            eventService.AddEvent(newEvent);

            // Parse the timestamp into a proper time object
            String eventTimestamp = newEvent.getEventHeaders().getEventTimestamp();
            String formattedTime;
            try {
                // If the timestamp is in ISO-8601 format with a 'Z', use ZonedDateTime
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(eventTimestamp);
                formattedTime = zonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            } catch (Exception e) {
                // Fallback to handle if the timestamp is not in ISO-8601 format
                LocalDateTime localDateTime = LocalDateTime.parse(eventTimestamp);
                formattedTime = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            }

            String userId = newEvent.getEventPayload().getContext().getUser().getUserId();

            // Default the userId to "user" if it's empty
            if (userId == null || userId.isEmpty()) {
                userId = "user";
            }

            String eventName = newEvent.getEventPayload().getContext().getPage().getTitle();

            // Create a structured JSON object
            Map<String, String> responseMessage = new HashMap<>();
            responseMessage.put("timestamp", formattedTime);
            responseMessage.put("user", userId); // Use the updated userId
            responseMessage.put("action", String.format("looked at %s", eventName));
            // After creating the event, send a message to WebSocket clients
            messagingTemplate.convertAndSend("/topic/livefeed", responseMessage);  // Broadcast to all clients
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private MainEvent convertToDomain(MainEventRequest eventRequest) {
        return modelMapper.map(eventRequest, MainEvent.class);
    }
}