package br.com.agedsalao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.primefaces.model.DefaultScheduleEvent;

import br.com.agedsalao.convert.ScheduleEventConverter;
import br.com.agedsalao.entity.Agendamento;
import br.com.agedsalao.entity.AgendamentoSchedule;
import br.com.agedsalao.entity.Person;

public class agendamentoDAO extends GenericDAO<Agendamento> {
	
	public agendamentoDAO() {
		super(Agendamento.class);		
	}

	public List<AgendamentoSchedule> listar() {
		EntityManager manager = getEntityManager();
		List<Agendamento> lista = manager.createQuery("FROM Agendamento", Agendamento.class).getResultList();
		manager.close();
		List<AgendamentoSchedule> listaSchedule = new ArrayList<>();
		lista.forEach(ag -> {
			listaSchedule.add(ScheduleEventConverter.getConverter().toSchedule(ag));
		});
		return listaSchedule;
	}
	

}
