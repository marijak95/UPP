package upp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upp.model.Casopis;
import upp.model.Korisnik;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	Korisnik findByEmail(String email);
	
	List<Korisnik> findAllByCasopisId(Long casopisId);
}
