package upp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upp.model.Koautor;
import upp.repository.KoautorRepository;

@Service
@Transactional
public class KoautorService {
	
	@Autowired
	KoautorRepository koautorRepository;
	
	public void save(Koautor k){
		koautorRepository.save(k);
	}
}
