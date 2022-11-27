package unit;

import com.example.repository.Repository;
import com.example.service.FileService;
import com.example.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;

public class ServiceTest {

    @Mock
    private FileService fileService;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private ObjectMapper mapper;

    @Mock
    private Repository repository;

    private Service service;

    @BeforeAll
    public void init() {
        service = new Service(sessionFactory,fileService,mapper);
        service.setRepository(repository);
    }
}
