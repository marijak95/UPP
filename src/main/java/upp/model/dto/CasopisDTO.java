package upp.model.dto;

import java.util.List;

import upp.model.Korisnik;

public class CasopisDTO {
	
	private Long id;
	private String naziv;
	private String issnBroj;
	private Boolean isOpenAccess;
	private List<Long> urednici;
	
	public CasopisDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getIssnBroj() {
		return issnBroj;
	}

	public void setIssnBroj(String issnBroj) {
		this.issnBroj = issnBroj;
	}

	public Boolean getIsOpenAccess() {
		return isOpenAccess;
	}

	public void setIsOpenAccess(Boolean isOpenAccess) {
		this.isOpenAccess = isOpenAccess;
	}

	public List<Long> getUrednici() {
		return urednici;
	}

	public void setUrednici(List<Long> urednici) {
		this.urednici = urednici;
	}
	
	

}
