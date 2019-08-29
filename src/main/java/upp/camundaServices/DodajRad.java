package upp.camundaServices;

import java.util.List;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import upp.model.converter.ListOfFormFieldsToRadConverter;
import upp.model.dto.FieldIdNamePairDto;
import upp.model.Casopis;
import upp.model.NaucnaOblast;
import upp.model.Rad;
import upp.model.RadNaucnaOblast;
import upp.service.NaucnaOblastService;
import upp.service.RadNaucnaOblastService;
import upp.service.RadService;

@Service
public class DodajRad implements JavaDelegate {

	@Autowired
	private ListOfFormFieldsToRadConverter converter;
	
	@Autowired
	private RadService radService;
	
	@Autowired
	private RadNaucnaOblastService radNOService;
	
	@Autowired
	private NaucnaOblastService naucnaOblastService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {

		NaucnaOblast naucnaOblast = null;
		List<FieldIdNamePairDto> dto = (List<FieldIdNamePairDto>) execution.getVariable("radDTO");
		Rad dodatiRad = converter.convert(dto);
		for(FieldIdNamePairDto field : dto){
			if(field.getFieldId().equals("naucnaOblast")){
				naucnaOblast = naucnaOblastService.findById(Long.parseLong(field.getFieldValue()));
			}
			if(field.getFieldId().equals("idCasopisa")){
				execution.setVariable("idCasopisaRada", Long.parseLong(field.getFieldValue()));
			}
		}
		String PID = (String) execution.getVariable("PID");
		dodatiRad.setPID(PID);
		Rad saved = radService.save(dodatiRad);
		if(naucnaOblast!=null){
			RadNaucnaOblast radNO = new RadNaucnaOblast();
			radNO.setNaucnaOblastId(naucnaOblast.getId());
			radNO.setRadId(saved.getId());
			radNOService.save(radNO);
		}
		execution.setVariable("idRada", saved.getId());
		execution.setVariable("idAutoraRada", saved.getAutor().getId().toString());
	}
}
