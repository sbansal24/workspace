package com.test.pojo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class MultipleJdbcTemplateExample {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TransactionService transactionService = context.getBean("transactionService", TransactionService.class);
//        transactionService.getTransactions().forEach(System.out::println);

        ((ClassPathXmlApplicationContext) context).registerShutdownHook();

    }
}
