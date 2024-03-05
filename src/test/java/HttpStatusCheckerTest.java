import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class HttpStatusCheckerTest {

    @Test
    void testGetStatusImage_Success() {
        HttpStatusChecker checker = new HttpStatusChecker();
        try {
            String imageUrl = checker.getStatusImage(200);
            assertNotNull(imageUrl);
            assertTrue(imageUrl.startsWith("https://http.cat/"));
        } catch (IOException e) {
            fail("An unexpected IOException occurred: " + e.getMessage());
        }
    }

    @Test
    void testGetStatusImage_Failure() {
        HttpStatusChecker checker = new HttpStatusChecker();
        assertThrows(IOException.class, () -> {
            checker.getStatusImage(10000);
        });
    }
}
