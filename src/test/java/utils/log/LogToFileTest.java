package utils.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;



class LogToFileTest {
    Path file;

    @BeforeEach
    void createPath (@TempDir Path tmpDir) throws IOException {
        file = tmpDir.resolve("Service.tmp");
        Files.createFile(file);
    }

    @Test
    void testLoadFromServiceFileExpectedValue() throws IOException {
        Files.writeString(file, "LOG_LEVEL = DEBUG");

        String expected = "DEBUG";
        String actual = LogToFile.getInstance().loadFromServiceFile(LogProperty.LOG_LEVEL, file).name();

        assertEquals(expected, actual);
    }


}