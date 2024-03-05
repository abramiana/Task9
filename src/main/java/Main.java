import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();


        // Приклади викликів методу getStatusImage
        try {
            System.out.println(checker.getStatusImage(226)); // Повинно вивести посилання на картинку для коду 404
            // Спроба отримати зображення для неіснуючого коду 10000
            System.out.println(checker.getStatusImage(10000));

        } catch (IOException e) {
            logger.error("An error occurred while getting the image", e);
        }

        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader(checker);
        // Приклад виклику методу downloadStatusImage
        try {
            downloader.downloadStatusImage(200);
        } catch (IOException e) {
            logger.error("Error while downloading image", e);
        }
    }
}