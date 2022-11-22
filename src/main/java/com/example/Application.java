package com.example;

import com.example.file.FileService;
import com.example.message.ErrorMessage;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.Purchase;
import com.example.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;

public class Application {
    private static ObjectMapper mapper;

    public Application() {
        mapper = new ObjectMapper();
    }

    public void initApp() {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Purchase.class)
                .buildSessionFactory();

        Service service = new Service(sessionFactory);
        sessionFactory.close();
    }

    public static void main(String[] args) {
        Application app = new Application();

        try {
            if(args.length == 0 || args.length < 3) {
                mapper.writeValue(new File("error.json"), new ErrorMessage("Wrong input value..."));
                return;
            }else if(!args[0].equals("search") || !args[0].equals("stat")) {
                mapper.writeValue(new File("error.json"), new ErrorMessage("Wrong param request (only search or stat..."));
                return;
            }
            FileService fileService = new FileService(args[1], args[2]);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
