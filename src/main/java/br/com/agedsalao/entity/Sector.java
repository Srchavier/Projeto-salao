package br.com.agedsalao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name="SECTOR")
public class Sector extends AbstractPersistable<Long> {

	@NotEmpty(message ="Setor nao pode ser nulo")
	@Column(name="NOME" ,nullable = false,length=100)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
