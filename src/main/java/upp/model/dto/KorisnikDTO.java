package upp.model.dto;

import java.util.List;

import upp.enumeracije.Uloga;



public class KorisnikDTO {

	private Long id;
	private String ime;
	private String prezime;
	private String grad;
	private String drzava;
	private String email;
	private String username;
	private String titula;
	private List<Long> idRadova;
	private Boolean isGlavni;
	private Long idCasopisa;
	private Uloga uloga;
	private String lozinka;
	
	public KorisnikDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	/*public List<Rad> getRadovi() {
		return radovi;
	}

	public void setRadovi(List<Rad> radovi) {
		this.radovi = radovi;
	}*/

	public Boolean getIsGlavni() {
		return isGlavni;
	}

	public void setIsGlavni(Boolean isGlavni) {
		this.isGlavni = isGlavni;
	}

	/*public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}*/

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}

	public List<Long> getIdRadova() {
		return idRadova;
	}

	public void setIdRadova(List<Long> idRadova) {
		this.idRadova = idRadova;
	}

	public Long getIdCasopisa() {
		return idCasopisa;
	}

	public void setIdCasopisa(Long idCasopisa) {
		this.idCasopisa = idCasopisa;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

}
