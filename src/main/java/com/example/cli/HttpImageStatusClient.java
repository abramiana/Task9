package com.example.cli;

import java.io.IOException;

/**
 * Інтерфейс HttpImageStatusClient визначає метод для взаємодії з користувачем через командний рядок
 * для запиту статусу HTTP та завантаження зображень, пов'язаних із цими статусами.
 */
public interface HttpImageStatusClient {
    /**
     * Метод askStatus використовується для запиту користувача статусу HTTP та завантаження
     * зображень, пов'язаних із цими статусами, через командний рядок.
     *
     * @throws IOException виникає в разі помилки під час взаємодії з користувачем або під час завантаження зображення
     */
    void askStatus() throws IOException;
}