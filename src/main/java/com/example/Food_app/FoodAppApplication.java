package com.example.Food_app;

import com.example.Food_app.domain.Admin;
import com.example.Food_app.domain.SecuredUser;
import com.example.Food_app.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodAppApplication implements CommandLineRunner {

	@Autowired
	private AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(FoodAppApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Admin admin = Admin.builder()
				.name("admin1")
				.email("admin1@gmail.com")
				.securedUser(SecuredUser.builder()
						.username("admin1")
						.password("admin1@123")
						.build())
				.build();
		adminService.createAdmin(admin);
	}


}
