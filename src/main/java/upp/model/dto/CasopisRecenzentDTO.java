package upp.model.dto;

public class CasopisRecenzentDTO {
	
	private Long id;
	private Long casopisId;
	private Long recenzentId;
	
	public CasopisRecenzentDTO() {
		
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
