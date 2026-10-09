package dev.apisentinel.api;
import dev.apisentinel.domain.*;
import dev.apisentinel.repo.*;
import dev.apisentinel.service.ExecutionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api")
public class ApiController {
 private final ApiRequestRepository requests;private final TestCollectionRepository collections;private final ExecutionRepository executions;
 private final MonitorRepository monitors;private final AlertRuleRepository rules;private final ExecutionService executor;
 public ApiController(ApiRequestRepository r,TestCollectionRepository c,ExecutionRepository e,MonitorRepository m,AlertRuleRepository a,ExecutionService x){requests=r;collections=c;executions=e;monitors=m;rules=a;executor=x;}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","ok","service","api-sentinel");}
 @GetMapping("/docs") public Map<String,Object> docs(){return Map.of("name","API Sentinel API","version","1.0.0","auth","HTTP Basic except health/docs/share reports","endpoints",List.of("GET/POST /api/requests","POST /api/requests/{id}/run","GET/POST /api/collections","POST /api/collections/{id}/run","GET /api/executions","GET/POST /api/monitors","GET/POST /api/alerts/rules"));}
 public record RequestPayload(@NotBlank String name,@NotBlank String method,@NotBlank String url,String headersJson,String queryJson,String body,Integer expectedStatus,Integer maxResponseTimeMs){}
 @GetMapping("/requests") public List<ApiRequest> requests(){return requests.findAll();}
 @PostMapping("/requests") @ResponseStatus(HttpStatus.CREATED) public ApiRequest create(@Valid @RequestBody RequestPayload p){return requests.save(new ApiRequest(p.name(),p.method(),p.url(),p.headersJson(),p.queryJson(),p.body(),p.expectedStatus(),p.maxResponseTimeMs()));}
 @GetMapping("/requests/{id}") public ApiRequest get(@PathVariable Long id){return requests.findById(id).orElseThrow(()->new IllegalArgumentException("Request not found"));}
 @PutMapping("/requests/{id}") public ApiRequest update(@PathVariable Long id,@Valid @RequestBody RequestPayload p){ApiRequest r=get(id);r.update(p.name(),p.method(),p.url(),p.headersJson(),p.queryJson(),p.body(),p.expectedStatus(),p.maxResponseTimeMs());return requests.save(r);}
 @DeleteMapping("/requests/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){requests.delete(get(id));}
 @PostMapping("/requests/{id}/run") @ResponseStatus(HttpStatus.CREATED) public Execution run(@PathVariable Long id){return executor.run(id);}
 public record CollectionPayload(@NotBlank String name,String description,String requestIdsJson){}
 @GetMapping("/collections") public List<TestCollection> collections(){return collections.findAll();}
 @PostMapping("/collections") @ResponseStatus(HttpStatus.CREATED) public TestCollection createCollection(@Valid @RequestBody CollectionPayload p){return collections.save(new TestCollection(p.name(),p.description(),p.requestIdsJson()));}
 @PostMapping("/collections/{id}/run") public List<Execution> runCollection(@PathVariable Long id){
  TestCollection c=collections.findById(id).orElseThrow(()->new IllegalArgumentException("Collection not found"));
  try{List<Integer> ids=new com.fasterxml.jackson.databind.ObjectMapper().readValue(c.getRequestIdsJson(),new com.fasterxml.jackson.core.type.TypeReference<List<Integer>>(){});List<Execution> out=new ArrayList<>();for(Integer requestId:ids)out.add(executor.run(requestId.longValue()));return out;}
  catch(Exception ex){throw new IllegalArgumentException("Invalid collection request IDs");}
 }
 @GetMapping("/executions") public List<Execution> executions(){return executions.findTop100ByOrderByExecutedAtDesc();}
 @GetMapping("/requests/{id}/executions") public List<Execution> requestExecutions(@PathVariable Long id){return executions.findByRequestIdOrderByExecutedAtDesc(id);}
 public record MonitorPayload(@NotBlank String name,@NotBlank String url,String method,Integer intervalSeconds,Integer expectedStatus,Integer timeoutMs){}
 @GetMapping("/monitors") public List<Monitor> monitors(){return monitors.findAll();}
 @PostMapping("/monitors") @ResponseStatus(HttpStatus.CREATED) public Monitor createMonitor(@Valid @RequestBody MonitorPayload p){return monitors.save(new Monitor(p.name(),p.url(),p.method(),p.intervalSeconds(),p.expectedStatus(),p.timeoutMs()));}
 public record AlertPayload(@NotBlank String name,@NotBlank String metric,@NotBlank String operator,@NotNull Double thresholdValue,@NotBlank String destination){}
 @GetMapping("/alerts/rules") public List<AlertRule> rules(){return rules.findAll();}
 @PostMapping("/alerts/rules") @ResponseStatus(HttpStatus.CREATED) public AlertRule createRule(@Valid @RequestBody AlertPayload p){return rules.save(new AlertRule(p.name(),p.metric(),p.operator(),p.thresholdValue(),p.destination()));}
 @GetMapping("/reports/{token}") public Execution report(@PathVariable String token){return executions.findByShareToken(token).orElseThrow(()->new IllegalArgumentException("Report not found"));}
}
