import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class HttpStatusCheckerTest {


    @Test
    void testGetStatusImage_Success() throws IOException {
        // Arrange
        HttpStatusChecker checker = new HttpStatusChecker();

        // Act
        String imageUrl = checker.getStatusImage(200);

        // Assert
        assertNotNull(imageUrl);
        assertTrue(imageUrl.startsWith("https://http.cat/"));
    }

    @Test
    void testGetStatusImage_Failure() {
        HttpStatusChecker checker = new HttpStatusChecker();
        assertThrows(IOException.class, () -> {
            checker.getStatusImage(10000);
        });
    }
}
