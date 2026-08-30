package za.co.gpbrij.origination.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface StatusHistoryRepository extends JpaRepository<StatusHistoryEntity,String>{List<StatusHistoryEntity> findByApplicationIdOrderByRecordedAtAsc(String id);}