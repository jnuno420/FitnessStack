package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Utilizador")
public class Utilizador implements Serializable {
	public Utilizador() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="UTILIZADOR_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="UTILIZADOR_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@Column(name="Email", nullable=true, length=1024)	
	private String email;
	
	@Column(name="Password", nullable=true, length=1024)	
	private String password;
	
	@Column(name="Idade", nullable=false, length=10)	
	private int idade;
	
	@Column(name="Peso", nullable=false)	
	private float peso;
	
	@Column(name="Nome", nullable=true, length=1024)	
	private String nome;
	
	@Column(name="Altura", nullable=false)	
	private float altura;
	
	@Column(name="Genero", nullable=false, length=1)	
	private boolean genero;

	@OneToMany(targetEntity=Marcacao.class)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns({ @JoinColumn(name="UtilizadorID", nullable=false) })	
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)	
	private java.util.Set ORM_agenda = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setIdade(int value) {
		this.idade = value;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setPeso(float value) {
		this.peso = value;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setNome(String value) {
		this.nome = value;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setAltura(float value) {
		this.altura = value;
	}
	
	public float getAltura() {
		return altura;
	}
	
	public void setGenero(boolean value) {
		this.genero = value;
	}
	
	public boolean getGenero() {
		return genero;
	}

	private void setORM_Agenda(java.util.Set value) {
		this.ORM_agenda = value;
	}
	
	private java.util.Set getORM_Agenda() {
		return ORM_agenda;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
