package upp.model.dto;

public class RadRecenzijaDTO {

	private String naslovRada;
	private String autorRada;
	private String komentarRecenzije;
	private String autorRecenzije;
	
	public RadRecenzijaDTO(){
		
	}

	public String getNaslovRada() {
		return naslovRada;
	}

	public void setNaslovRada(String naslovRada) {
		this.naslovRada = naslovRada;
	}

	public String getAutorRada() {
		return autorRada;
	}

	public void setAutorRada(String autorRada) {
		this.autorRada = autorRada;
	}

	public String getKomentarRecenzije() {
		return komentarRecenzije;
	}

	public void setKomentarRecenzije(String komentarRecenzije) {
		this.komentarRecenzije = komentarRecenzije;
	}

	public String getAutorRecenzije() {
		return autorRecenzije;
	}

	public void setAutorRecenzije(String autorRecenzije) {
		this.autorRecenzije = autorRecenzije;
	}
	
	
	
}
