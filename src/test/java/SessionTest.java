import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.Purchase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    public void checkConnection() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Purchase.class)
                .buildSessionFactory();
    }

}
