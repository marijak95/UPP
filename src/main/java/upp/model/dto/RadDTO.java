package upp.model.dto;

import upp.enumeracije.Status;

public class RadDTO {
	
	private Long id;
	private String naslov;
	private String kljucniPojmovi;
	private String apstrakt;
	private String filePathRadnaVerzija;
	private String filePathKonacnaVerzija;
	private String koautori;
	private Long autor;
	private String idTask;
	private Status statusRada;
	private String PID;
	
	
	public RadDTO() {
		
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

	public String getFilePathKonacnaVerzija() {
		return filePathKonacnaVerzija;
	}

	public void setFilePathKonacnaVerzija(String filePathKonacnaVerzija) {
		this.filePathKonacnaVerzija = filePathKonacnaVerzija;
	}

	public String getKoautori() {
		return koautori;
	}

	public void setKoautori(String koautori) {
		this.koautori = koautori;
	}

	public Long getAutor() {
		return autor;
	}

	public void setAutor(Long autor) {
		this.autor = autor;
	}

	public String getIdTask() {
		return idTask;
	}

	public void setIdTask(String idTask) {
		this.idTask = idTask;
	}

	public Status getStatusRada() {
		return statusRada;
	}

	public void setStatusRada(Status statusRada) {
		this.statusRada = statusRada;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

}
