package de.borowski.reminder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReminderApplication {

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(ReminderApplication.class, args)));
	}

}
