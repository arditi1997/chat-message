package com.chat.message;

import com.chat.message.model.Role;
import com.chat.message.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MessageApplication{

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}
}
