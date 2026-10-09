package dev.apisentinel.domain;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
@Entity @Table(name="alert_rules")
public class AlertRule {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name; @Column(nullable=false) private String metric;
 @Column(nullable=false) private String operator; private Double thresholdValue;
 @Column(nullable=false) private String destination; private Boolean enabled=true;
 @Column(nullable=false) private OffsetDateTime createdAt=OffsetDateTime.now();
 protected AlertRule(){}
 public AlertRule(String name,String metric,String operator,Double thresholdValue,String destination){this.name=name;this.metric=metric;this.operator=operator;this.thresholdValue=thresholdValue;this.destination=destination;this.enabled=true;}
 public Long getId(){return id;}public String getName(){return name;}public String getMetric(){return metric;}public String getOperator(){return operator;}public Double getThresholdValue(){return thresholdValue;}public String getDestination(){return destination;}public Boolean getEnabled(){return enabled;}public OffsetDateTime getCreatedAt(){return createdAt;}
}
