package upp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import upp.model.converter.CasopisToCasopisDTOConverter;
import upp.model.dto.CasopisDTO;
import upp.model.Casopis;
import upp.service.CasopisService;

@RestController
@RequestMapping(value = "/casopis")
public class CasopisController {

	@Autowired
	CasopisService casopisService;
	
	@Autowired
	CasopisToCasopisDTOConverter converter;
	
	@RequestMapping(
            value = "/getAll",
            method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
		List<Casopis> sviCasopisi = casopisService.findAll();
		if(sviCasopisi == null) {
			return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
		}
		List<CasopisDTO> retVal = converter.convertList(sviCasopisi);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
	
}
