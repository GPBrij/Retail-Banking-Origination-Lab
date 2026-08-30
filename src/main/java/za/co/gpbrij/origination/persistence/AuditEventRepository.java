package za.co.gpbrij.origination.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AuditEventRepository extends JpaRepository<AuditEventEntity,String>{List<AuditEventEntity> findByApplicationIdOrderByOccurredAtAsc(String id);}