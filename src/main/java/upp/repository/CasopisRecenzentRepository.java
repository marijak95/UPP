package upp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upp.model.CasopisRecenzent;

@Repository
public interface CasopisRecenzentRepository extends JpaRepository<CasopisRecenzent, Long> {

	List<CasopisRecenzent> findAllByCasopisId(Long idCasopisa);
	
}
