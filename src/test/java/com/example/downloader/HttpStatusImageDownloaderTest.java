package com.example.downloader;

import com.example.checker.HttpStatusChecker;
import com.example.downloader.HttpStatusImageDownloader;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class HttpStatusImageDownloaderTest {

    @Test
    void testDownloadStatusImage_Success() throws IOException {
        // Створення фейкового об'єкту com.example.checker.HttpStatusChecker
        HttpStatusChecker fakeChecker = new HttpStatusChecker() {
            @Override
            public String getStatusImage(int code) {
                return "https://http.cat/301.jpg"; // Повертаємо сталий URL зображення
            }
        };

        // Створення об'єкту com.example.downloader.HttpStatusImageDownloader з фейковим com.example.checker.HttpStatusChecker
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader(fakeChecker);

        // Перевірка, що метод завантаження працює успішно для коду статусу 200
        assertDoesNotThrow(() -> downloader.downloadStatusImage(200));
        // Можна також перевірити, чи файл існує, але це залежить від середовища виконання тестів
    }

    @Test
    void testDownloadStatusImage_Failure() {
        // Створення фейкового об'єкту com.example.checker.HttpStatusChecker
        HttpStatusChecker fakeChecker = new HttpStatusChecker() {
            @Override
            public String getStatusImage(int code) {
                return null; // Повертаємо null, щоб викликати FileNotFoundException
            }
        };

        // Створення об'єкту com.example.downloader.HttpStatusImageDownloader з фейковим com.example.checker.HttpStatusChecker
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader(fakeChecker);

        // Перевірка, що метод генерує FileNotFoundException для коду статусу 404
        assertThrows(FileNotFoundException.class, () -> downloader.downloadStatusImage(404));
    }
}
