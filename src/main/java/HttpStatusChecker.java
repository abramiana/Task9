import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Клас HttpStatusChecker відповідає за перевірку статусів HTTP-відповідей та отримання URL зображень,
 * пов'язаних із цими статусами.
 */
public class HttpStatusChecker {
    private final OkHttpClient client;
    private static final Logger logger = LogManager.getLogger(HttpStatusChecker.class);

    public HttpStatusChecker() {
        this.client = new OkHttpClient();
    }

    /**
     * Метод для отримання URL зображення для вказаного коду статусу HTTP-відповіді.
     *
     * @param code код статусу HTTP-відповіді
     * @return URL зображення для вказаного коду статусу
     * @throws IOException якщо виникла помилка під час виконання запиту або отримання відповіді
     */
    public String getStatusImage(int code) throws IOException {
        String url = "https://http.cat/" + code + ".jpg";
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("No image for status code " + code);
            }
            logger.info("Image for status " + code + " successfully found");
            return url;
        } catch (IOException e) {
            logger.error("An error occurred while receiving the image ", e);
            throw e;
        }
    }
}