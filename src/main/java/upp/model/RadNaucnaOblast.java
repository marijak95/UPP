package upp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RadNaucnaOblast {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long naucnaOblastId;
	
	@Column(nullable = false)
	private Long radId;
	
	public RadNaucnaOblast() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNaucnaOblastId() {
		return naucnaOblastId;
	}

	public void setNaucnaOblastId(Long naucnaOblastId) {
		this.naucnaOblastId = naucnaOblastId;
	}

	public Long getRadId() {
		return radId;
	}

	public void setRadId(Long radId) {
		this.radId = radId;
	}
	
	
	
}
