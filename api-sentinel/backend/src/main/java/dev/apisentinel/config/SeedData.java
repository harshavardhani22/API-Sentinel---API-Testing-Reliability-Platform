package dev.apisentinel.config;
import dev.apisentinel.domain.*;
import dev.apisentinel.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration public class SeedData{
 @Bean CommandLineRunner seed(ApiRequestRepository requests,TestCollectionRepository collections,MonitorRepository monitors,AlertRuleRepository rules){
  return args->{
   if(requests.count()==0){
    ApiRequest health=new ApiRequest("JSONPlaceholder · Get post","GET","https://jsonplaceholder.typicode.com/posts/1","{}","{}","",200,2500);
    ApiRequest users=new ApiRequest("JSONPlaceholder · List users","GET","https://jsonplaceholder.typicode.com/users","{}","{}","",200,2500);
    requests.save(health);requests.save(users);
   }
   if(collections.count()==0)collections.save(new TestCollection("Smoke test suite","Basic API contract checks","[1,2]"));
   if(monitors.count()==0)monitors.save(new Monitor("JSONPlaceholder API","https://jsonplaceholder.typicode.com/posts/1","GET",300,200,5000));
   if(rules.count()==0)rules.save(new AlertRule("Latency budget","latency_ms",">",2500.0,"email:dev@example.com"));
  };
 }
}
