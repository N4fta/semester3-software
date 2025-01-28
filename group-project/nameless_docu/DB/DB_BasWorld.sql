CREATE DATABASE UserEventTracking;

USE UserEventTracking;

-- Table for Event
CREATE TABLE Event (
    id INT PRIMARY KEY,
    eventType ENUM('bas-user-event') NOT NULL
);

-- Table for Event Headers
CREATE TABLE EventHeaders (
    id INT PRIMARY KEY,
    entity ENUM('userEvent') NOT NULL,
    entityKey VARCHAR(255) NOT NULL,
    eventMainType ENUM('CREATE') NOT NULL,
    eventSubType ENUM('newUserEvent') NOT NULL,
    eventTimestamp DATETIME NOT NULL,
    publishedBy ENUM('buttPackage') NOT NULL,
    policyVersion VARCHAR(10) NOT NULL,
    eventId INT,
    FOREIGN KEY (eventId) REFERENCES Event(id)
);

-- Table for Application in EventPayload
CREATE TABLE Application (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    version VARCHAR(50) NOT NULL,
    environment VARCHAR(50) NOT NULL,
    eventId INT,
    FOREIGN KEY (eventId) REFERENCES Event(id)
);

-- Table for Context in EventPayload
CREATE TABLE Context (
    id INT  PRIMARY KEY,
    eventId INT,
    FOREIGN KEY (eventId) REFERENCES Event(id)
);

-- Table for Page in Context
CREATE TABLE Page (
    id INT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    designSize ENUM('designSize-xs', 'designSize-sm', 'designSize-md', 'designSize-lg', 'designSize-xl') NOT NULL,
    url VARCHAR(255) NOT NULL,
    canonicalUrl VARCHAR(255) NOT NULL,
    contextId INT,
    FOREIGN KEY (contextId) REFERENCES Context(id)
);

-- Table for Session in Context
CREATE TABLE Session (
    id INT PRIMARY KEY,
    buttSessionId VARCHAR(255) NOT NULL,
    referer VARCHAR(255),
    contextId INT,
    FOREIGN KEY (contextId) REFERENCES Context(id)
);

-- Table for VendorClickIds in Session
CREATE TABLE VendorClickIds (
    id INT PRIMARY KEY,
    google VARCHAR(255),
    bing VARCHAR(255),
    facebook VARCHAR(255),
    linkedIn VARCHAR(255),
    sessionId INT,
    FOREIGN KEY (sessionId) REFERENCES Session(id)
);

-- Table for UTM Parameters in Session
CREATE TABLE UTMParameters (
    id INT PRIMARY KEY,
    source VARCHAR(255),
    medium VARCHAR(255),
    campaign VARCHAR(255),
    term VARCHAR(255),
    content VARCHAR(255),
    sessionId INT,
    FOREIGN KEY (sessionId) REFERENCES Session(id)
);

-- Table for Client in Context
CREATE TABLE Client (
    id INT PRIMARY KEY,
    buttClientId VARCHAR(255) NOT NULL,
    rawUserAgent TEXT NOT NULL,
    contextId INT,
    FOREIGN KEY (contextId) REFERENCES Context(id)
);

-- Table for VendorClientIds in Client
CREATE TABLE VendorClientIds (
    id INT PRIMARY KEY,
    googleAnalytics VARCHAR(255),
    bing VARCHAR(255),
    facebook VARCHAR(255),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

-- Table for Device in Client
CREATE TABLE Device (
    id INT PRIMARY KEY,
    type VARCHAR(50),
    vendor VARCHAR(50),
    model VARCHAR(50),
    screenWidth INT,
    screenHeight INT,
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

-- Table for OperatingSystem in Client
CREATE TABLE OperatingSystem (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    version VARCHAR(50),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

-- Table for Browser in Client
CREATE TABLE Browser (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    version VARCHAR(50),
    viewportWidth INT,
    viewportHeight INT,
    language VARCHAR(50),
    cookiesEnabled BOOLEAN,
    javaScriptEnabled BOOLEAN,
    doNotTrackEnabled BOOLEAN,
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

-- Table for Engine in Client
CREATE TABLE Engine (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    version VARCHAR(50),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

-- Table for Geolocation in Client
CREATE TABLE Geolocation (
    id INT PRIMARY KEY,
    latitude VARCHAR(50),
    longitude VARCHAR(50),
    country VARCHAR(50),
    region VARCHAR(50),
    city VARCHAR(50),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(id)
);

-- Table for User in Context
CREATE TABLE User (
    id INT PRIMARY KEY,
    userId VARCHAR(255) NOT NULL,
    userEmailAddress VARCHAR(255) NOT NULL,
    personId VARCHAR(255),
    personEmailAddress VARCHAR(255),
    companyId VARCHAR(255),
    companyName VARCHAR(255),
    contextId INT,
    FOREIGN KEY (contextId) REFERENCES Context(id)
);

-- Definitions for Events such as PageVisitEvent, ElementVisibilityEvent, etc.
CREATE TABLE EventType (
    id INT PRIMARY KEY,
    eventName ENUM('page_visit', 'element_visibility', 'element_interaction', 'view_item_list', 'select_item', 'other') NOT NULL,
    eventId INT,
    FOREIGN KEY (eventId) REFERENCES Event(id)
);

-- Table for storing PageVisitEvent specific data
CREATE TABLE PageVisitEvent (
    id INT PRIMARY KEY,
    pageType VARCHAR(50) NOT NULL,
    pageLanguage VARCHAR(50) NOT NULL,
    eventTypeId INT,
    FOREIGN KEY (eventTypeId) REFERENCES EventType(id)
);
