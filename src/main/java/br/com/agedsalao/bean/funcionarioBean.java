package br.com.agedsalao.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.agedsalao.convert.ConverterSector;
import br.com.agedsalao.dao.PersonDAO;
import br.com.agedsalao.dao.SectorDAO;
import br.com.agedsalao.entity.Person;
import br.com.agedsalao.entity.Sector;
import br.com.agedsalao.util.UtilMensagens;

@ManagedBean(name = "CadastroBean")
@ViewScoped
public class funcionarioBean {
	private Person person;
	private Sector sector;
	private PersonDAO personDAO;
	private String type;
	private SectorDAO sectorDAO;
	private ConverterSector converterSector;

	public funcionarioBean() {
		personDAO = new PersonDAO();
		this.person = new Person();
		this.sector = new Sector();
		this.sectorDAO = new SectorDAO();
		this.converterSector = new ConverterSector();
	}

	public String adicionar() {
		if (personDAO.save(person)) {
			return "listar";
		} else {
			return "cadastroPessoa";
		}

	}
	public List<Person> listTodos() {
		return personDAO.findAll();
	}
	
	public String novo() {
		person = new Person();
		return "/employee/cadastroPessoa";

	}

	public String list() {
		return "/private/employee/listar?faces-redirect=true";
	}

	public String alterar(Person obj) {
		person = obj;
		return "cadastroPessoa";
	}

	public String excluir(Person obj) {
		personDAO.excluir(obj);
		return "/private/employee/listar";
	}
	public String cancelar(){
		return "listar";
	}


	public List<Sector> listarSetor() {
		return sectorDAO.findAll();
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public SectorDAO getSectorDAO() {
		return sectorDAO;
	}

	public void setSectorDAO(SectorDAO sectorDAO) {
		this.sectorDAO = sectorDAO;
	}

	public ConverterSector getConverterSector() {
		return converterSector;
	}

	public void setConverterSector(ConverterSector converterSector) {
		this.converterSector = converterSector;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	
}
