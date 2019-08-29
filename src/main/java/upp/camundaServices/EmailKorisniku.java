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
public class EmailKorisniku implements JavaDelegate {

	@Autowired
	RadService radService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CasopisService casopisService;
	
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
		
		execution.setVariable("glavniUrednik",glavniUrednik);
		emailService.getMail().setTo(autorRada.getEmail());
		
		emailService.getMail().setSubject("Korekcija pdf");
		emailService.getMail().setText("Postovani, \n\n Vas rad je vracen na korekciju formata."
				+ "\n Casopisa: \"" + casopisRada.getNaziv() + "\", ciji je glavni urednik "
						+ glavniUrednik.getIme() + " " + glavniUrednik.getPrezime() + ".\n"
								+ " \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
						+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
			+".\n\n NC Admin");
		emailService.sendNotificaitionSync(autorRada);
		
	}

}
