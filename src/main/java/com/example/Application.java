package com.example;

import com.example.model.Result;
import com.example.service.FileService;
import com.example.message.ErrorMessage;
import com.example.message.InputMessage;
import com.example.model.Customer;
import com.example.model.Product;
import com.example.model.Purchase;
import com.example.service.MessageService;
import com.example.service.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;

public class Application {
    private static SessionFactory sessionFactory;
    private static ObjectMapper mapper;
    private static FileService fileService;

    public Application() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void main(String[] args) {
        Application app = new Application();

        try {
            if(args.length == 0 || args.length < 3 || !checkMeth(args[0])) {
                mapper.writeValue(new File("error.json"), new ErrorMessage("Wrong input value..."));
                return;
            }

            fileService = new FileService(args[1], args[2]);

            InputMessage inputMessage = mapper.readValue(fileService.getFileContent(), InputMessage.class);

            sessionFactory = new Configuration()
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Purchase.class)
                    .buildSessionFactory();

            Service service = new Service(sessionFactory, fileService, mapper);

            MessageService messageService = new MessageService(args[0], service, inputMessage, fileService, mapper);
            messageService.run();
            sessionFactory.close();
        }catch (Exception e) {
            try {
                mapper.writeValue(new File("error.json"), new ErrorMessage(e.getMessage()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            sessionFactory.close();
        }
    }

    private static boolean checkMeth(String line) {

        if(line.equals("search") || line.equals("stat")) {
            return true;
        }
        return false;
    }
}
