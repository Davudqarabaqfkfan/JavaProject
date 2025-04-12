package www.com.Project.sheduling;

import java.time.LocalTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ShedulingClass {
@Scheduled(fixedRate = 1000)
public void timeTime() {
	LocalTime time = LocalTime.now();
	System.out.println("Time: " + time);
	
	
	
	
	
	
	
	
	
	
	
}
}
