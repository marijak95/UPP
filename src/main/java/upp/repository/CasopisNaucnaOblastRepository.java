package upp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upp.model.CasopisNaucnaOblast;

@Repository
public interface CasopisNaucnaOblastRepository extends JpaRepository<CasopisNaucnaOblast, Long> {

	
	
}
