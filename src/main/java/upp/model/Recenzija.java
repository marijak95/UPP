package upp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recenzija {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String komentar;
	
	@Column(nullable = false)
	private Long idAutoraRecenzije;
	
	@Column(nullable = false)
	private Long idRadaRecenzije;
	
	public Recenzija(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Long getIdAutoraRecenzije() {
		return idAutoraRecenzije;
	}

	public void setIdAutoraRecenzije(Long idAutoraRecenzije) {
		this.idAutoraRecenzije = idAutoraRecenzije;
	}

	public Long getIdRadaRecenzije() {
		return idRadaRecenzije;
	}

	public void setIdRadaRecenzije(Long idRadaRecenzije) {
		this.idRadaRecenzije = idRadaRecenzije;
	}
}
