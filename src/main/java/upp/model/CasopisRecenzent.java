package upp.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CasopisRecenzent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long casopisId;
	
	@Column(nullable = false)
	private Long recenzentId;
	
	public CasopisRecenzent() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCasopisId() {
		return casopisId;
	}

	public void setCasopisId(Long casopisId) {
		this.casopisId = casopisId;
	}

	public Long getRecenzentId() {
		return recenzentId;
	}

	public void setRecenzentId(Long recenzentId) {
		this.recenzentId = recenzentId;
	}
	
	
	
}
