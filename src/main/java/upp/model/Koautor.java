package upp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Koautor {

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
	private Long idAutora; // koji ga je dodao
	
	@Column(nullable = false)
	private Long idRada; //za koji ga je dodao autor
	
	public Koautor(){
		
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

	public Long getIdAutora() {
		return idAutora;
	}

	public void setIdAutora(Long idAutora) {
		this.idAutora = idAutora;
	}

	public Long getIdRada() {
		return idRada;
	}

	public void setIdRada(Long idRada) {
		this.idRada = idRada;
	}
}
