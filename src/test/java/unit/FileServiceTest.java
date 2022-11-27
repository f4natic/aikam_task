package unit;

import com.example.service.FileService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class FileServiceTest {

    private FileService fileService;
    private String input;
    private String output;

    @BeforeEach
    public void init() {
        input = "test_input.json";
        output = "test_output.json";
        fileService = mock(FileService.class);
    }

    @Test
    public void shouldRead() throws IOException {
        when(fileService.getFileContent()).thenReturn("test content");

        String content = fileService.getFileContent();
        Assertions.assertEquals(content, "test content");

        fileService.writeFile(content);
        verify(fileService).writeFile(content);

    }
}
