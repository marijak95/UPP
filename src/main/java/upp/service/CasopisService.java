package upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upp.model.Casopis;
import upp.model.RadNaucnaOblast;
import upp.repository.CasopisRepository;

@Service
@Transactional
public class CasopisService {

	@Autowired
	CasopisRepository casopisRepository;
	
	public List<Casopis> findAll(){
		return casopisRepository.findAll();
	}
	
	public void save(Casopis casopis){
		casopisRepository.save(casopis);
	}
	
	public Casopis findById(Long id){
		return casopisRepository.findById(id).get();
	}
}
