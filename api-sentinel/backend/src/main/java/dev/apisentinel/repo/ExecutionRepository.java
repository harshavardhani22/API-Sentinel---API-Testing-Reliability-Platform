package dev.apisentinel.repo;
import dev.apisentinel.domain.Execution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface ExecutionRepository extends JpaRepository<Execution,Long>{
 List<Execution> findTop100ByOrderByExecutedAtDesc();
 Optional<Execution> findByShareToken(String shareToken);
 List<Execution> findByRequestIdOrderByExecutedAtDesc(Long requestId);
}
