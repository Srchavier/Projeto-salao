package br.com.agedsalao.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.agedsalao.dao.SectorDAO;
import br.com.agedsalao.entity.Sector;

@ManagedBean(name = "setorBean")
@SessionScoped
public class SectorBean {

	private SectorDAO dao;
	private Sector objeto;

	public SectorBean() {
		dao = new SectorDAO();
	}

	public String novo() {
		objeto = new Sector();
        return "form";
	}

	public String listar() {
		return "/private/sector/listar?faces-redirect=true";
	}

	public String excluir(Sector obj) {
		dao.excluir(obj);
		return "listar";
	}

	public String cancel() {
		return "listar";
	}

	public String save() {
		dao.save(objeto);
		return "listar";
	}

	public String alterar(Sector obj) {
		objeto = obj;
		return "form";
	}

	public List<Sector> listTodos() {
		return dao.findAll();
	}

	public SectorDAO getDao() {
		return dao;
	}

	public void setDao(SectorDAO dao) {
		this.dao = dao;
	}

	public Sector getObjeto() {
		return objeto;
	}

	public void setObjeto(Sector objeto) {
		this.objeto = objeto;
	}

}
