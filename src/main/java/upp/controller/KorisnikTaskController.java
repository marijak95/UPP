package upp.controller;

import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import upp.model.converter.KorisnikToKorisnikDTOConverter;
import upp.model.converter.ListOfFormFieldsToKorisnikConverter;
import upp.model.dto.FieldIdNamePairDto;
import upp.model.dto.FormFieldsDto;
import upp.model.dto.KorisnikDTO;
import upp.model.dto.TaskDTO;
import upp.model.Korisnik;
import upp.service.KorisnikService;


@Controller
@RequestMapping("/user")
public class KorisnikTaskController {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired 
	private KorisnikService userService;
	
	@Autowired
	private ListOfFormFieldsToKorisnikConverter registerConverter;
	
	@Autowired
	private KorisnikToKorisnikDTOConverter converter;
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/startProcess/{idProcess}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> startProcess(@PathVariable String idProcess) {
		
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(idProcess);
		

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		
		return new ResponseEntity<>(new FormFieldsDto(task.getId(), pi.getId(), properties),HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/loginUser",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> loginKorisnik(@RequestBody List<FieldIdNamePairDto> dto) {
		
		Korisnik user = null;
		for(FieldIdNamePairDto field : dto){
			if(field.getFieldId().equals("email")){
				user = userService.findByEmail(field.getFieldValue());
			}
				
		}
		
		if(user !=null){
			KorisnikDTO dtoUser = converter.convert(user);
			for(FieldIdNamePairDto field : dto){
				if(field.getFieldId().equals("lozinka")){
					if(user.getLozinka().equals(field.getFieldValue())){
						userService.setCurrentUser(user);
						return new ResponseEntity<>(dtoUser,HttpStatus.OK);
					}else{
						return new ResponseEntity<>(null,HttpStatus.OK);
					}
				}
					
			}
			
		}else{
			return new ResponseEntity<>(false,HttpStatus.OK);
		}
		return new ResponseEntity<>(false,HttpStatus.OK);
	
        
    }
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/formCheck/{processInstanceId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> formCheck(@PathVariable String processInstanceId) {
		
		//provera koji je task u pitanju
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).list().get(0);
		System.out.println(task.getName());
		
		if(task.getName().equals("")){
			return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
		}
        
			return new ResponseEntity<>(new TaskDTO(task.getName(),processInstanceId,task.getId()),HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/getFormFields/{PInsId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getFormFields(@PathVariable String PInsId) {
		
		//provera koji je task u pitanju
		Task task = taskService.createTaskQuery().processInstanceId(PInsId).list().get(0);
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
        
		return new ResponseEntity<>(new FormFieldsDto(task.getId(), PInsId, properties),HttpStatus.OK);
	}
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/registerUser/{taskId}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> registerKorisnik(@RequestBody List<FieldIdNamePairDto> dto,@PathVariable String taskId) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
		map.put(pair.getFieldId(), pair.getFieldValue());
		}
		runtimeService.setVariable(task.getProcessInstanceId(), "registerData", dto);
	/*	
		Korisnik createdKorisnik = registerConverter.convert(dto);
		Korisnik saved = userService.save(createdKorisnik);
		*/
		
			formService.submitTaskForm(taskId, map);
			return new ResponseEntity<>(HttpStatus.OK);
			
	}
	
	
	
}
