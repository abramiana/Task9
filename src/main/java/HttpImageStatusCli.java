import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Клас HttpImageStatusCli представляє інтерфейс командного рядка для взаємодії з програмою для завантаження зображень HTTP статусів.
 */
public class HttpImageStatusCli {
    private final HttpStatusChecker checker;
    private final HttpStatusImageDownloader downloader;
    private static final Logger logger = LogManager.getLogger(HttpImageStatusCli.class);

    /**
     * Конструктор класу HttpImageStatusCli.
     *
     * @param checker    об'єкт HttpStatusChecker для перевірки наявності зображення для HTTP статусу
     * @param downloader об'єкт HttpStatusImageDownloader для завантаження зображення за HTTP статусом
     */
    public HttpImageStatusCli(HttpStatusChecker checker, HttpStatusImageDownloader downloader) {
        this.checker = checker;
        this.downloader = downloader;
    }

    /**
     * Метод для взаємодії з користувачем через командний рядок.
     */
    public void askStatus() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            logger.info("Enter HTTP status code:");
            System.out.println("Enter HTTP status code:");
            String input = reader.readLine();

            try {
                int statusCode = Integer.parseInt(input);
                String imageUrl = checker.getStatusImage(statusCode);
                if (imageUrl != null) {
                    logger.info("Downloading image for status code " + statusCode + "...");
                    System.out.println("Downloading image for status code " + statusCode + "...");
                    downloader.downloadStatusImage(statusCode);
                    logger.info("Image downloaded successfully.");
                    System.out.println("Image downloaded successfully.");
                } else {
                    logger.info("There is no image for HTTP status " + statusCode);
                    System.out.println("There is no image for HTTP status " + statusCode);
                }
            } catch (NumberFormatException e) {
                logger.error("Please enter a valid number.", e);
                System.out.println("Please enter a valid number.");
            } catch (IOException e) {
                logger.error("Error occurred while downloading image: " + e.getMessage(), e);
                System.out.println("Error occurred while downloading image: " + e.getMessage());
            }
        } catch (IOException e) {
            logger.error("Error occurred while reading input: " + e.getMessage(), e);
            System.out.println("Error occurred while reading input: " + e.getMessage());
        }
    }
}