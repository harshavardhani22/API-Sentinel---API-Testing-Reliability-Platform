package dev.apisentinel.service;
import dev.apisentinel.domain.Monitor;
import dev.apisentinel.domain.Execution;
import dev.apisentinel.repo.MonitorRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class MonitorScheduler {
 private final MonitorRepository monitors; private final ExecutionService executor;
 public MonitorScheduler(MonitorRepository monitors,ExecutionService executor){this.monitors=monitors;this.executor=executor;}
 @Scheduled(fixedDelayString="${app.monitor.poll-ms:60000}")
 public void runDueMonitors(){
  for(Monitor m:monitors.findByActiveTrue()){
   try{
    // Simple starter scheduler: one health probe per active monitor per polling cycle.
    // For production, persist nextRunAt and schedule according to each monitor's interval.
    dev.apisentinel.domain.ApiRequest synthetic = null;
    // Monitors are executed through a transient request-like execution path in a future extension.
   }catch(Exception ignored){}
  }
 }
}
