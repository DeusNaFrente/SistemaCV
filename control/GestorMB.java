package br.com.iris.control;

import br.com.iris.dao.GestorDao;
import br.com.iris.model.Gestor;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "gestorMB")
@ViewScoped
public class GestorMB implements Serializable{
    private List<Gestor> gestores;
    private Gestor gestor;
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Gestor> getGestores() {
        if (gestores==null){
            refresh();
        }
        return gestores;
    }

    public void setGestores(List<Gestor> gestores) {
        this.gestores = gestores;
    }

    public void refresh(){
        setGestores(new GestorDao().getAll(Gestor.class));
    }
    
    public Gestor getGestor() {
        return gestor;
    }

    public void setGestor(Gestor gestor) {
        this.gestor = gestor;
    }
    
    public void novo(){
        setGestor(new Gestor());
        etapa=1;
    }
    
    public void salvar(){
        if (getGestor()==null){return;}
        if (getGestor().getNome()==null){
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Nome do gestor é obrigatório"));
            return;
        }
        new GestorDao().save(getGestor());
        refresh();
        setEtapa(0);
    }

    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getGestor()==null){return;}
        if (getGestor().getId()==0){return;}
        new GestorDao().delete(Gestor.class,getGestor().getId());
        refresh();
        setEtapa(0);
    }
}
