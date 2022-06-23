package br.com.iris.model;

import br.com.iris.dao.CondpgtoDao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Condpgto.class)
public class CondpgtoCVT implements Converter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Condpgto getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        return new CondpgtoDao().getById(Condpgto.class, Integer.parseInt(arg2));  
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return "" + ((Condpgto) arg2).getId();
    }
}
