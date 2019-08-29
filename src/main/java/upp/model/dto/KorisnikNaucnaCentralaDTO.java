package upp.model.dto;

public class KorisnikNaucnaCentralaDTO {
	
	private Long id;
	private Long korisnikId;
	private Long naucnaOblastId;
	
	public KorisnikNaucnaCentralaDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
	}

	public Long getNaucnaOblastId() {
		return naucnaOblastId;
	}

	public void setNaucnaOblastId(Long naucnaOblastId) {
		this.naucnaOblastId = naucnaOblastId;
	}
	
	

}
