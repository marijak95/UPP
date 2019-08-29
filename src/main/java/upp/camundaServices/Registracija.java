package upp.camundaServices;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.model.converter.ListOfFormFieldsToKorisnikConverter;
import upp.model.dto.FieldIdNamePairDto;
import upp.model.Korisnik;
import upp.enumeracije.Uloga;
import upp.service.KorisnikService;

@Service
public class Registracija implements JavaDelegate {
	
	@Autowired
	private ListOfFormFieldsToKorisnikConverter registerConverter;
	
	@Autowired
	private KorisnikService userService;
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("registerData");
		Korisnik kreiraniKorisnik = registerConverter.convert(dto);
		kreiraniKorisnik.setUloga(Uloga.AUTOR);
		Korisnik saved = null;
		if(kreiraniKorisnik !=null){
			 saved = userService.save(kreiraniKorisnik);
		}
		
	}

}
