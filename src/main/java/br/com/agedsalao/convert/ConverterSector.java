package br.com.agedsalao.convert;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.agedsalao.dao.SectorDAO;
import br.com.agedsalao.entity.Sector;
import br.com.agedsalao.util.JPAUtil;

public class ConverterSector implements Converter, Serializable {

	private SectorDAO dao;
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione um setor")){
			return null;
		}
		return JPAUtil.getInstance().getEntityManager().find(Sector.class, 
				Long.parseLong(string));
	}


	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null){
			return null;
		}
		Sector obj = (Sector) o;
		return obj.getId().toString();
	}


}
