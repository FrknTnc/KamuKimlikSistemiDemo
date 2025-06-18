
// Proje: Kamu Kimlik ve Yetkilendirme Sistemi
// Amaç: TÜBİTAK BİLGEM ilânındaki teknolojileri kapsayan gerçekçi, çok katmanlı, mikroservis tabanlı bir uygulama

// === KLASÖR YAPISI ===
/*
project-root/
├── auth-service/         --> JWT tabanlı kimlik doğrulama (başlangıç noktası)
├── user-service/         --> Kullanıcı CRUD + PostgreSQL
├── permission-service/   --> Rol & yetki kontrolü
├── log-service/          --> Kafka consumer + Elasticsearch
├── notification-service/ --> RabbitMQ bildirim servisi
├── api-gateway/          --> Opsiyonel servis yönlendirici
├── frontend/             --> React tabanlı kullanıcı arayüzü
├── docker-compose.yml    --> PostgreSQL, Redis, Kafka, Elasticsearch, RabbitMQ ortamları
└── README.md             --> Proje dokümantasyonu
*/

// === 1. AUTH-SERVICE ===
// Spring Boot 3.x, Java 17, JWT ile authentication

// auth-service/src/main/java/com/example/auth/AuthServiceApplication.java

package com.example.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}