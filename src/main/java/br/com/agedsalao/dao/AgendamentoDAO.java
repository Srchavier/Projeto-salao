package br.com.agedsalao.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import br.com.agedsalao.convert.ScheduleEventConverter;
import br.com.agedsalao.entity.Agendamento;
import br.com.agedsalao.entity.AgendamentoSchedule;
import br.com.agedsalao.entity.Person;
import br.com.agedsalao.filtresegurance.SessionContext;

public class AgendamentoDAO extends GenericDAO<Agendamento> {

	public AgendamentoDAO() {
		super(Agendamento.class);
	}

	public List<AgendamentoSchedule> listar() {
		Person list = (Person) SessionContext.getInstance().getAttribute("usuarioLogado");
		EntityManager manager = getEntityManager();
		List<Agendamento> lista = manager
				.createQuery("FROM Agendamento  where persons=" + list.getId(), Agendamento.class).getResultList();
		manager.close();
		List<AgendamentoSchedule> listaSchedule = new ArrayList<>();
		lista.forEach(ag -> {
			listaSchedule.add(ScheduleEventConverter.getConverter().toSchedule(ag));
		});
		return listaSchedule;
	}

	public boolean dataValid(java.util.Date date) {
		Person list = (Person) SessionContext.getInstance().getAttribute("usuarioLogado");
		EntityManager manager = getEntityManager();
		try {
			manager.createQuery("FROM Agendamento  where dateInicil= :date and persons= :id").setParameter("date", date)
					.setParameter("id", list.getId()).getSingleResult();
			return true;
		} catch (NoResultException | NonUniqueResultException e) {
			return false;
		}

	}

}
