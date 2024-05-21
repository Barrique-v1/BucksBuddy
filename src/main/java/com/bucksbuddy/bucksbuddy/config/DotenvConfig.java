package com.bucksbuddy.bucksbuddy.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * DotenvConfig ist eine Konfigurationsklasse, die verwendet wird, um Umgebungsvariablen
 * aus einer .env-Datei zu laden und sie in die Systemumgebungsvariablen zu setzen.
 * Dies ermöglicht es der Spring Boot Anwendung, sensible Konfigurationswerte sicher zu nutzen,
 * ohne sie direkt im Quellcode oder in den application.properties-Dateien zu speichern.
 */
@Configuration
public class DotenvConfig {

    /**
     * Die init-Methode wird nach der Konstruktion der Bean ausgeführt.
     * Sie lädt die Umgebungsvariablen aus der .env-Datei und setzt sie als
     * Systemumgebungsvariablen, die von der Spring Boot Anwendung genutzt werden können.
     */
    @PostConstruct
    public void init() {
        // Lädt die Umgebungsvariablen aus der .env-Datei
        Dotenv dotenv = Dotenv.load();

        // Iteriert über alle geladenen Umgebungsvariablen und setzt sie als Systemvariablen
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}


