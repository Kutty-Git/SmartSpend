# System Architecture

```mermaid
flowchart LR
 Browser[Vanilla HTML CSS JS] -->|Fetch + Bearer JWT| Controller[Spring REST Controllers]
 Controller --> Security[JWT Filter and Spring Security]
 Controller --> Domain[Domain calculations and validation]
 Domain --> Repo[Spring Data repositories]
 Repo --> DB[(H2 or MySQL)]
 Controller --> PDF[OpenPDF report generator]
```

The servlet filter validates the signed token, controllers enforce role boundaries, repositories scope records by the authenticated user, and response maps avoid exposing entity passwords. The current code keeps the domain classes compact for viva readability; business calculations are contained in controller services/helpers rather than frontend code.
