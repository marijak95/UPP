package upp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upp.model.KorisnikNaucnaOblast;

@Repository
public interface KorisnikNaucnaOblastRepository extends JpaRepository<KorisnikNaucnaOblast, Long> {

	
}
