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
public class EmailAutoruIUredniku implements JavaDelegate {

	@Autowired
	private RadService radService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private CasopisService casopisService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long idRada = (Long) execution.getVariable("idRada");
		Long idCasopisa = (Long) execution.getVariable("idCasopisaRada");
		Rad rad = radService.findOne(idRada);
		Casopis casopisRada = casopisService.findById(idCasopisa);
		Korisnik autorRada = rad.getAutor();
		Korisnik glavniUrednik = null;
		List<Korisnik> uredniciCasopisa = korisnikService.findByCasopisId(casopisRada.getId());
		for(Korisnik k : uredniciCasopisa){
			if(k.getIsGlavni()){
				glavniUrednik = k;
				break;
			}
		}
		if(glavniUrednik!=null && autorRada!= null){
			emailService.getMail().setTo(glavniUrednik.getEmail());
			
			emailService.getMail().setSubject("Novi Rad");
			emailService.getMail().setText("Postovani, "
					+ "\nPodnet je zahtev za prijavu rada u vasem casopisu."
					+ "\nOsnovni podaci rada: \nNaslov rada: "+ rad.getNaslov() + "\nAutor rada:"
						 + autorRada.getIme() + " " + autorRada.getPrezime() 
						 + "\n\nS postovanjem, \nNaucna Centrala");
			
			emailService.sendNotificaitionSync(glavniUrednik);
			
			emailService.getMail().setTo(autorRada.getEmail());
			
			emailService.getMail().setSubject("Novi rad");
			emailService.getMail().setText("Postovani, "
					+"\nOvim putem potvrdjujemo da je Vas rad prijavljen u casopis: "
					 + casopisRada.getNaziv() 
									+ "\nOsnovni podaci rada: \nNaslov rada: "+ rad.getNaslov() + 
									"\nAutor rada:"
									+ autorRada.getIme() + " " + autorRada.getPrezime() 
									+ "\n\nS postovanjem, \nNaucna Centrala");
			emailService.sendNotificaitionSync(autorRada);
			
		}
	}

}
