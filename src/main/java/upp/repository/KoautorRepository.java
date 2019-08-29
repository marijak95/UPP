package upp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import upp.model.Koautor;

@Repository
public interface KoautorRepository extends JpaRepository<Koautor, Long> {

}
