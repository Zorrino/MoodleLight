package fr.uca.springbootstrap;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import fr.uca.springbootstrap.payload.response.MessageResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SpringBootApplication
public class SpringBootSecurityPostgresqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityPostgresqlApplication.class, args);
		System.out.println("running on http://localhost:8080");
	}

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<MessageResponse> handleException(InvalidFormatException e) {
		return ResponseEntity.status(500).body(new MessageResponse("The server encountered a problem."));
	}
}
