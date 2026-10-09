# API Sentinel — API Testing & Reliability Platform

**A developer-focused platform for executing API requests, tracking response performance, and organizing API reliability checks.**

## Overview

API Sentinel is a full-stack API testing and reliability platform designed to help developers inspect API behavior, organize requests into collections, review execution history, and build toward automated endpoint monitoring.

The project focuses on API observability, response validation, request organization, and reliability engineering.

## Features

- API request execution and response inspection
- Request collections for organizing test cases
- Execution history for reviewing previous requests
- Response status and latency visibility
- Monitor and reliability-rule management foundations
- React dashboard for API testing workflows
- Spring Boot REST API
- PostgreSQL-backed persistence
- Docker Compose development setup

## Tech Stack

- **Frontend:** React, TypeScript, Vite
- **Backend:** Java 21, Spring Boot
- **Database:** PostgreSQL, Spring Data JPA
- **Security:** Spring Security
- **API execution:** Java HTTP client
- **Deployment:** Docker Compose

## Architecture

```text
React + TypeScript Dashboard
            |
            v
    Spring Boot REST API
            |
      +-----+------+
      |            |
      v            v
 PostgreSQL    HTTP Client
      |            |
      v            v
 Request data   Target APIs
 and history
```

## Getting Started

### Prerequisites

- Docker Desktop
- Git

### Run with Docker Compose

```bash
git clone https://github.com/harshavardhani22/API-Sentinel-Reliability-Platform.git
cd API-Sentinel-Reliability-Platform
docker compose up --build
```

Open the frontend at `http://localhost:5174`.

Backend health endpoint: `http://localhost:8081/api/health`.

### Demo Credentials

- Username: `sentinel`
- Password: `sentinel123`

Use demo credentials for local development only.

## Example Use Cases

- Test REST endpoints during development
- Group related API requests into collections
- Review response status and latency
- Investigate previous request executions
- Develop repeatable API reliability checks

## Future Enhancements

- Scheduled monitor execution
- Assertions for response body, headers, and status codes
- Retry policies with exponential backoff
- Historical latency charts and reliability metrics
- Alert notifications for failed checks
- API authentication profiles and secret management
- Automated integration and load tests
- Production-grade user authentication and authorization

## Engineering Concepts

- HTTP request execution
- REST API design
- Database persistence
- Response-time measurement
- Reliability checks and monitoring architecture
- Retry and failure-handling strategies
- Full-stack application architecture

## Security Note

This repository is a portfolio starter, not a production-ready monitoring service. Replace demo authentication, use secure password hashing, protect credentials and API secrets, and implement appropriate authorization before deploying publicly. Test requests carefully when targeting external services.

## Author

**Harshavardhani Balusu**

GitHub: [@harshavardhani22](https://github.com/harshavardhani22)

---

*Scheduled monitoring, advanced assertions, retries, and notification delivery should be described as implemented only after they are functional and tested in your version.*
