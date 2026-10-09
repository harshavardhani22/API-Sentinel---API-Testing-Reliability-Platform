package dev.apisentinel.repo;
import dev.apisentinel.domain.TestCollection;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TestCollectionRepository extends JpaRepository<TestCollection,Long>{}
