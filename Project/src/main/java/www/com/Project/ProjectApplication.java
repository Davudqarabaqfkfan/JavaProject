package www.com.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}//http://localhost:8090/h2-console/login.jsp?jsessionid=658d6e345fcb7073ebe1c035aeccedb0
	//http://localhost:8090/swagger-ui/index.html#/
	//http://localhost:8090/actuator/info

}
