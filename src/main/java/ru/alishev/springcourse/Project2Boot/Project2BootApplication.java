package ru.alishev.springcourse.Project2Boot;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.alishev.springcourse.Project2Boot.shpilevsky.core.impl.spring.installer.InstallerSpring;

@SpringBootApplication
@EnableTransactionManagement
public class Project2BootApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(Project2BootApplication.class, args);
	}
}
