package dev.apisentinel.domain;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
@Entity @Table(name="api_requests")
public class ApiRequest {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false) private String method;
 @Column(nullable=false,length=2048) private String url;
 @Column(columnDefinition="text") private String headersJson="{}";
 @Column(columnDefinition="text") private String queryJson="{}";
 @Column(columnDefinition="text") private String body="";
 private Integer expectedStatus=200; private Integer maxResponseTimeMs=2500;
 @Column(nullable=false) private OffsetDateTime createdAt=OffsetDateTime.now();
 protected ApiRequest(){}
 public ApiRequest(String name,String method,String url,String headersJson,String queryJson,String body,Integer expectedStatus,Integer maxResponseTimeMs){
  this.name=name;this.method=method;this.url=url;this.headersJson=headersJson==null?"{}":headersJson;this.queryJson=queryJson==null?"{}":queryJson;this.body=body==null?"":body;this.expectedStatus=expectedStatus==null?200:expectedStatus;this.maxResponseTimeMs=maxResponseTimeMs==null?2500:maxResponseTimeMs;
 }
 public void update(String name,String method,String url,String headersJson,String queryJson,String body,Integer expectedStatus,Integer maxResponseTimeMs){
  this.name=name;this.method=method;this.url=url;this.headersJson=headersJson==null?"{}":headersJson;this.queryJson=queryJson==null?"{}":queryJson;this.body=body==null?"":body;this.expectedStatus=expectedStatus==null?200:expectedStatus;this.maxResponseTimeMs=maxResponseTimeMs==null?2500:maxResponseTimeMs;
 }
 public Long getId(){return id;} public String getName(){return name;} public String getMethod(){return method;} public String getUrl(){return url;}
 public String getHeadersJson(){return headersJson;} public String getQueryJson(){return queryJson;} public String getBody(){return body;}
 public Integer getExpectedStatus(){return expectedStatus;} public Integer getMaxResponseTimeMs(){return maxResponseTimeMs;} public OffsetDateTime getCreatedAt(){return createdAt;}
}
