package upp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KorisnikNaucnaOblast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long korisnikId;
	
	@Column(nullable = false)
	private Long naucnaOblastId;
	
	public KorisnikNaucnaOblast() {
		
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
