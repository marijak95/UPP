package upp.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import upp.model.dto.FieldIdNamePairDto;
import upp.model.Korisnik;

@Component
public class ListOfFormFieldsToKorisnikConverter implements Converter<List<FieldIdNamePairDto>, Korisnik>{

	@Override
	public Korisnik convert(List<FieldIdNamePairDto> source) {
		// TODO Auto-generated method stub
		if(source == null){
			return null;
		}
		Korisnik user = new Korisnik();
		
		for(FieldIdNamePairDto field : source){
			if(field.getFieldId().equals("ime")){
				user.setIme(field.getFieldValue());
			}
			if(field.getFieldId().equals("prezime")){
				user.setPrezime(field.getFieldValue());
			}
			if(field.getFieldId().equals("email")){
				user.setEmail(field.getFieldValue());
			}
			if(field.getFieldId().equals("grad")){
				user.setGrad(field.getFieldValue());
			}
			if(field.getFieldId().equals("drzava")){
				user.setDrzava(field.getFieldValue());
			}
			if(field.getFieldId().equals("lozinka")){
				user.setLozinka(field.getFieldValue());
			}
			if(field.getFieldId().equals("korisnickoIme")){
				user.setUsername(field.getFieldValue());
			}
		
		}
		
		return user;
	}
	
	public List<Korisnik> convertList(List<List<FieldIdNamePairDto>> source) {
		List<Korisnik> ret = new ArrayList<Korisnik>();
		for(List<FieldIdNamePairDto> field : source) {
			ret.add(convert(field));
		}
		return ret;
	}
	

}
