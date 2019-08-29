package upp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upp.model.Rad;

@Repository
public interface RadRepository extends JpaRepository<Rad, Long> {

	List<Rad> findAll();
	List<Rad> findByNaslov(String naslov);
	List<Rad> findByKljucniPojmovi(String kljucniPojmovi);
	List<Rad> findAllByCasopisId(Long casopisId);
	List<Rad> findAllByAutorId(Long autorId);
	
}
