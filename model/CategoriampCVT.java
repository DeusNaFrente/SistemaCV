package br.com.iris.model;


import br.com.iris.dao.CategoriampDao;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(forClass = Categoriamp.class)
public class CategoriampCVT implements Converter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public Categoriamp getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        return new CategoriampDao().getById(Categoriamp.class, Integer.parseInt(arg2));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        return "" + ((Categoriamp) arg2).getId();
    }
}
