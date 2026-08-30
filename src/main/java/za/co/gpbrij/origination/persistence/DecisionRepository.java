package za.co.gpbrij.origination.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface DecisionRepository extends JpaRepository<DecisionEntity, String> {
    Optional<DecisionEntity> findByApplicationId(String applicationId);
}
