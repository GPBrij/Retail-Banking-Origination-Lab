package za.co.gpbrij.origination.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.gpbrij.origination.workflow.ReferralStatus;
import java.util.*;
public interface ReferralRepository extends JpaRepository<ReferralEntity,String>{Optional<ReferralEntity> findByApplicationId(String id);List<ReferralEntity> findByStatus(ReferralStatus status);}