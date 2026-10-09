package dev.apisentinel.repo;
import dev.apisentinel.domain.AlertRule;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AlertRuleRepository extends JpaRepository<AlertRule,Long>{}
