package upp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Casopis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String naziv;
	
	@Column(nullable = true)
	private String issnBroj;
	
	@Column(nullable = false)
	private Boolean isOpenAccess;
	
	@OneToMany( fetch = FetchType.LAZY)
	private List<Korisnik> urednici;
	
	@OneToMany( fetch = FetchType.EAGER)
	private List<Rad> radovi;
	
	
	public Casopis() {
		this.urednici = new ArrayList<Korisnik>();
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

	public List<Korisnik> getUrednici() {
		return urednici;
	}

	public void setUrednici(List<Korisnik> urednici) {
		this.urednici = urednici;
	}

	public List<Rad> getRadovi() {
		return radovi;
	}

	public void setRadovi(List<Rad> radovi) {
		this.radovi = radovi;
	}
}
