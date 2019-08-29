package upp.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import upp.model.dto.KorisnikDTO;
import upp.model.Korisnik;
import upp.model.Rad;

@Component
public class KorisnikToKorisnikDTOConverter implements Converter<Korisnik, KorisnikDTO> {

	@Override
	public KorisnikDTO convert(Korisnik toConvert) {
		KorisnikDTO retVal = new KorisnikDTO();
		retVal.setId(toConvert.getId());
		retVal.setDrzava(toConvert.getDrzava());
		retVal.setGrad(toConvert.getGrad());
		retVal.setIme(toConvert.getIme());
		retVal.setPrezime(toConvert.getPrezime());
		retVal.setUsername(toConvert.getUsername());
		retVal.setTitula(toConvert.getTitula());
		retVal.setEmail(toConvert.getEmail());
		if(toConvert.getCasopis()!=null){
			retVal.setIdCasopisa(toConvert.getCasopis().getId());
		}
		if(toConvert.getRadovi()!=null && toConvert.getRadovi().size()>0){
			List<Long> idRadoviList = new ArrayList<Long>();
			for(Rad r : toConvert.getRadovi()){
				idRadoviList.add(r.getId());
			}
			retVal.setIdRadova(idRadoviList);
		}
		retVal.setIsGlavni(toConvert.getIsGlavni());
		retVal.setUloga(toConvert.getUloga());
		retVal.setLozinka(toConvert.getLozinka());
		return retVal;
	}
	
	public List<KorisnikDTO> convertList(List<Korisnik> korisnici){
		List<KorisnikDTO> listaDTO = new ArrayList<KorisnikDTO>();
		for(Korisnik k : korisnici){
			listaDTO.add(convert(k));
		}
		return listaDTO;
	}

}
