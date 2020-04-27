package ada.spd.startup;

import ada.spd.startup.Domains.Question;
import ada.spd.startup.Repositories.QuestionRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import java.io.File;
import java.io.IOException;

@SpringBootApplication

public class StartupApplication {


	public static void main(String[] args) throws IOException {



		SpringApplication.run(StartupApplication.class, args);
	}



}
