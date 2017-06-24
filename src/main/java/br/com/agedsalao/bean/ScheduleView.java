package br.com.agedsalao.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import br.com.agedsalao.convert.ConverterPerson;
import br.com.agedsalao.convert.ScheduleEventConverter;
import br.com.agedsalao.dao.PersonDAO;
import br.com.agedsalao.dao.agendamentoDAO;
import br.com.agedsalao.entity.Agendamento;
import br.com.agedsalao.entity.AgendamentoSchedule;
import br.com.agedsalao.entity.Person;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

	private static final long serialVersionUID = 1L;

	private ConverterPerson convertPerson;

	private ScheduleModel eventModel;

	private ScheduleEvent event;

	private Agendamento agendamento;

	private List<AgendamentoSchedule> agenda;

	private agendamentoDAO agendamentoDAO;

	private PersonDAO personDAO;

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		agendamentoDAO = new agendamentoDAO();
		personDAO = new PersonDAO();
		convertPerson = new ConverterPerson();
		buscarTodos();

	}

	public void prepararEvento() {
		event = new AgendamentoSchedule();
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(ActionEvent actionEvent) throws ParseException {
		if (event.getId() == null) {
			for (ScheduleEvent eventoAux : eventModel.getEvents()) {
				if (eventoAux.getStartDate().compareTo(event.getStartDate()) == 0) {
					if (((AgendamentoSchedule) eventoAux).getPerson().equals(((AgendamentoSchedule) event).getPerson())) {
						addMessage(new FacesMessage("Ja existe horario!"));
						return;
					}
				}
			}
			agendamento = ScheduleEventConverter.getConverter().toAgendamento((AgendamentoSchedule) event);
			agendamentoDAO.save(agendamento);

		} else {
			agendamento = ScheduleEventConverter.getConverter().toAgendamento((AgendamentoSchedule) event);
			agendamentoDAO.save(agendamento);
		}
		addMessage(new FacesMessage("Evento salvo com sucesso!"));

		agendamento = new Agendamento();
		event = new AgendamentoSchedule();

		buscarTodos();
		
	}

	private void buscarTodos() {
		agenda = agendamentoDAO.listar();
		eventModel.clear();
		agenda.forEach(ag -> {
			eventModel.addEvent(ag);
		});
	}

	public void excluir() {
		if (event.getData() != null) {
			agendamento = ScheduleEventConverter.getConverter().toAgendamento((AgendamentoSchedule) event);
			agendamentoDAO.delete(agendamento.getId());
		} else {
			addMessage(new FacesMessage("Nao existe regristo"));
		}
		addMessage(new FacesMessage("Excluído com sucesso!"));
		buscarTodos();
	}

	public List<Person> listarSetor() {
		return personDAO.findAll();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (AgendamentoSchedule) selectEvent.getObject();
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public ConverterPerson getConvertPerson() {
		return convertPerson;
	}

	public void setConvertPerson(ConverterPerson convertPerson) {
		this.convertPerson = convertPerson;
	}

}