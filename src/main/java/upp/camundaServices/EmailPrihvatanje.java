package upp.camundaServices;

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
public class EmailPrihvatanje implements JavaDelegate {

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
		Long idCasopisa = (Long) execution.getVariable("idCasopisaRada");
		Casopis casopis = casopisService.findById(idCasopisa);
		Rad rad = radService.findOne(idRada);

		rad.setStatusRada(Status.PRIHVACEN);
		String DOI = "10.2298/" + casopis.getNaziv().substring(0, 3) + ".2019."+ rad.getAutor().getPrezime().substring(0,1);
		rad.setDOI(DOI);
		Korisnik autorRada = rad.getAutor();
	
		emailService.getMail().setTo(autorRada.getEmail());
		
		emailService.getMail().setSubject("Rad prihvacen");
		emailService.getMail().setText("Postovani, Vas rad je prihvacen. Bice objavljen u casopisu: "
				+ casopis.getNaziv()
				+ ".\n \n"
								+ " \n Naslov rada: "+ rad.getNaslov() + ".\n Autor rada"
						+ " je: " + autorRada.getIme() + " " + autorRada.getPrezime() 
			+".\n\n NC Admin");
		emailService.sendNotificaitionSync(autorRada);
		
	}
	
}
