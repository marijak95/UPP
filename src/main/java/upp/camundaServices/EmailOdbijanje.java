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
		
		emailService.getMail().setSubject("Odbijen rad");
		emailService.getMail().setText("Postovani, "
				+ "\nOvom prilikom Vas obavestavamo da je Vas rad odbijen."
				+ " \nOsnovni podaci rada: \nNaslov rada: "+ rad.getNaslov() + "\nAutor rada"
				+ autorRada.getIme() + " " + autorRada.getPrezime() 
				+ "\n\nS postovanjem, \nNaucna Centrala");
		emailService.sendNotificaitionSync(autorRada);
		
	}
	
}
