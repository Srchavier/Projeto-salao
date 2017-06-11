package br.com.agedsalao.convert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.agedsalao.dao.PersonDAO;
import br.com.agedsalao.entity.Person;
import br.com.agedsalao.util.JPAUtil;

public class ConverterPerson implements Converter, Serializable {

	private PersonDAO dao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione profissional")){
			return null;
		}
		return JPAUtil.getInstance().getEntityManager().find(Person.class, 
				Long.parseLong(string));
	}


	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null){
			return null;
		}
		Person obj = (Person) o;
		return obj.getId().toString();
	}

}