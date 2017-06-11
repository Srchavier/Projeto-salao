package br.com.agedsalao.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * //
 * uniqueConstraints={@UniqueConstraint(columnNames="FIRST_NAME,LAST_NAME",name="IDX_1")},//COMO
 * // FAZIA ANTIGAMENTE JPA 2.0
 * 
 * @author Eduardo implements Serializable para nao ter nem um tipo de
 *         altera√ßao
 */

@Entity
@Table(name = "PERSONS") // versao // 2.1)//adicionar index // indice
public class Person extends AbstractPersistable<Long> {

	@NotEmpty(message = "Informe o primeiro nome")
	@Size(min = 5, max = 30)
	@Column(name = "FIRST_NAME", nullable = false, length = 30)
	private String firstName;

	@NotEmpty(message = "Informe o segundo nome")
	@Size(min = 5, max = 60)
	@Column(name = "LAST_NAME", nullable = false, length = 60)
	private String lastName;

	@NotEmpty(message = "Email deve ser informado")
	@Size(min = 5, max = 60)
	@Email(message = "Email valido")
	@Column(name = "EMAIL", nullable = false, unique = true, length = 60)
	private String email;

	@NotEmpty(message = "Senha deve ser informando")
	@Length(max = 20, min = 5)
	@Size(min = 5, max = 20)
	@Column(name = "SENHA", nullable = false, length = 20)
	private String senha;

	@NotEmpty(message = "Usuario deve ser informando")
	@Length(max = 20, min = 5)
	@Size(min = 5, max = 20)
	@Column(name = "NOME_USUARIO", nullable = false, length = 20)
	private String nomeUsuario;

	@Column(name = "DATA_NASCIMENTO")
	private Calendar dataNascimento;

	@Column(name = "data_cadastro")
	private Date dataCadastro;

	@NotEmpty
	@Size(max = 40)
	@Column(name = "CIDADE", nullable = false)
	private String cidade;
	
	@NotEmpty
	@Size(max = 70)
	@Column(name = "ENDERE«O", nullable = false)
	private String endereco;
	
	@CPF(message = "Nao pode ser nulo")
	@Column(name = "CPF", nullable = false, unique = true, length = 14)
	private String cpf;
	
	@NotEmpty
	@Column(name = "NUMBER", nullable = false)
	private String number;
	
	@NotNull(message = "Informe o sexo")
	@Enumerated(EnumType.STRING) // aqui vc coloca o tipo de dados
	@Column(name = "TYPE_SEXO")
	private TypeSexo type;

	@NotNull(message = "Setor deve ser informando")
	@ManyToOne
	@JoinColumn(name = "SECTOR", referencedColumnName = "id", nullable = false)
	private Sector setor;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.trim().toLowerCase();
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha.trim();
	}

	public Sector getSetor() {
		return setor;
	}

	public void setSetor(Sector setor) {
		this.setor = setor;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public TypeSexo getType() {
		return type;
	}

	public void setType(TypeSexo type) {
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName;
	}
	
	

}
