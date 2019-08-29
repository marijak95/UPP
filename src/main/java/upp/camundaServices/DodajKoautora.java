package upp.camundaServices;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.model.converter.ListOfFormFieldsToKorisnikConverter;
import upp.model.dto.FieldIdNamePairDto;
import upp.model.Koautor;
import upp.model.Korisnik;
import upp.service.KoautorService;
import upp.service.KorisnikService;

@Service
public class DodajKoautora implements JavaDelegate  {

	@Autowired
	private KoautorService koautorService;
	
	@Autowired
	private ListOfFormFieldsToKorisnikConverter converter;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("koautor");
		/*for(FieldIdNamePairDto pair: dto){
			if(pair.getFieldId().equals("josKoautora")){
				execution.setVariable("josKoautora", pair.getFieldValue());
				break;
			}
		}*/
		Korisnik user = converter.convert(dto);
		Koautor noviKoautor = new Koautor();
		noviKoautor.setDrzava(user.getDrzava());
		noviKoautor.setGrad(user.getGrad());
		noviKoautor.setIme(user.getIme());
		noviKoautor.setPrezime(user.getPrezime());
		noviKoautor.setEmail(user.getEmail());
		Long idRada = (Long) execution.getVariable("idRada");
		noviKoautor.setIdRada(idRada);
		Korisnik ulogovani = korisnikService.getCurrentUser();
		noviKoautor.setIdAutora(ulogovani.getId());
		koautorService.save(noviKoautor);
	}

}
