package com.fontys.nameless_backend.Domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Browser {
    private String name;
    private String version;
    private int viewportWidth;
    private int viewportHeight;
    private String language;
    private boolean cookiesEnabled;
    private boolean javaScriptEnabled;
    private boolean doNotTrackEnabled;
}
