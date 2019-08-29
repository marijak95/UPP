package upp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upp.model.Rad;
import upp.repository.KorisnikNaucnaOblastRepository;
import upp.repository.RadRepository;

@Service
@Transactional
public class KorisnikNaucnaOblastService {

	@Autowired
	KorisnikNaucnaOblastRepository korisnikNaucnaOblastRepository;
	
}
