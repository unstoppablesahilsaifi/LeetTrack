# 📘 LeetTrack – Daily Coding Challenge Tracker

**LeetTrack** is a backend service designed to manage coding challenges, user submissions, and streaks — with support for caching via Redis and streak resets via a scheduled cron job.

---

## 🚀 Project Overview

LeetTrack helps users:
- Attempt daily coding challenges.
- Track their **submission streaks**.
- Cache frequently accessed data (like hot challenges) using **Redis**.
- Reset streaks daily using a **midnight cron job**.

This service forms the backend logic for any frontend/client application for a "Leetcode-like" experience.

---

## 🛠️ Technologies Used

| Tool / Framework       | Purpose                         |
|------------------------|----------------------------------|
| **Java 17+**           | Backend language                 |
| **Spring Boot**        | Core framework                   |
| **Spring Data JPA**    | ORM for database interaction     |
| **MySQL**              | Relational database              |
| **Memurai (Redis)**    | Caching via Redis (Windows-compatible) |
| **Spring Cache**       | Easy Redis integration           |
| **Lombok**             | To reduce boilerplate code       |
| **Scheduled Cron Jobs**| For resetting streaks daily      |
| **Swagger**            | API testing and documentation    |

---

## 📦 Features Implemented

- ✅ **User Registration** (via email, password, roles)
- ✅ **Create Challenges**
- ✅ **Submit Challenges**
- ✅ **Streak Tracking**
  - Updates when users submit on different days
  - Avoids incrementing for same-day submissions
- ✅ **Cron Job (Midnight Reset)**
  - Automatically resets streaks for inactive users
- ✅ **Redis Caching with Memurai**
  - Caches hot challenges (`@Cacheable`)
- ✅ **Exception Handling** with global `@ControllerAdvice`

---

## 🔄 Caching Logic

Hot challenges are cached using Spring's `@Cacheable` annotation:

```java
@Cacheable(value = "hotChallenges", key = "'hot'")
public List<Challenge> getHotChallenges() {
    log.info("Fetching hot challenges from DB");
    return challengeRepository.findTop10ByOrderByViewsDesc();
}
```

---

## ⏰ Cron Job Logic

Streak reset logic runs every midnight:

```java
@Scheduled(cron = "0 0 0 * * *")
public void resetStreaksIfMissed() {
    // Resets users who haven't submitted today
}
```

---

## 🧪 Testing with Swagger

Use Swagger UI to test:
- `/api/challenges`
- `/api/submissions`
- `/api/users`
- Try a submission to see if `streak` updates
- Try the same user again the same day (no duplicate streak increase)

---

## 🔧 Setup Instructions

1. Clone the repo and import it into IntelliJ or VSCode.
2. Configure `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/leettrack
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.cache.type=redis
spring.data.redis.host=localhost
spring.data.redis.port=6379
```
3. Make sure **Memurai** is running (Redis for Windows).
4. Launch the app with `LeettrackApplication.java`
5. Access Swagger at:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## ✅ Future Scope

- Daily Challenge Scheduler (can use cron or Redis TTL)
- Email notifications for reminders
- Leaderboard and gamification
- Integration with a frontend (React, Vue, etc.)

---

## 👨‍💻 Author

**Built with ❤️ by [You]**

If you have feedback, ideas, or issues — feel free to reach out!
