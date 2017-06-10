package br.com.agedsalao.convert;

import java.util.regex.Matcher;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneConvert")
public class ConvertTelefone implements Converter {

	private static final  String formate = 	"(99)9 9999-9999";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		String telTratado = null;

		if (value == null && value.equals("")) {
			return null;
		} else if (value != null) {
			telTratado = value.replaceAll("[^ 0-9]", " ");
			telTratado = value.replaceAll(" ", "");
		}

		return telTratado;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		return formate.format((String) value);
	}

}
