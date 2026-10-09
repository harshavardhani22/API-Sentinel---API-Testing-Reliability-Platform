package dev.apisentinel.service;
import dev.apisentinel.domain.*;
import dev.apisentinel.repo.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import java.net.URI;
import java.time.Duration;
import java.util.*;
@Service
public class ExecutionService {
 private final ApiRequestRepository requests; private final ExecutionRepository executions; private final ObjectMapper mapper;
 public ExecutionService(ApiRequestRepository requests,ExecutionRepository executions,ObjectMapper mapper){this.requests=requests;this.executions=executions;this.mapper=mapper;}
 @Transactional
 public Execution run(Long id){
  ApiRequest req=requests.findById(id).orElseThrow(()->new IllegalArgumentException("Request not found"));
  long start=System.nanoTime(); Integer code=null; String snippet=""; String error=null; boolean passed=false; String result="FAILED";
  try{
   URI uri=buildUri(req.getUrl(),req.getQueryJson());
   HttpHeaders headers=new HttpHeaders();
   Map<String,String> hs=mapper.readValue(req.getHeadersJson(),new TypeReference<Map<String,String>>(){});
   hs.forEach(headers::set);
   HttpMethod method=HttpMethod.valueOf(req.getMethod().toUpperCase(Locale.ROOT));
   RestClient client=RestClient.builder().requestFactory(factory()).build();
   RestClient.RequestBodySpec spec=client.method(method).uri(uri).headers(h->h.addAll(headers));
   if(!req.getBody().isBlank()&&method!=HttpMethod.GET&&method!=HttpMethod.HEAD) spec.body(req.getBody());
   ResponseEntity<String> response=spec.retrieve().toEntity(String.class);
   code=response.getStatusCode().value(); snippet=truncate(response.getBody(),4000);
   long latency=(System.nanoTime()-start)/1_000_000;
   passed=code==req.getExpectedStatus()&&latency<=req.getMaxResponseTimeMs();
   result=passed?"PASSED":"ASSERTION_FAILED";
   return executions.save(new Execution(req.getId(),req.getName(),req.getMethod(),req.getUrl(),code,latency,passed,result,snippet,null));
  }catch(Exception ex){error=truncate(ex.getMessage(),1000);long latency=(System.nanoTime()-start)/1_000_000;
   return executions.save(new Execution(req.getId(),req.getName(),req.getMethod(),req.getUrl(),code,latency,false,result,snippet,error));}
 }
 private org.springframework.http.client.ClientHttpRequestFactory factory(){
  var f=new org.springframework.http.client.SimpleClientHttpRequestFactory();f.setConnectTimeout(3000);f.setReadTimeout(5000);return f;
 }
 private URI buildUri(String raw,String queryJson)throws Exception{
  if(!raw.startsWith("https://")&&!raw.startsWith("http://"))throw new IllegalArgumentException("Only HTTP(S) URLs are supported");
  StringBuilder s=new StringBuilder(raw);Map<String,String> qs=mapper.readValue(queryJson,new TypeReference<Map<String,String>>(){});
  if(!qs.isEmpty()){s.append(raw.contains("?")?"&":"?");boolean first=true;for(var e:qs.entrySet()){if(!first)s.append("&");first=false;s.append(java.net.URLEncoder.encode(e.getKey(),java.nio.charset.StandardCharsets.UTF_8)).append("=").append(java.net.URLEncoder.encode(e.getValue(),java.nio.charset.StandardCharsets.UTF_8));}}
  return URI.create(s.toString());
 }
 private String truncate(String s,int n){if(s==null)return "";return s.length()>n?s.substring(0,n):s;}
}
