package edu.miu.cse.finalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "edu.miu.cse.finalproject")
public class FinalprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalprojectApplication.class, args);
        System.out.println("Hello world");
    }

}
