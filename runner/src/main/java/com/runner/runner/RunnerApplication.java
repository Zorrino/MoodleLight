package com.runner.runner;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.runner.runner.response.MessageResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class RunnerApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(RunnerApplication.class, args);
            System.out.println("Runner is running successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<MessageResponse> handleException(InvalidFormatException e) {
        return ResponseEntity.status(500).body(new MessageResponse("The server encountered a problem."));
    }
}
