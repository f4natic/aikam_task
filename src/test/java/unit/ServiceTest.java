package unit;

import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.Result;
import com.example.model.StatResult;
import com.example.repository.Repository;
import com.example.service.FileService;
import com.example.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ServiceTest {

    @Mock
    private static FileService fileService;

    @Mock
    private static SessionFactory sessionFactory;

    @Mock
    private static ObjectMapper mapper;

    private static Repository repository;

    private static Service service;

    @BeforeAll
    static void init() {
        repository = mock(Repository.class);
        service = new Service(sessionFactory,fileService,mapper);
        service.setRepository(repository);
    }

    @Test
    public void shouldReturnCatomersBySurname() throws IOException {

        Customer customer = new Customer("TEST_NAME", "TEST_SURNAME");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(repository.getCatomersBySurname(any())).thenReturn(customers);

        List<Customer> customerList = service.getCatomersBySurname("TEST_SURNAME");

        assertEquals(customerList.get(0), customer);
        verify(repository).getCatomersBySurname("TEST_SURNAME");
    }

    @Test
    public void shouldReturnProductPurchasedTimes() throws IOException {

        Customer customer = new Customer("TEST_NAME", "TEST_SURNAME");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        Product product = new Product();
        product.setCustomers(customers);

        when(repository.getProductPurchasedTimes(anyString(), anyInt())).thenReturn(product);

        List<Customer> customerList = service.getProductPurchasedTimes("TEST_NAME", 5);

        assertEquals(customerList.get(0), customer);
        verify(repository).getProductPurchasedTimes("TEST_NAME", 5);
    }

    @Test
    public void shouldReturnCatomersWithinRange() throws IOException {

        Customer customer = new Customer("TEST_NAME", "TEST_SURNAME");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(repository.getCatomersWithinRange(anyDouble(), anyDouble())).thenReturn(customers);

        List<Customer> customerList = service.getCatomersWithinRange(1.0, 2.0);

        assertEquals(customerList.get(0), customer);
        verify(repository).getCatomersWithinRange(1.0, 2.0);
    }

    @Test
    public void shouldReturnBadCustomers() throws IOException {

        Customer customer = new Customer("TEST_NAME", "TEST_SURNAME");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        when(repository.getBadCustomers(anyInt())).thenReturn(customers);

        List<Customer> customerList = service.getBadCustomers(15);

        assertEquals(customerList.get(0), customer);
        verify(repository).getBadCustomers(15);
    }

    @Test
    public void shouldCustomersByStat() throws IOException {

        Customer customer = new Customer("TEST_NAME", "TEST_SURNAME");


        Result result = new Result();
        result.setCustomer(customer);
        result.setProductName("Test product");
        result.setTotal(253.53);

        List<Result> customers = new ArrayList<>();
        customers.add(result);

        when(repository.getCustomersByStat(any(), any())).thenReturn(customers);

        List<StatResult> customerList = service.getCustomersByStat(LocalDate.of(2022, 12, 12), LocalDate.of(2022, 12, 20));

        assertEquals(customerList.get(0).getName(), customer.getSurname() + " " + customer.getName());
        assertEquals(customerList.get(0).getTotalExpenses(), 253.53);
        verify(repository).getCustomersByStat(LocalDate.of(2022, 12, 12), LocalDate.of(2022, 12, 20));
    }
}
