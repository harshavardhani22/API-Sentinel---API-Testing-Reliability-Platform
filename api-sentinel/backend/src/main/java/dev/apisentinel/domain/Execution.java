package dev.apisentinel.domain;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
@Entity @Table(name="executions")
public class Execution {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long requestId; private String requestName; private String method;
 @Column(length=2048) private String url; private Integer statusCode; private Long latencyMs;
 private Boolean passed; private String result;
 @Column(columnDefinition="text") private String responseSnippet;
 @Column(columnDefinition="text") private String errorMessage;
 @Column(nullable=false) private OffsetDateTime executedAt=OffsetDateTime.now();
 @Column(nullable=false,unique=true) private String shareToken=java.util.UUID.randomUUID().toString().replace("-","");
 protected Execution(){}
 public Execution(Long requestId,String requestName,String method,String url,Integer statusCode,Long latencyMs,Boolean passed,String result,String responseSnippet,String errorMessage){
  this.requestId=requestId;this.requestName=requestName;this.method=method;this.url=url;this.statusCode=statusCode;this.latencyMs=latencyMs;this.passed=passed;this.result=result;this.responseSnippet=responseSnippet;this.errorMessage=errorMessage;
 }
 public Long getId(){return id;} public Long getRequestId(){return requestId;} public String getRequestName(){return requestName;} public String getMethod(){return method;}
 public String getUrl(){return url;} public Integer getStatusCode(){return statusCode;} public Long getLatencyMs(){return latencyMs;} public Boolean getPassed(){return passed;}
 public String getResult(){return result;} public String getResponseSnippet(){return responseSnippet;} public String getErrorMessage(){return errorMessage;}
 public OffsetDateTime getExecutedAt(){return executedAt;} public String getShareToken(){return shareToken;}
}
