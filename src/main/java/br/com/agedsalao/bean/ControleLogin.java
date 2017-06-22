package br.com.agedsalao.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.agedsalao.dao.PersonDAO;
import br.com.agedsalao.entity.Person;
import br.com.agedsalao.filtresegurance.SessionContext;
import br.com.agedsalao.util.UtilMensagens;

@ManagedBean(name = "controleLogin")
@SessionScoped
public class ControleLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	private PersonDAO dao;
	private Person usuarioLogado;
	private String nomeUsuario;
	private String senha;

	public ControleLogin() {
		dao = new PersonDAO();
	}

	public String paginaLogin() {
		return "/login";
	}

	public String efetuarLogin() {
		usuarioLogado = dao.login(nomeUsuario, senha);

		if (usuarioLogado != null) {
			SessionContext.getInstance().setAttribute("usuarioLogado", usuarioLogado);
			UtilMensagens.mensagemInformacao("Login efetuado com sucesso!");
			return "/private/index?faces-redirect=true";
		} else {
			UtilMensagens.mensagemErro("Login nao efetuado com sucesso" + " Usuario ou senha invalidos!");
			return "/login";
		}

	}

	public String efetuarLogout() {
		SessionContext.getInstance().encerrarSessao();
		usuarioLogado = null;
		return "/login?faces-redirect=true";
	}

	public PersonDAO getDao() {
		return dao;
	}

	public void setDao(PersonDAO dao) {
		this.dao = dao;
	}

	public Person getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Person usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public String getnomeUsuario() {
		return nomeUsuario;
	}

	public void setnomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
