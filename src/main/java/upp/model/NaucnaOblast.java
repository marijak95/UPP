package upp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NaucnaOblast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String naziv;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Korisnik urednikNaucneOblasti;
	
	public NaucnaOblast() {
		
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

	public Korisnik getUrednikNaucneOblasti() {
		return urednikNaucneOblasti;
	}

	public void setUrednikNaucneOblasti(Korisnik urednikNaucneOblasti) {
		this.urednikNaucneOblasti = urednikNaucneOblasti;
	}
}
