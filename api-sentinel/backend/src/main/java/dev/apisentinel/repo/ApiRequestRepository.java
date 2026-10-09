package dev.apisentinel.repo;
import dev.apisentinel.domain.ApiRequest;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ApiRequestRepository extends JpaRepository<ApiRequest,Long>{}
