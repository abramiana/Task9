package com.example.downloader;

import java.io.IOException;

/**
 * Інтерфейс для класу, який відповідає за завантаження зображень HTTP статусів.
 */
public interface ImageDownloader {
    /**
     * Метод для завантаження зображення з вказаним кодом статусу.
     *
     * @param code код статусу зображення, яке потрібно завантажити
     * @throws IOException виникає в разі помилки під час завантаження зображення
     */
    void downloadStatusImage(int code) throws IOException;
}