package upp.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import upp.model.dto.RadDTO;
import upp.model.Rad;

@Component
public class RadToRadDTOConverter implements Converter<Rad, RadDTO>{

	@Override
	public RadDTO convert(Rad arg0) {
		RadDTO retVal = new RadDTO();
		retVal.setApstrakt(arg0.getApstrakt());
		retVal.setAutor(arg0.getAutor().getId());
		retVal.setFilePathKonacnaVerzija(arg0.getFilePathKonacnaVerzija());
		retVal.setFilePathRadnaVerzija(arg0.getFilePathRadnaVerzija());
		retVal.setId(arg0.getId());
		retVal.setNaslov(arg0.getNaslov());
		retVal.setKljucniPojmovi(arg0.getKljucniPojmovi());
		retVal.setStatusRada(arg0.getStatusRada());
		retVal.setPID(arg0.getPID());
		return retVal;
	}
	
	public List<RadDTO> convertList(List<Rad> radovi){
		List<RadDTO> radoviDTO = new ArrayList<RadDTO>();
		for(Rad rad : radovi) {
			radoviDTO.add(convert(rad));
		}
		return radoviDTO;
	}

}
