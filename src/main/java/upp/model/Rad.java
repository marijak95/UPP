package upp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import upp.enumeracije.Status;

@Entity
public class Rad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String naslov;
	
	@Column(nullable = false)
	private String kljucniPojmovi;
	
	@Column(nullable = false)
	private String apstrakt;
	
	@Column(nullable = false)
	private String filePathRadnaVerzija;
	
	@Column(nullable = false)
	private String PID;
	
	@Column(nullable = false)
	private Status statusRada;

	@Column(nullable = true)
	private String filePathKonacnaVerzija;
	
	@ManyToOne( fetch = FetchType.EAGER)
	private Korisnik autor;
	
	@ManyToOne( fetch = FetchType.EAGER)
	private Casopis casopis;
	
	@Column(nullable = true)
	private String DOI;
	
	public Rad() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getKljucniPojmovi() {
		return kljucniPojmovi;
	}

	public void setKljucniPojmovi(String kljucniPojmovi) {
		this.kljucniPojmovi = kljucniPojmovi;
	}

	public String getApstrakt() {
		return apstrakt;
	}

	public void setApstrakt(String apstrakt) {
		this.apstrakt = apstrakt;
	}

	public String getFilePathRadnaVerzija() {
		return filePathRadnaVerzija;
	}

	public void setFilePathRadnaVerzija(String filePathRadnaVerzija) {
		this.filePathRadnaVerzija = filePathRadnaVerzija;
	}

	public Status getStatusRada() {
		return statusRada;
	}

	public void setStatusRada(Status statusRada) {
		this.statusRada = statusRada;
	}

	public String getFilePathKonacnaVerzija() {
		return filePathKonacnaVerzija;
	}

	public void setFilePathKonacnaVerzija(String filePathKonacnaVerzija) {
		this.filePathKonacnaVerzija = filePathKonacnaVerzija;
	}

	public Korisnik getAutor() {
		return autor;
	}

	public void setAutor(Korisnik autor) {
		this.autor = autor;
	}

	public Casopis getCasopis() {
		return casopis;
	}

	public void setCasopis(Casopis casopis) {
		this.casopis = casopis;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public String getDOI() {
		return DOI;
	}

	public void setDOI(String dOI) {
		DOI = dOI;
	}
}
