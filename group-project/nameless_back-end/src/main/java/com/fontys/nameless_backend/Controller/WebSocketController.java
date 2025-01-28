package com.fontys.nameless_backend.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    // Endpoint for handling messages sent to '/app/livefeed'
    @MessageMapping("/livefeed")
    @SendTo("/topic/livefeed")
    public String sendLiveFeedMessage(String message) {
        // This will send the message to all clients subscribed to /topic/livefeed
        return message;
    }
}
