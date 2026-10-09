package dev.apisentinel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@EnableScheduling
public class ApiSentinelApplication {
 public static void main(String[] args){SpringApplication.run(ApiSentinelApplication.class,args);}
}
