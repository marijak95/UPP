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
import upp.service.RadService;

@Service
public class EmailNoviRecenzenti implements JavaDelegate{
	@Autowired
	private RadService radService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CasopisService casopisService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
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
		emailService.getMail().setTo(glavniUrednik.getEmail());
		
		emailService.getMail().setSubject("Novi recenzenti");
		emailService.getMail().setText("Postovani, "
				+ "\nBuduci da su odabrani recenzenti prekoracili dozvoljen vremenski period, trebate odabrati nove"
				+ " \nOsnovni podaci rada: \nNaslov rada: "+ rad.getNaslov() + "\nAutor rada"
				+ autorRada.getIme() + " " + autorRada.getPrezime() 
				+ "\n\nS postovanjem, \nNaucna Centrala");
		emailService.sendNotificaitionSync(glavniUrednik);
	}
	

}
