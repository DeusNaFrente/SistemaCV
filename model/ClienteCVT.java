package br.com.iris.model;


import br.com.iris.dao.ClienteDao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Cliente.class)
public class ClienteCVT implements Converter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Cliente getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        return new ClienteDao().getById(Cliente.class, Integer.parseInt(arg2));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return "" + ((Cliente) arg2).getId();
    }
}
