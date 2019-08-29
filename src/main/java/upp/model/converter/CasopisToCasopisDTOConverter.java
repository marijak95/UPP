package upp.model.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import upp.model.dto.CasopisDTO;
import upp.model.Casopis;
import upp.model.Korisnik;

@Component
public class CasopisToCasopisDTOConverter implements Converter<Casopis,CasopisDTO> {

	@Override
	public CasopisDTO convert(Casopis arg0) {
		CasopisDTO retVal = new CasopisDTO();
		retVal.setId(arg0.getId());
		retVal.setIsOpenAccess(arg0.getIsOpenAccess());
		retVal.setIssnBroj(arg0.getIssnBroj());
		retVal.setNaziv(arg0.getNaziv());
		if(arg0.getUrednici()!=null && arg0.getUrednici().size()>0){
			List<Long> idUrednika = new ArrayList<Long>();
			for(Korisnik k : arg0.getUrednici()){
				idUrednika.add(k.getId());
			}
			retVal.setUrednici(idUrednika);
		}
		return retVal;
	}
	
	public List<CasopisDTO> convertList(List<Casopis> casopisi){
		List<CasopisDTO> retVal = new ArrayList<CasopisDTO>();
		for(Casopis c : casopisi){
			CasopisDTO dto = convert(c);
			retVal.add(dto);
		}
		return retVal;
	}

}
