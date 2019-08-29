package upp.camundaServices;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.model.Casopis;
import upp.model.Korisnik;
import upp.model.Rad;
import upp.service.CasopisService;
import upp.service.EmailService;
import upp.service.KorisnikService;
import upp.service.NaucnaOblastService;
import upp.service.RadNaucnaOblastService;
import upp.service.RadService;

@Service
public class IzborUrednika implements JavaDelegate{

	@Autowired
	RadService radService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	RadNaucnaOblastService radNOService;
	
	@Autowired
	NaucnaOblastService noService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CasopisService casopisService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long radId = (Long) execution.getVariable("idRada");
		
		Long idCasopisa = (Long) execution.getVariable("idCasopisaRada");
		Casopis casopisRada = casopisService.findById(idCasopisa);
		Rad rad = radService.findOne(radId);
		Korisnik autorRada = rad.getAutor();
		Korisnik glavniUrednik = null;
		List<Korisnik> uredniciCasopisa = korisnikService.findByCasopisId(casopisRada.getId());
		for(Korisnik k : uredniciCasopisa){
			if(k.getIsGlavni()){
				glavniUrednik = k;
				break;
			}
		}
		if(glavniUrednik!=null){
			emailService.getMail().setTo(glavniUrednik.getEmail());
			
			emailService.getMail().setSubject("Notifikacija o novom radu");
			emailService.getMail().setText("Postovani, \n\n Novi rad je prijavljen."
					+ " Izvrsite izbor recenzenata rada."
					+ "\n \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
							+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
				+".\n\n NC Admin");
			emailService.sendNotificaitionSync(glavniUrednik);
		}
	}

}
