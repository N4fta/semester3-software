import React, { useState, useEffect } from "react";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { TrendingUp } from "lucide-react";
import { Client } from "@stomp/stompjs"; // Importing STOMP.js library

export default function LiveFeedWidget({ title, websocketUrl }) {
  const [messages, setMessages] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Initialize the STOMP client
    const client = new Client({
      brokerURL: websocketUrl,
      connectHeaders: {},
      debug: (str) => console.log(str),
      onConnect: () => {
        console.log("WebSocket connected"); // Log when connection is successful
        // Subscribe to the /topic/livefeed endpoint
        client.subscribe("/topic/livefeed", (message) => {
          console.log("Received message: ", message.body);
          const eventMessage = JSON.parse(message.body);
          // Add the new message to the state and keep only the latest 10 messages
          setMessages((prevMessages) =>
            [eventMessage, ...prevMessages].slice(0, 10)
          );
        });
      },
      onStompError: (error) => {
        console.error("STOMP error: ", error);
        setError("Failed to connect to the live feed"); // Update the error state if connection fails
      },
      onWebSocketClose: () => {
        console.log("WebSocket connection closed"); // Log when the WebSocket closes unexpectedly
      },
    });

    client.activate();

    // Cleanup function to deactivate the WebSocket client on component unmount
    return () => {
      client.deactivate();
    };
  }, [websocketUrl]); // The effect depends on the websocketUrl, so it will re-run if the URL changes

  // Render error message if there was an issue with the WebSocket connection
  if (error) {
    return <div>Error: {error}</div>;
  }

  return (
    <Card className="flex flex-col h-full w-full">
      <CardHeader className="items-center pb-0 h-1/6">
        <CardTitle className="text-[clamp(1rem, 5vw, 2rem)] font-bold text-gray-800">
          {title} {/* Display the title of the widget */}
        </CardTitle>
      </CardHeader>
      <CardContent className="flex-1 overflow-y-auto">
        {/* If there are no messages, show a "No messages yet" message */}
        {messages.length === 0 ? (
          <div className="text-muted-foreground">No messages yet...</div>
        ) : (
          // Otherwise, display the list of messages
          <ul className="space-y-2">
            {messages.map((message, index) => {
              const displayText = `${message.timestamp} ${message.user} ${message.action}`;
              return (
                <li
                  key={index}
                  className="bg-muted p-2 rounded shadow-sm text-sm"
                >
                  {displayText}
                </li>
              );
            })}
          </ul>
        )}
      </CardContent>
      <footer className="flex justify-between items-center text-sm mt-4 p-2 border-t">
        <div className="flex items-center gap-2 font-medium leading-none">
          Live feed updating <TrendingUp className="h-4 w-4" />{" "}
          {/* Display live feed status */}
        </div>
        {/*<div className="leading-none text-muted-foreground">via WebSocket</div>*/}
      </footer>
    </Card>
  );
}
