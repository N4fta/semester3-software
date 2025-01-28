package com.fontys.nameless_backend.Controller;

import com.fontys.nameless_backend.Business.EventTestsService;
import com.fontys.nameless_backend.Domain.event.MainEvent;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/tests")
@AllArgsConstructor
public class EventTestsController {

    private final EventTestsService eventTestsService;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/new-random")
    public Map<String, String> addRandomEvent() {
        // Generate or retrieve a new random event
        MainEvent randomEvent = eventTestsService.addRandomEvent();

        // Parse the timestamp into a proper time object
        String eventTimestamp = randomEvent.getEventHeaders().getEventTimestamp();
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

        String userId = randomEvent.getEventPayload().getContext().getUser().getUserId();

        // Default the userId to "user" if it's empty
        if (userId == null || userId.isEmpty()) {
            userId = "user";
        }

        String eventName = randomEvent.getEventPayload().getContext().getPage().getTitle();

        // Create a structured JSON object
        Map<String, String> responseMessage = new HashMap<>();
        responseMessage.put("timestamp", formattedTime);
        responseMessage.put("user", userId); // Use the updated userId
        responseMessage.put("action", String.format("looked at %s", eventName));

        // Send the JSON object to the WebSocket topic
        messagingTemplate.convertAndSend("/topic/livefeed", responseMessage);

        // Return the same JSON object for confirmation
        return responseMessage;
    }
}
