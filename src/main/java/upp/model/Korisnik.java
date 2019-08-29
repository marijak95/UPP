package upp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import upp.enumeracije.Uloga;

@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String ime;
	
	@Column(nullable = false)
	private String prezime;
	
	@Column(nullable = false)
	private String grad;
	
	@Column(nullable = false)
	private String drzava;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String lozinka;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = true) //recenzent, urednik
	private String titula;
	
	@OneToMany( fetch = FetchType.LAZY) //autor
	private List<Rad> radovi;
	
	@Column(nullable = true) //Za urednika
	private Boolean isGlavni;
	
	@ManyToOne( fetch = FetchType.EAGER) //Za urednika
	private Casopis casopis;			//ima casopis ciji je urednik
	
	@Column(nullable = false) //Za urednika
	private Uloga uloga;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<NaucnaOblast> naucneOblastiUrednika;
	
	public Korisnik() {
		this.radovi = new ArrayList<Rad>();
	}
	
	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
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

	public List<Rad> getRadovi() {
		return radovi;
	}

	public void setRadovi(List<Rad> radovi) {
		this.radovi = radovi;
	}

	public Boolean getIsGlavni() {
		return isGlavni;
	}

	public void setIsGlavni(Boolean isGlavni) {
		this.isGlavni = isGlavni;
	}

	public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public List<NaucnaOblast> getNaucneOblastiUrednika() {
		return naucneOblastiUrednika;
	}

	public void setNaucneOblastiUrednika(List<NaucnaOblast> naucneOblastiUrednika) {
		this.naucneOblastiUrednika = naucneOblastiUrednika;
	}
	
	
}
