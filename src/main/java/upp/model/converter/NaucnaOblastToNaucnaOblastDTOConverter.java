package upp.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import upp.model.dto.NaucnaOblastDTO;
import upp.model.NaucnaOblast;

@Component
public class NaucnaOblastToNaucnaOblastDTOConverter implements Converter<NaucnaOblast,NaucnaOblastDTO> {

	@Override
	public NaucnaOblastDTO convert(NaucnaOblast arg0) {
		NaucnaOblastDTO retVal = new NaucnaOblastDTO();
		retVal.setId(arg0.getId());
		retVal.setNaziv(arg0.getNaziv());
		return retVal;
	}
	
	public List<NaucnaOblastDTO> convertList(List<NaucnaOblast> lista){
		List<NaucnaOblastDTO> listaDTO = new ArrayList<NaucnaOblastDTO>();
		for(NaucnaOblast no : lista){
			listaDTO.add(convert(no));
		}
		return listaDTO;
	}
}
