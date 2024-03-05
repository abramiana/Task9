package com.example.cli;

import com.example.checker.HttpStatusChecker;
import com.example.cli.HttpImageStatusCli;
import com.example.downloader.HttpStatusImageDownloader;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class HttpImageStatusCliTest {

    @Test
    void testAskStatus_ValidInput() {
        // Arrange
        HttpStatusChecker checker = new HttpStatusChecker();
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader(checker);
        HttpImageStatusCli cli = new HttpImageStatusCli(checker, downloader);
        String input = "200\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Act
        cli.askStatus();
        String output = out.toString().trim();

        // Assert
        assertTrue(output.contains("Downloading image for status code 200..."));
        assertTrue(output.contains("Image downloaded successfully."));
    }

    @Test
    void testAskStatus_InvalidInput() {
        // Arrange
        HttpStatusChecker checker = new HttpStatusChecker();
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader(checker);
        HttpImageStatusCli cli = new HttpImageStatusCli(checker, downloader);
        String input = "test\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Act
        cli.askStatus();
        String output = out.toString().trim();

        // Assert
        assertTrue(output.contains("Please enter a valid number."));
    }
}
