package br.com.agedsalao.convert;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.hibernate.loader.custom.Return;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import br.com.agedsalao.util.UtilMensagens;

@FacesConverter(value ="converteCalendar")
public class ConvertCalendar implements Converter {

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	// converte da tela para o objeto 
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		Calendar c = Calendar.getInstance();
		sdf.setLenient(false);
		try {
			c.setTime(sdf.parse(string));
		} catch (Exception e){
			return null;
		}
		return c;
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		Calendar c = (Calendar) obj;
		String str = sdf.format(c.getTime());
		return str;
	}
}
