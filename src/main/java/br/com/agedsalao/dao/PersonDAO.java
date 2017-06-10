package br.com.agedsalao.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.agedsalao.entity.Person;
import br.com.agedsalao.util.UtilErros;
import br.com.agedsalao.util.UtilMensagens;

public class PersonDAO extends GenericDAO<Person> {


	public PersonDAO() {
		super(Person.class);

	}
	public List<Person> listar(){
		return findAll();
	}

	public Person fidByEmailAndSenha(String email, String senha) {
		String jpql = "from Person p where p.email like ? and p.senha like ?";

		return findOne(jpql, email, senha);
	}

	public List<Person> finByLastName(String lastName) {
		String jpql = "from Person p where p.lastName like ?";

		return find(jpql, lastName);
	}

	public List<Person> findAgeIsBetween(int min, int max) {
		String jpql = "from Person p where p.age between ? and ?";
		return find(jpql, min, max);
	}

	public Person fidByFullName(String firstName, String lastName) {
		String jpql = "from Person p where p.firstName like ? and p.lastName like ?";

		return findOne(jpql, firstName, lastName);
	}

	public Person findBylogin(String nomeUsuario, String senha) {
		return login(nomeUsuario, senha);
	}

	public Person findByLocalizaAndEmail(String email) {

		return findByLocalizaAndEmai(email);
	}

	
}
