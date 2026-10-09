# API Sentinel — API Testing & Reliability Platform

API Sentinel helps developers organize HTTP requests, run assertion-based test collections, and track endpoint reliability from one dashboard.

## Stack
- React + TypeScript + Vite + Lucide
- Java 21 + Spring Boot 3 + Spring Data JPA + Spring Security
- PostgreSQL 16
- Scheduled health checks and execution history
- Docker Compose

## Features
- Create, edit, execute, and organize API requests
- HTTP methods, URL, headers, query parameters, JSON body
- Test collections and status-code / response-time assertions
- Execute saved requests and collections on demand
- Scheduled endpoint health checks with persisted run history
- Latency, success rate, availability trends and status breakdowns
- Threshold rules and notification configuration records
- Shareable run report links and OpenAPI-style docs endpoint
- Responsive dashboard with request explorer, collections, monitor list, activity, and run details

## Quick start
Requirements: Docker Desktop + Compose.

```bash
docker compose up --build
```

Then open:
- Frontend: http://localhost:5174
- Backend API: http://localhost:8081/api/health
- API docs JSON: http://localhost:8081/api/docs

Demo credentials (HTTP Basic):
- `sentinel` / `sentinel123`
- `admin` / `admin123`

The dashboard uses a demo-friendly local state for instant interaction. The backend independently persists request definitions, collections, executions, monitors, and notification rules in PostgreSQL. API endpoints are listed below.

## Local development

### Backend
Install JDK 21 and Maven:
```bash
cd backend
mvn spring-boot:run
```
Configure `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, and `SPRING_DATASOURCE_PASSWORD` for a local PostgreSQL database.

### Frontend
Install Node.js 20+:
```bash
cd frontend
npm install
npm run dev
```

## API endpoints
All endpoints except `/api/health` and `/api/docs` use HTTP Basic authentication.

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/health` | API health |
| GET/POST | `/api/requests` | List/create requests |
| GET/PUT/DELETE | `/api/requests/{id}` | Read/update/delete request |
| POST | `/api/requests/{id}/run` | Execute a request and store result |
| GET/POST | `/api/collections` | List/create collections |
| POST | `/api/collections/{id}/run` | Execute collection requests |
| GET | `/api/executions` | Execution history |
| GET/POST | `/api/monitors` | List/create monitors |
| POST | `/api/monitors/{id}/run` | Run monitor immediately |
| GET | `/api/alerts/rules` | List alert rules |
| POST | `/api/alerts/rules` | Create alert rule |
| GET | `/api/reports/{shareToken}` | Read shareable execution report |

## Example request
```json
{
  "name": "JSONPlaceholder posts",
  "method": "GET",
  "url": "https://jsonplaceholder.typicode.com/posts/1",
  "headers": {},
  "queryParams": {},
  "body": "",
  "expectedStatus": 200,
  "maxResponseTimeMs": 2500
}
```

## Engineering notes
- Execution runs in a backend service with connection and read timeouts.
- Every execution is stored with status, latency, response snippet, and error details.
- Scheduled monitors run through Spring's scheduler and persist outcomes.
- PostgreSQL stores request definitions, collections, execution history, monitors, and rules.
- Demo HTTP Basic credentials are for local development only. For production, use secure password hashing, proper user ownership/tenant boundaries, secret encryption, outbound request allowlists/SSRF protection, rate limits, and managed authentication.
- The dashboard is currently a demo UI with local state; connect it to the REST API before presenting it as an integrated full-stack deployment.


## Project structure
```text
api-sentinel/
├── frontend/
├── backend/
└── docker-compose.yml
```
