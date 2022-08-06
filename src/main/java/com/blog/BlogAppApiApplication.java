package com.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.configs.AppConstants;
import com.blog.entities.Roles;
import com.blog.repositories.RoleRepo;



@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Roles role1 = new Roles();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setRoleName("ROLE_NORMAL");
			
			Roles role2 = new Roles();
			role2.setId(AppConstants.ADMIN_USER);
			role2.setRoleName("ROLE_ADMIN");
			
			List<Roles> roles = List.of(role1,role2);
			List<Roles> result = this.roleRepo.saveAll(roles);
//			result.forEach(r->{
//				System.out.println(r.getRoleName());
//			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
