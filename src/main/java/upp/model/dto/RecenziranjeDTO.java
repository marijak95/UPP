package upp.model.dto;

import java.util.List;

public class RecenziranjeDTO {

	public List<Long> idRecenzenata;
	public String vremeRecenziranja;
	
	public RecenziranjeDTO(){
		
	}

	public List<Long> getIdRecenzenata() {
		return idRecenzenata;
	}

	public void setIdRecenzenata(List<Long> idRecenzenata) {
		this.idRecenzenata = idRecenzenata;
	}

	public String getVremeRecenziranja() {
		return vremeRecenziranja;
	}

	public void setVremeRecenziranja(String vremeRecenziranja) {
		this.vremeRecenziranja = vremeRecenziranja;
	}
	
}
