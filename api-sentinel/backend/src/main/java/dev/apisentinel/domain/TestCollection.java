package dev.apisentinel.domain;
import jakarta.persistence.*;
import java.time.OffsetDateTime;
@Entity @Table(name="test_collections")
public class TestCollection {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String name;
 @Column(columnDefinition="text") private String description;
 @Column(columnDefinition="text",nullable=false) private String requestIdsJson="[]";
 @Column(nullable=false) private OffsetDateTime createdAt=OffsetDateTime.now();
 protected TestCollection(){}
 public TestCollection(String name,String description,String requestIdsJson){this.name=name;this.description=description;this.requestIdsJson=requestIdsJson==null?"[]":requestIdsJson;}
 public Long getId(){return id;} public String getName(){return name;} public String getDescription(){return description;} public String getRequestIdsJson(){return requestIdsJson;} public OffsetDateTime getCreatedAt(){return createdAt;}
}
