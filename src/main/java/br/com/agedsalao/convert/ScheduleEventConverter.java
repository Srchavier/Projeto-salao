package br.com.agedsalao.convert;

import java.util.Date;

import br.com.agedsalao.entity.Agendamento;
import br.com.agedsalao.entity.AgendamentoSchedule;

public class ScheduleEventConverter {

	private static ScheduleEventConverter converter;

	private ScheduleEventConverter() {
	}

	public static ScheduleEventConverter getConverter() {
		if (converter == null)
			converter = new ScheduleEventConverter();

		return converter;
	}

	public Agendamento toAgendamento(AgendamentoSchedule event) {
		if (event != null) {
			Agendamento a = new Agendamento();
			a.setId((Long) event.getData());
			a.setNome(event.getTitle());
			a.setValor(event.getValor());
			a.setPerson(event.getPerson());
			a.setDateInicil((Date) event.getStartDate());
			a.setDateFim((Date) event.getEndDate());

			return a;
		}
		return null;
	}

	public AgendamentoSchedule toSchedule(Agendamento agendamento) {
		if (agendamento != null) {
			AgendamentoSchedule as = new AgendamentoSchedule();
			as.setData(agendamento.getId());
			as.setTitle(agendamento.getNome());
			as.setPerson(agendamento.getPerson());
			as.setValor(agendamento.getValor());
			as.setStartDate(agendamento.getDateInicil());
			as.setEndDate(agendamento.getDateFim());

			return as;
		}
		return null;
	}

}
