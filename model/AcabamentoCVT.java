package br.com.iris.model;

import br.com.iris.dao.AcabamentoDao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Acabamento.class)
public class AcabamentoCVT implements Converter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Acabamento getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        return new AcabamentoDao().getById(Acabamento.class, Integer.parseInt(arg2));  
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return "" + ((Acabamento) arg2).getId();
    }
}
