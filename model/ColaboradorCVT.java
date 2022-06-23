package br.com.iris.model;

import br.com.iris.dao.ColaboradorDao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Colaborador.class)
public class ColaboradorCVT implements Converter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Colaborador getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        return new ColaboradorDao().getById(Colaborador.class, Integer.parseInt(arg2));  
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return "" + ((Colaborador) arg2).getId();
    }
}
