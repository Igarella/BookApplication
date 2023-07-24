package ru.alishev.springcourse.Project2Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Project2BootApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(Project2BootApplication.class, args);
	}

	/*@Bean
	public InstallerSpring installerSpring()
	{
		return new InstallerSpring();
	}*/

}
