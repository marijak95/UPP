package upp.camundaServices;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.model.Casopis;
import upp.model.Korisnik;
import upp.model.Rad;
import upp.enumeracije.Status;
import upp.service.CasopisService;
import upp.service.EmailService;
import upp.service.RadService;

@Service
public class EmailOdbijanje implements JavaDelegate {

	@Autowired
	private RadService radService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	CasopisService casopisService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Long idRada = (Long) execution.getVariable("idRada");
		Rad rad = radService.findOne(idRada);
		rad.setStatusRada(Status.ODBIJEN);
		Korisnik autorRada = rad.getAutor();
	
		emailService.getMail().setTo(autorRada.getEmail());
		
		emailService.getMail().setSubject("Rad odbijen");
		emailService.getMail().setText("Postovani, Vas rad je odbijen."
				+ "\n .\n"
								+ " \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
						+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
			+".\n\n NC Admin");
		emailService.sendNotificaitionSync(autorRada);
		
	}
	
}
