package upp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import upp.model.converter.NaucnaOblastToNaucnaOblastDTOConverter;
import upp.model.dto.CasopisDTO;
import upp.model.dto.NaucnaOblastDTO;
import upp.model.Casopis;
import upp.model.NaucnaOblast;
import upp.service.NaucnaOblastService;

@RestController
@RequestMapping(value = "/naucnaOblast")
public class NaucnaOblastController {

	@Autowired
	NaucnaOblastService naucnaOblastService;
	
	@Autowired
	NaucnaOblastToNaucnaOblastDTOConverter converter;
	
	@RequestMapping(
            value = "/getAll",
            method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
		List<NaucnaOblast> sveNO = naucnaOblastService.findAll();
		if(sveNO == null) {
			return new ResponseEntity<>(HttpStatus.MOVED_PERMANENTLY);
		}
		List<NaucnaOblastDTO> retVal = converter.convertList(sveNO);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
