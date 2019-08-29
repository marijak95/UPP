package upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upp.model.Rad;
import upp.model.RadNaucnaOblast;
import upp.repository.RadNaucnaOblastRepository;
import upp.repository.RadRepository;

@Service
@Transactional
public class RadNaucnaOblastService {

	@Autowired
	RadNaucnaOblastRepository naucnaOblastRepository;
	
	public void save(RadNaucnaOblast radNO){
		naucnaOblastRepository.save(radNO);
	}

	public RadNaucnaOblast findByRad(Long idRada){
		return naucnaOblastRepository.findByRadId(idRada);
	}
	
}
