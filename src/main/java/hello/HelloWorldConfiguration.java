package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldConfiguration {
    // TODO Mettre au niveau du package, va prendre toutes les classes
    // A ce niveau là!
	public static void main(String[] args) {
		SpringApplication.run(HelloWorldConfiguration.class, args);
	}

}
