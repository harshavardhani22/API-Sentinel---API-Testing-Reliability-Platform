package dev.apisentinel.domain;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
@Entity @Table(name="monitors")
public class Monitor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(nullable=false,length=2048) private String url;
 @Column(nullable=false) private String method="GET";
 private Integer intervalSeconds=300; private Integer expectedStatus=200; private Integer timeoutMs=5000;
 private Boolean active=true;
 @Column(nullable=false) private OffsetDateTime createdAt=OffsetDateTime.now();
 protected Monitor(){}
 public Monitor(String name,String url,String method,Integer intervalSeconds,Integer expectedStatus,Integer timeoutMs){this.name=name;this.url=url;this.method=method==null?"GET":method;this.intervalSeconds=intervalSeconds==null?300:intervalSeconds;this.expectedStatus=expectedStatus==null?200:expectedStatus;this.timeoutMs=timeoutMs==null?5000:timeoutMs;this.active=true;}
 public Long getId(){return id;}public String getName(){return name;}public String getUrl(){return url;}public String getMethod(){return method;}
 public Integer getIntervalSeconds(){return intervalSeconds;}public Integer getExpectedStatus(){return expectedStatus;}public Integer getTimeoutMs(){return timeoutMs;}public Boolean getActive(){return active;}public OffsetDateTime getCreatedAt(){return createdAt;}
}
