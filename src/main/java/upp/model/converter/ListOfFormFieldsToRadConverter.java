package upp.model.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import upp.model.dto.FieldIdNamePairDto;
import upp.model.Casopis;
import upp.model.Korisnik;
import upp.model.Rad;
import upp.enumeracije.Status;
import upp.service.CasopisService;
import upp.service.KorisnikService;

@Component
public class ListOfFormFieldsToRadConverter implements Converter<List<FieldIdNamePairDto>, Rad> {

	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	CasopisService casopisService;

	
	@Override
	public Rad convert(List<FieldIdNamePairDto> arg0) {
		if(arg0 == null){
			return null;
		}
		Rad rad = new Rad();
		for(FieldIdNamePairDto field : arg0){
			if(field.getFieldId().equals("naslov")){
				rad.setNaslov(field.getFieldValue());
			}
			if(field.getFieldId().equals("apstrakt")){
				rad.setApstrakt(field.getFieldValue());
			}
			if(field.getFieldId().equals("putanjaDoPDF")){
				rad.setFilePathRadnaVerzija(field.getFieldValue());
				rad.setFilePathKonacnaVerzija(field.getFieldValue());
			}
			if(field.getFieldId().equals("kljucniPojmovi")){
				rad.setKljucniPojmovi(field.getFieldValue());
			}
			if(field.getFieldId().equals("idCasopisa")){
				Casopis casopisRada = casopisService.findById(Long.parseLong(field.getFieldValue()));
				rad.setCasopis(casopisRada);
			}
		}
		
		rad.setStatusRada(Status.PODNET);
		Korisnik ulogovani = korisnikService.getCurrentUser();
		if(ulogovani != null){
			rad.setAutor(ulogovani);
		}
		
		return rad;
	}
}
