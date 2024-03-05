package com.example.checker;

import java.io.IOException;

/**
 * Інтерфейс HttpStatusCheckerInterface визначає методи для перевірки статусів HTTP-відповідей та отримання URL зображень,
 * пов'язаних із цими статусами.
 */
public interface HttpStatusCheckerInterface {
    /**
     * Метод для отримання URL зображення для вказаного коду статусу HTTP-відповіді.
     *
     * @param code код статусу HTTP-відповіді
     * @return URL зображення для вказаного коду статусу
     * @throws IOException якщо виникла помилка під час виконання запиту або отримання відповіді
     */
    String getStatusImage(int code) throws IOException;
}