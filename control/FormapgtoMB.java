package br.com.iris.control;

import br.com.iris.dao.FormapgtoDao;
import br.com.iris.model.Formapgto;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "formapgtoMB")
@ViewScoped
public class FormapgtoMB implements Serializable{
   
    private List<Formapgto> formapgtos;
    private Formapgto formapgto;
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Formapgto> getFormapgtos() {
        if (formapgtos==null){
            refresh();
        }
        return formapgtos;
        
    }

    public void setFormapgtos(List<Formapgto> formapgtos) {
        this.formapgtos = formapgtos;
    }

    public void refresh(){
        setFormapgtos(new FormapgtoDao().getAll(Formapgto.class));
    }
    
    public Formapgto getFormapgto() {
        return formapgto;
    }

    public void setFormapgto(Formapgto formapgto) {
        this.formapgto = formapgto;
    }
    
    public void novo(){
        setFormapgto(new Formapgto());
        etapa=1;
    }
    
    public void salvar(){
        if (getFormapgto()==null){return;}
        if (getFormapgto().getForma()==null){
          PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Forma de pgto é obrigatório"));
            return;
        }
        
        new FormapgtoDao().save(getFormapgto());
        refresh();
        setEtapa(0);
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getFormapgto()==null){return;}
        if (getFormapgto().getId()==0){return;}
        new FormapgtoDao().delete(Formapgto.class,getFormapgto().getId());
        refresh();
        setEtapa(0);
    }
}
