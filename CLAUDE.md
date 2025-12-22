# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Vaadin 25 + Spring Boot 4 web application demonstrating TestBench testing patterns. It uses Java 21, Spring Data JPA with H2 in-memory database, and Spring Security for authentication.

## Build Commands

```bash
# Run development server (hot-reload enabled)
./mvnw spring-boot:run

# Run unit tests only
./mvnw test

# Run all tests including integration tests
./mvnw verify -Pit

# Build production JAR with optimizations
./mvnw clean package -Pproduction

# Run packaged application
java -jar target/my-app-1.0-SNAPSHOT.jar
```

## Running a Single Test

```bash
# Run specific unit test class
./mvnw test -Dtest=HelloWorldViewTest

# Run specific integration test (requires server running or use -Pit profile)
./mvnw verify -Pit -Dit.test=SimpleIT
```

## Architecture

```
Views (Vaadin @Route components)
    ↓
MainLayout (App shell with navigation)
    ↓
Services (Business logic, @Service)
    ↓
Repositories (Spring Data JPA)
    ↓
Entities (JPA @Entity classes)
```

**Key architectural decisions:**
- Server-side rendering via Vaadin Flow - UI components render on server, not client
- All views extend from `MainLayout` as the parent layout
- Security configured in `SecurityConfiguration.java` with role-based access (USER, ADMIN)
- `AuthenticatedUser` wrapper provides current user context throughout the app

## Testing Patterns

**Unit Tests** (`src/test/java`):
- Extend `SpringUIUnitTest` from `spring-boot-test-bench` for Vaadin component testing
- Test views in isolation without browser/server

**Integration Tests** (`*IT.java`):
- Extend `BrowserTestBase` for Selenium-based browser tests
- Use Vaadin TestBench for element interactions
- The `it` Maven profile auto-starts server via failsafe plugin

## Test Users

Defined in `data.sql`:
- `user` / `user` - USER role
- `admin` / `admin` - ADMIN role

## Key Files

- `Application.java` - Spring Boot entry point, PWA and theme configuration
- `MainLayout.java` - Navigation drawer, header, user menu
- `SecurityConfiguration.java` - Auth setup, protected routes, BCrypt encoding
- `application.properties` - Server config, JPA settings, Vaadin production mode
