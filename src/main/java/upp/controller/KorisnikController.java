package upp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.camunda.bpm.engine.FormService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import upp.model.converter.KorisnikToKorisnikDTOConverter;
import upp.model.converter.RadToRadDTOConverter;
import upp.model.dto.FormFieldsDto;
import upp.model.dto.KorisnikDTO;
import upp.model.dto.RadRecenzijaDTO;
import upp.model.dto.RecenzijaDTO;
import upp.model.dto.RecenziranjeDTO;
import upp.model.dto.TaskDTO;
import upp.model.CasopisRecenzent;
import upp.model.Korisnik;
import upp.model.Rad;
import upp.model.Recenzija;
import upp.enumeracije.Status;
import upp.service.CasopisRecenzentService;
import upp.service.CasopisService;
import upp.service.KorisnikService;
import upp.service.RadService;
import upp.service.RecenzijaService;

@RestController
@RequestMapping(value = "/korisnik")
public class KorisnikController {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	KorisnikToKorisnikDTOConverter converter;
	
	@Autowired
	RadToRadDTOConverter radCOnverter;
	
	@Autowired
	RuntimeService runtimeService;
	
	@Autowired
	CasopisService casopisService;
	
	@Autowired
	FormService formService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	RadService radService;
	
	@Autowired
	RecenzijaService recenzijaService;
	
	@Autowired
	CasopisRecenzentService casopisRecenzentService;
	
	@RequestMapping(
            value = "/getLoggedIn",
            method = RequestMethod.GET)
    public ResponseEntity<?> getLoggedIn() {
		Korisnik loggedIn = korisnikService.getCurrentUser();
		if(loggedIn == null) {
			return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
		}
		KorisnikDTO retVal = converter.convert(loggedIn);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/logout",
            method = RequestMethod.POST)
    public ResponseEntity<?> signout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/getRecenzenti/{taskId}",
            method = RequestMethod.GET)
    public ResponseEntity<?> getRecenzenti(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		Long idCasopisa = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idCasopisaRada");
		List<CasopisRecenzent> recenzentiCasopisa = casopisRecenzentService.findByCasopisId(idCasopisa);
		List<Korisnik> recenzenti = new ArrayList<Korisnik>();
		for(CasopisRecenzent cr : recenzentiCasopisa){
			Korisnik recenzent = korisnikService.findById(cr.getRecenzentId());
			recenzenti.add(recenzent);
		}
		List<KorisnikDTO> korisnikDto = converter.convertList(recenzenti);
        return new ResponseEntity<>(korisnikDto, HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/submitRecenzenti//{taskId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> submitRecenzenti(@PathVariable String taskId, @RequestBody RecenziranjeDTO recenziranjeDTO) {

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		List<Long> recenzentiId = recenziranjeDTO.getIdRecenzenata();
		List<String> listaRecenzentId = new ArrayList<String>();
		for(Long id : recenzentiId){
			listaRecenzentId.add(id.toString());
		}
	
		runtimeService.setVariable(task.getProcessInstanceId(), "recenzentiId", listaRecenzentId);
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
		Rad rad = radService.findOne(idRada);
		rad.setStatusRada(Status.NA_RECENZIRANJU);
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/getTaskovi",
            method = RequestMethod.GET)
    public ResponseEntity<?> getTaskovi() {
		Korisnik ulogovani = korisnikService.getCurrentUser();
		List<Task> tasks = taskService.createTaskQuery()
				.active()
				.taskAssignee(ulogovani.getId().toString())
				.list();

			List<TaskDTO> result = new ArrayList<>();
			for(Task task : tasks) {
				TaskDTO dto = new TaskDTO();
				dto.setId(task.getId());
				dto.setName(task.getName());
				dto.setProcessInstanceId(task.getProcessInstanceId());
				result.add(dto);
			}
		
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(
			value = "/getFormFields/{taskId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getFormFields(@PathVariable String taskId) {
		
		//provera koji je task u pitanju
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
        Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
        Rad rad = radService.findOne(idRada);
		return new ResponseEntity<>(new FormFieldsDto(task.getId(), task.getProcessInstanceId(), properties,rad.getNaslov(),rad.getAutor().getIme() + " " + rad.getAutor().getPrezime()),HttpStatus.OK);
	}
	
	@RequestMapping(
            value = "/submitRecenziranje/{taskId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> submitRecenziju(@PathVariable String taskId, @RequestBody RecenzijaDTO recenziranjeDTO) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Korisnik ulogovani = korisnikService.getCurrentUser();
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
		Recenzija recenzija = new Recenzija();
		recenzija.setKomentar(recenziranjeDTO.getKomentar());
		recenzija.setIdAutoraRecenzije(ulogovani.getId());
		recenzija.setIdRadaRecenzije(idRada);
		recenzijaService.save(recenzija);
		System.out.println(task.getName());
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@CrossOrigin
	@RequestMapping(
			value = "/getRadDTO/{taskId}",
			method = RequestMethod.GET
	)
	public ResponseEntity<?> getRadDTO(@PathVariable String taskId) {
		
		//provera koji je task u pitanju
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		List<RadRecenzijaDTO> radRecenzijaDtoList = new ArrayList<RadRecenzijaDTO>();
        Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
        Rad rad = radService.findOne(idRada);
        
		List<Recenzija> recenzijeRada = recenzijaService.findAllByRadId(idRada);
        for(Recenzija r : recenzijeRada){
        	RadRecenzijaDTO dto = new RadRecenzijaDTO();
        	dto.setAutorRada(rad.getAutor().getIme() + " " + rad.getAutor().getPrezime());
        	dto.setNaslovRada(rad.getNaslov());
        	dto.setKomentarRecenzije(r.getKomentar());
        	Korisnik autorRecenzije = korisnikService.findById(r.getIdAutoraRecenzije());
        	dto.setAutorRecenzije(autorRecenzije.getIme() + " " + autorRecenzije.getPrezime());
        	radRecenzijaDtoList.add(dto);
        }
		return new ResponseEntity<>(radRecenzijaDtoList, HttpStatus.OK);
	}
	
	@RequestMapping(
            value = "/prihvatiRadKonacno/{taskId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> prihvatiRadKonacno(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
		String konacnaOdluka = "prihvacen";
		Rad rad = radService.findOne(idRada);
		rad.setStatusRada(Status.PRIHVACEN);
		runtimeService.setVariable(task.getProcessInstanceId(), "konacnaOdluka", konacnaOdluka);
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/odbijRadKonacno/{taskId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> odbijRadKonacno(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
		String konacnaOdluka = "odbijen";
		Rad rad = radService.findOne(idRada);
		rad.setStatusRada(Status.ODBIJEN);
		runtimeService.setVariable(task.getProcessInstanceId(), "konacnaOdluka", konacnaOdluka);
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/vecaKorekcija/{taskId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> vecaKorekcija(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
		String konacnaOdluka = "uslovnoPrihvacen";
		String izmena = "veca";
		Rad rad = radService.findOne(idRada);
		runtimeService.setVariable(task.getProcessInstanceId(), "konacnaOdluka", konacnaOdluka);
		runtimeService.setVariable(task.getProcessInstanceId(), "izmena", izmena);
		
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/manjaKorekcija/{taskId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> manjaKorekcija(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
		String konacnaOdluka = "uslovnoPrihvacen";
		String izmena = "manja";
		Rad rad = radService.findOne(idRada);
		runtimeService.setVariable(task.getProcessInstanceId(), "konacnaOdluka", konacnaOdluka);
		runtimeService.setVariable(task.getProcessInstanceId(), "izmena", izmena);
		
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@RequestMapping(
            value = "/noviRecenzenti/{taskId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> ponovnoBiranjeRecenzenata(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		
		Long idRada = (Long) runtimeService.getVariable(task.getProcessInstanceId(), "idRada");
		String konacnaOdluka = "noviRecenzenti";
		Rad rad = radService.findOne(idRada);
		runtimeService.setVariable(task.getProcessInstanceId(), "konacnaOdluka", konacnaOdluka);
		
		formService.submitTaskForm(task.getId(), new HashMap<String,Object>());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
