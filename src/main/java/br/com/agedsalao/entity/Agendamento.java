package br.com.agedsalao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "AGEDAMENTO")
public class Agendamento extends AbstractPersistable<Long> {
	
	@Column(name = "NOME" , length=40)
	private String nome;
	
	@Column(name="DATEINICIL")
	private Date dateInicil;
	
	@Column(name="DATEFIM")
	private Date dateFim;
	
	@Column(name="VALOR", columnDefinition="numeric(10,2)")
	private String valor;

	@ManyToOne
	@JoinColumn(name = "PERSONS", referencedColumnName = "id" )
	private Person person;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public Date getDateInicil() {
		return dateInicil;
	}

	public void setDateInicil(Date dateInicil) {
		this.dateInicil = dateInicil;
	}

	public Date getDateFim() {
		return dateFim;
	}

	public void setDateFim(Date dateFim) {
		this.dateFim = dateFim;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}
	
	
	
	
	
}
