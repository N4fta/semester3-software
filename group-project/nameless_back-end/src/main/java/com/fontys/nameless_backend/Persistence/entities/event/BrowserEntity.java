package com.fontys.nameless_backend.Persistence.entities.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrowserEntity {
    private String name;
    private String version;
    private int viewportWidth;
    private int viewportHeight;
    private String language;
    private boolean cookiesEnabled;
    private boolean javaScriptEnabled;
    private boolean doNotTrackEnabled;
}
