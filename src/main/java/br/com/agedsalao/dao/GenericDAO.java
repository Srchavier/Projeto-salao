package br.com.agedsalao.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.agedsalao.entity.Person;
import br.com.agedsalao.util.JPAUtil;
import br.com.agedsalao.util.UtilErros;
import br.com.agedsalao.util.UtilMensagens;

/**
 * @author Eduardo abstract porque nao quero q ela seja instancianda so e
 *         herdanda
 * @param <t>
 *            Serializable classe de entidade tem q ser sempre Serializable
 */

public abstract class GenericDAO<T extends Serializable> {

	/**
	 * atributo do tipo- class com generic
	 */
	private Class<T> aClass;

	/**
	 * vamos passa o tipo da classe q vai inicializar essa classe
	 * 
	 * @param <T>
	 */
	protected GenericDAO(Class<T> aClass) {
		this.aClass = aClass;// atualizar com o valor q vem como paramento
	}

	/**
	 * @return retorna nossa instancia de jpa com retorno do do metodo
	 *         getinstance metodo q chamo depois geEntityManager que em chamo
	 *         logo apos vai retorna um objeto do tipo Entytimanager para eu
	 *         trabalha
	 */
	protected EntityManager getEntityManager() {// criando a fabrica
		return JPAUtil.getInstance().getEntityManager();
	}

	/**
	 * @param entity
	 *            vamos ter uma entitade como paremento tipo de generic a gente
	 *            vai sabe a parti do generic q temos na assinatura da classe
	 * 
	 */

	public boolean save(T entity) {
		EntityManager manager = getEntityManager();// chama o metodo de cima
													// passa o objeto ja criado
													// do tipo entyttimanager
		try {
			manager.getTransaction().begin();// inicia a transaçao
			if (entity == null) {
				manager.persist(entity);// persist o objeto q tamos recebendo
			} else {
				manager.merge(entity);
				
			}
			manager.getTransaction().commit();// vamos commint a transaçao
			UtilMensagens.mensagemInformacao(" sucesso!");
			return true;
		} catch (Exception e) {
			if (manager.getTransaction().isActive() == false) {
				manager.getTransaction().begin();
			}
			manager.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao alterar ou salvar" + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	public boolean excluir(T entity) {
		EntityManager manager = getEntityManager();
		try {

			manager.getTransaction().begin();
			manager.remove(manager.merge(entity));
			manager.getTransaction().commit();
			UtilMensagens.mensagemInformacao("removido com sucesso!");
			return true;
		} catch (Exception e) {
			if (manager.getTransaction().isActive() == false) {
				manager.getTransaction().begin();
			}
			manager.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover grupo" + UtilErros.getMensagemErro(e));
			return false;
		}
	}

	public void delete(Long id) {
		EntityManager manager = getEntityManager();// chama o metodo de cima
													// passa o objeto ja criando
													// do tipo entyttimanager
		manager.getTransaction().begin();// inicia a transaçao
		// esse metodo busca todo o
		// conteudo//manager.remove(manager.find(aClass, id));//temos q passa
		// uma entidade essa entidade ja tem q ser um id
		// vai fazer uma consulta por id e e devolver o objeto ai a gente remove
		manager.remove(manager.getReference(aClass, id));// metodo busca so a
															// referencia faz o
															// upload depois
															// remove
		manager.getTransaction().commit();// vamos commint a transaçao
		manager.close();// encerra e libera da memoria
	}

	public void delete(T entity) {
		EntityManager manager = getEntityManager();// chama o metodo de cima
													// passa o objeto ja criando
													// do tipo entyttimanager
		manager.getTransaction().begin();// inicia a transaçao
		// esse metodo busca todo o
		// conteudo//manager.remove(manager.find(aClass, id));//temos q passa
		// uma entidade essa entidade ja tem q ser um id
		// vai fazer uma consulta por id e e devolver o objeto ai a gente remove
		manager.remove(manager.merge(entity));// merge faz um update em cascata
												// depois executa o delete
		manager.getTransaction().commit();// vamos commint a transaçao
		manager.close();// encerra e libera da memoria
	}

	/**
	 * consulta id findbyid para consulta por id para retorna um unico objeto.
	 */
	public T findByid(Long id) {
		EntityManager manager = getEntityManager();// chama o metodo de cima
													// passa o objeto ja criando
													// do tipo entyttimanager
		manager.getTransaction().begin();// inicia a transaçao

		T entity = (T) manager.find(aClass, id);
		// ja recebemos uma entidade como retorno
		// a parti do metodo manager.find
		// faz um quest para pega objeto do msm jeito
		manager.getTransaction().commit();// vamos commint a transaçao
		manager.close();// encerra e libera da memoria

		return entity;
	}

	/**
	 * consulta lista retorna uma lista
	 */
	@SuppressWarnings("unchecked") // gera usso pq ele nao sabe q tipo ele vai
									// retornar
	public List<T> findAll() {
		EntityManager manager = getEntityManager();// chama o metodo de cima
													// passa o objeto ja criando
													// do tipo entyttimanager
		manager.getTransaction().begin();// inicia a transaçao

		Query query = manager.createQuery("from " + aClass.getSimpleName());// pega
																			// o
																			// nome
																			// da
																			// classe
																			// para
																			// consulta
		// precisa de uma query como paramento
		List<T> entities = query.getResultList();// recupera os resultandos
		// retorno da lista
		manager.getTransaction().commit();// vamos commint a transaçao
		manager.close();// encerra e libera da memoria

		return entities;
	}

	/**
	 * @param jpql
	 *            passa string
	 * @param params
	 *            paramentos
	 * @return consulta
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(String jpql, Object... params) {// lista baseada em
														// paramento ,object os
														// valores referente a
														// consulta
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();

		Query query = manager.createQuery(jpql);
		// Essa e uma consulta baseada em jpql trabalhando com a classe Person e
		// a da classe nao da tabela
		// nois ultilizamos o atributo name da classe nao da tabela
		// ser tiver a idade no age retorna tudo q tiver

		// add paramentos referente a casa um ? da consulta ali em cima
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		List<T> entities = query.getResultList();// returna uma lista

		manager.getTransaction().commit();
		manager.close();

		return entities;

	}

	/**
	 * @param jpql
	 *            passa string
	 * @param params
	 *            objetos q passamos qlq tpo
	 * @return consulta
	 */
	@SuppressWarnings("unchecked")
	public T findOne(String jpql, Object... params) {// lista baseada em
														// paramento ,object os
														// valores referente a
														// consulta
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();

		Query query = manager.createQuery(jpql);
		// Essa e uma consulta baseada em jpql trabalhando com a classe Person e
		// a da classe nao da tabela
		// nois ultilizamos o atributo name da classe nao da tabela
		// ser tiver a idade no age retorna tudo q tiver

		// add paramentos referente a casa um ? da consulta ali em cima
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		T entity = (T) query.getSingleResult();// retorna uma entidade

		manager.getTransaction().commit();
		manager.close();

		return entity;

	}

	/**
	 * @return contador
	 */
	public long count() {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();

		Query query = manager.createQuery("select count(c) from " + aClass.getSimpleName() + " c");// consulta
																									// pelo
																									// nome
																									// da
																									// classe
																									// getSimpleName

		long count = (Long) query.getSingleResult();// passa o resultando para
													// long

		manager.getTransaction().commit();
		manager.close();

		return count;
	}

	public Person login(String nomeUsuario, String senha) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		try {
			Person pessoa = manager
					.createQuery("from Person p where p.nomeUsuario= :nomeUsuario and p.senha = :senha", Person.class)
					.setParameter("nomeUsuario", nomeUsuario.toUpperCase()).setParameter("senha", senha.toUpperCase())
					.getSingleResult();
			pessoa.setSenha("");
			UtilMensagens.mensagemInformacao("Login sucesso");
			return pessoa;
		} catch (NoResultException e) {
			UtilMensagens.mensagemErro("Erro ao remover grupo" + UtilErros.getMensagemErro(e));
			return null;
		}
	}

	public Person findByLocalizaAndEmai(String email) {
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();

		return (Person) manager.createQuery("from Person p where p.email = :email ")
				.setParameter("email", email.toUpperCase()).getSingleResult();

	}

}
