# Java Docker Proxy with Cache

## Overview

This project is a Java-based proxy application that aggregates data from **three Docker-based services**, returning the current **date and time** from them. The proxy implements a **cache layer with TTL (time-to-live)** and a **retry mechanism** to improve performance and reliability.

If the requested data is available in cache, it is returned immediately. Otherwise, the proxy queries the Docker services again, updates the cache, and then returns the response.

---

## Features

* Aggregates data from 3 Docker services
* Returns date and time information from each service
* In-memory cache with TTL expiration
* Automatic retry mechanism for failed requests
* JSON-based configuration using Jackson
* Simple proxy abstraction layer for service communication

---

## Architecture

The system is composed of:

* **Proxy Layer**

  * Central entry point for requests
  * Coordinates Docker service calls
  * Handles caching and retries

* **Docker Connections**

  * Represents each external Docker service
  * Responsible for fetching data from containers

* **Cache Layer**

  * Stores recent responses
  * Removes expired entries based on TTL

* **Configuration Loader**

  * Reads JSON configuration file
  * Defines:

    * Docker connections
    * Cache TTL
    * Retry count

---

## Configuration

The application uses a JSON file loaded at startup.

Example structure:

```json
{
  "cacheTTL": 5000,
  "retryCount": 3,
  "connections": [
    "http://docker1:8080",
    "http://docker2:8080",
    "http://docker3:8080"
  ]
}
```

### Fields

* `cacheTTL`: Time in milliseconds that a cache entry remains valid
* `connections`: List of Docker service endpoints

---

## How It Works

1. Client requests data from the Proxy
2. Proxy checks cache:

   * If valid entry exists → returns cached data
   * If not → continues to step 3
3. Proxy queries each Docker service
4. If a request fails, it is retried up to `retryCount`
5. Successful responses are stored in cache
6. Combined result is returned to the client

---

## Dependencies

* Java 8+
* Jackson Databind (for JSON parsing)
* Docker (for running external services)

---

## Project Structure

```
com.Dorcker
├── connection
│   ├── Proxy.java
│   ├── DockerConnect.java
│   └── Config.java
├── cache
│   ├── Cache.java
│   └── CacheEntry.java
└── Main.java
```

---

## Notes

* Cache cleanup runs periodically or during access (depending on implementation)
* Failed Docker calls are retried automatically up to the configured limit
* The system is designed to be lightweight and easily extensible (e.g., adding more Docker services)

---

## Possible Improvements

* Replace in-memory cache with Redis
* Add asynchronous requests (parallel Docker calls)
* Implement metrics (response time, cache hit rate)
* Add health check endpoint
* Improve logging system

---

If you want, I can also:

* refactor your Proxy class into a cleaner architecture
* fix your cache removal bug you showed earlier
* or draw a system diagram for this project
