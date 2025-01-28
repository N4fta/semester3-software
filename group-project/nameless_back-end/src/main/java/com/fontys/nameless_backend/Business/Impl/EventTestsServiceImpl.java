package com.fontys.nameless_backend.Business.Impl;

import java.io.File;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fontys.nameless_backend.Business.EventRepository;
import com.fontys.nameless_backend.Business.EventTestsService;
import com.fontys.nameless_backend.Domain.event.MainEvent;

@Service
public class EventTestsServiceImpl implements EventTestsService {
    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;
    private final Random random;

    public EventTestsServiceImpl(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
        random = new Random();
    }

    @Override
    public MainEvent addRandomEvent() {
        MainEvent newEvent = new MainEvent();
        String eventPath = String.format("generated(%d).json", random.nextInt(1, 11));
        try {
            newEvent = objectMapper.readValue(new File("example-events", eventPath), MainEvent.class);
        } catch (Exception e) {
            System.out.println("Failed conversion from file to object");
            System.out.println(eventPath);
        }
        eventRepository.AddEvent(newEvent);
        return newEvent;
    }
}
