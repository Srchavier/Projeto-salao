package br.com.agedsalao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Eduardo
 *
 */
public class JPAUtil {

	/**
	 * atibuto
	 */
	private EntityManagerFactory factory;
	private static JPAUtil instance;
	
	
	/**
	 * INICIA A FACTOry COM UNIDADE DE PERSISTENCE
	 */
	private JPAUtil(){
		this.factory=Persistence.createEntityManagerFactory("pq");
	}

	/**
	 * synchronized para q esse metodo seja sincronizando aparti da trend
	 * @return retorna um jpaulti
	 * if testa ser ela nao esta igual a null vai usa essa instancia, ser tive ele cria uma instance
	 */
	public static synchronized JPAUtil getInstance(){
		
		if(instance==null){
			instance=new JPAUtil();
		}
		return instance;
	}
	
	
	/**
	 * @return UM OBJETO entitymanager  a parti da fabrica  assim retorna para dao
	 */
	public EntityManager getEntityManager(){
		return factory.createEntityManager();
	}
	
}
