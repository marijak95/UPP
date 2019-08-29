package upp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import upp.model.converter.RadToRadDTOConverter;
import upp.model.dto.FieldIdNamePairDto;
import upp.model.dto.RadDTO;
import upp.model.Casopis;
import upp.model.Korisnik;
import upp.model.NaucnaOblast;
import upp.model.Rad;
import upp.model.RadNaucnaOblast;
import upp.enumeracije.Status;
import upp.service.CasopisService;
import upp.service.KorisnikService;
import upp.service.NaucnaOblastService;
import upp.service.RadNaucnaOblastService;
import upp.service.RadService;

@RestController
@RequestMapping(value = "/rad")
public class RadController {

	@Autowired
	private RadService radService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private FormService formService;
	
	@Autowired 
	private KorisnikService korisnikService;
	
	@Autowired
	private RadToRadDTOConverter converter;

	@Autowired
	private RadNaucnaOblastService radNOService;
	
	@Autowired
	private NaucnaOblastService noService;
	
	@Autowired
	private CasopisService casopisService;
	
	@CrossOrigin
	@RequestMapping(
			value = "/dodajRadForm/{PID}/{idCasopisa}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> dodajRadForm(@PathVariable String PID, @PathVariable String idCasopisa) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("idCasopisa", Long.parseLong(idCasopisa));
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), map);
        
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getCurrent/{PID}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getCurrent(@PathVariable String PID) {
		String id_rada = (String) runtimeService.getVariable(PID, "radId");
		
		
		if(id_rada == null){
			return new ResponseEntity<>(new RadDTO(),HttpStatus.OK);
		}else{
			Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
			System.out.println(task.getName());
			
			Rad rad= radService.findOne(Long.parseLong(id_rada));
			RadDTO ret = converter.convert(rad);
			ret.setIdTask(task.getId());
			return new ResponseEntity<>(ret,HttpStatus.OK);
		}
	}
	@CrossOrigin
	@RequestMapping(
			value = "/dodajRad/{taskId}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> dodajRad(@RequestBody List<FieldIdNamePairDto> dto, @PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
		map.put(pair.getFieldId(), pair.getFieldValue());
		}
		
		runtimeService.setVariable(task.getProcessInstanceId(), "PID", task.getProcessInstanceId());
		System.out.println(task.getName());
		runtimeService.setVariable(task.getProcessInstanceId(), "radDTO", dto);
		formService.submitTaskForm(task.getId(), map);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getAllByGlavniUrednik",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getAll() {
		Korisnik korisnik = korisnikService.getCurrentUser();
		Casopis casopis = korisnik.getCasopis();
		List<Rad> radovi = radService.findAllByCasopisId(casopis.getId());
		List<RadDTO> radoviDTO = converter.convertList(radovi);
		return new ResponseEntity<>(radoviDTO,HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/getAllIspravljeniRadovi",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getAllIspravljeniRadovi() {
		Korisnik korisnik = korisnikService.getCurrentUser();
		List<Rad> radovi = radService.findAllByAutorId(korisnik.getId());
		List<RadDTO> radoviDTO = new ArrayList<RadDTO>();
		for(Rad rad: radovi) {
			if(rad.getStatusRada().equals(Status.KOREKCIJA_FORMAT)) {
				radoviDTO.add(converter.convert(rad));
			}
		}
		return new ResponseEntity<>(radoviDTO,HttpStatus.OK);
	}
	
	
	
	@CrossOrigin
	@RequestMapping(
			value = "/dodajKoautora/{PID}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> dodajKoautora(@RequestBody List<FieldIdNamePairDto> dto, @PathVariable String PID) {
		String dodajJos = "false";
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FieldIdNamePairDto pair:dto){
			map.put(pair.getFieldId(), pair.getFieldValue());
			if(pair.getFieldId().equals("josKoautora")){
				dodajJos = pair.getFieldValue();
			}
		}
		
		runtimeService.setVariable(PID, "koautor", dto);
		
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		Long idRada = (Long) runtimeService.getVariable(PID,"idRada");
		Long idCasopisa = (Long) runtimeService.getVariable(PID,"idCasopisaRada");
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
		String id = "1";
		if(glavniUrednik!=null){
			runtimeService.setVariable(PID, "urednikId", glavniUrednik.getId().toString());
		}
		else {
			runtimeService.setVariable(PID, "urednikId", id.toString());
		}
		//submit forme i prelaz na execute metodu pridruzenog servisa automatski
		formService.submitTaskForm(task.getId(), map);
		return new ResponseEntity<>(dodajJos, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/odbijRad/{PID}/radId/{idRada}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> odbijRad(@PathVariable String PID,@PathVariable Long idRada) {
		String statusRada = "odbijen";
		
		runtimeService.setVariable(PID, "statusRada", statusRada);
		Rad odbijeniRad = radService.findOne(idRada);
		odbijeniRad.setStatusRada(Status.ODBIJEN);
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/tematskiPrihvatljiv/{PID}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> tematskiPrihvatljiv(@PathVariable String PID) {
		String statusRada = "tematskiPrihvatljiv";
		
		runtimeService.setVariable(PID, "statusRada", statusRada);
		
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		//submit forme i prelaz na execute metodu pridruzenog servisa automatski
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/prihvatiRad/{PID}/radId/{idRada}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> prihvatiRad(@PathVariable String PID,@PathVariable Long idRada) {
		String statusPdf = "prihvacen";
		
		runtimeService.setVariable(PID, "statusPdf", statusPdf);
		Rad rad = radService.findOne(idRada);
		rad.setStatusRada(Status.NA_RECENZIRANJU);
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		
		RadNaucnaOblast radNO = radNOService.findByRad(idRada);
		NaucnaOblast naucnaOblastRada = noService.findById(radNO.getNaucnaOblastId());
		Korisnik urednikNO = naucnaOblastRada.getUrednikNaucneOblasti();
		
		Korisnik glavniUrednik = (Korisnik) runtimeService.getVariable(PID, "glavniUrednik");
		Korisnik urednik = null;
		
		if(urednikNO == null){
			urednik = glavniUrednik;
		}else{
			urednik = urednikNO;
		}
		
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(
			value = "/korekcijaFormata/{PID}/radId/{idRada}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> korekcijaFormata(@PathVariable String PID,@PathVariable Long idRada) {
		String statusPdf = "korekcijaFormata";
		
		runtimeService.setVariable(PID, "statusPdf", statusPdf);
		Rad rad = radService.findOne(idRada);
		rad.setStatusRada(Status.KOREKCIJA_FORMAT);
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@CrossOrigin
	@RequestMapping(
			value = "/dodajIspravku/{PID}/idRada/{idRada}",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> dodajIspravku(@RequestBody List<FieldIdNamePairDto> dto, @PathVariable String PID, @PathVariable Long idRada) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		Rad izmenjeniRad = radService.findOne(idRada);
		String putanjaNova = null;
		for(FieldIdNamePairDto pair : dto) {
			if(pair.getFieldId().equals("putanjaNoviPdf")) {
				putanjaNova = pair.getFieldValue();
			}
		}
		if(izmenjeniRad!=null && putanjaNova!=null) {
			izmenjeniRad.setFilePathRadnaVerzija(putanjaNova);
			izmenjeniRad.setFilePathKonacnaVerzija(putanjaNova);
		}
		
		runtimeService.setVariable(PID, "idIspravljenogRada", izmenjeniRad.getId());
		Task task = taskService.createTaskQuery().processInstanceId(PID).list().get(0);
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), map);
        
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@RequestMapping(
			value = "/ispravka/{taskId}/noviPdf",
			method = RequestMethod.POST
	)
	public ResponseEntity<?> ispravka(@PathVariable String taskId, @RequestBody String putanjaNoviPdf) {
		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
	
		Rad rad = radService.findOne(idRada);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		if(rad!=null && putanjaNoviPdf!=null) {
			rad.setFilePathRadnaVerzija(putanjaNoviPdf);
			rad.setFilePathKonacnaVerzija(putanjaNoviPdf);
		}
		
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), map);
        
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}
