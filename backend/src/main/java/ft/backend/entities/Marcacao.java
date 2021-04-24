package ft.backend.entities;

import java.io.Serializable;
import javax.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Marcacao")
public class Marcacao implements Serializable {
	public Marcacao() {
	}
	
	@Column(name="ID", nullable=false, length=10)	
	@Id	
	@GeneratedValue(generator="MARCACAO_ID_GENERATOR")	
	@org.hibernate.annotations.GenericGenerator(name="MARCACAO_ID_GENERATOR", strategy="native")	
	private int ID;
	
	@ManyToOne(targetEntity=Treino.class, fetch=FetchType.LAZY)	
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.LOCK})	
	@JoinColumns(value={ @JoinColumn(name="TreinoID", referencedColumnName="ID", nullable=false) }, foreignKey=@ForeignKey(name="FKMarcacao343781"))	
	private Treino treino;
	
	@Column(name="Data_hora", nullable=true)	
	@Temporal(TemporalType.DATE)	
	private java.util.Date data_hora;
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setData_hora(java.util.Date value) {
		this.data_hora = value;
	}
	
	public java.util.Date getData_hora() {
		return data_hora;
	}
	
	public void setTreino(Treino value) {
		this.treino = value;
	}
	
	public Treino getTreino() {
		return treino;
	}

	public String toString() {
		return String.valueOf(getID());
	}
	
}
