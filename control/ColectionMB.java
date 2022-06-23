package br.com.iris.control;


import br.com.iris.model.Colection;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "colectionMB")
@ViewScoped
public class ColectionMB implements Serializable {

    private List<Colection> colections;
    private Colection colection = new Colection();
    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Colection> getColections() {
        return colections;
    }

    public void setColections(List<Colection> colections) {
        this.colections = colections;
    }

    public Colection getColection() {
        return colection;
    }

    public void setColection(Colection colection) {
        this.colection = colection;
    }

    

    
    public void editar() {
        setEtapa(1);
    }

    
    
}