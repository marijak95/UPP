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
			
			emailService.getMail().setSubject("Prijava novog rada u casopis");
			emailService.getMail().setText("Postovani, \n\n Novi rad je prijavljen u okviru"
					+ "Vaseg casopisa. \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
							+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
				+".\n\n NC Admin");
			emailService.sendNotificaitionSync(glavniUrednik);
			
			emailService.getMail().setTo(autorRada.getEmail());
			
			emailService.getMail().setSubject("Prijava novog rada u casopis");
			emailService.getMail().setText("Postovani, \n\n Vas rad je prijavljen u okviru"
					+ " casopisa: \"" + casopisRada.getNaziv() + "\", ciji je glavni urednik "
							+ glavniUrednik.getIme() + " " + glavniUrednik.getPrezime() + ".\n"
									+ " \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
							+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
				+".\n\n NC Admin");
			emailService.sendNotificaitionSync(autorRada);
			
		}
	}

}
