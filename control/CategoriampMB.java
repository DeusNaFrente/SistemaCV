package br.com.iris.control;

import br.com.iris.dao.CategoriampDao;
import br.com.iris.model.Categoriamp;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "categoriampMB")
@ViewScoped
public class CategoriampMB implements Serializable{
    private List<Categoriamp> categoriamps;
    private Categoriamp categoriamp = new Categoriamp();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Categoriamp> getCategoriamps() {
        if (categoriamps==null){
            refresh();
        }
        return categoriamps;
    }

    public void setCategoriamps(List<Categoriamp> categoriamps) {
        this.categoriamps = categoriamps;
    }

    public void refresh(){
        setCategoriamps(new CategoriampDao().getAll(Categoriamp.class));
    }
    
    public Categoriamp getCategoriamp() {
        return categoriamp;
    }

    public void setCategoriamp(Categoriamp categoriamp) {
        this.categoriamp = categoriamp;
    }
    
    public void novo(){
        setCategoriamp(new Categoriamp());
        etapa=1;
    }
    
    public void salvar(){
        if (getCategoriamp()==null){return;}
        if (getCategoriamp().getCategoria()==null){
          PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","O registro de nome categoria é obrigatório"));
            return;
        }
        
        
     
        
        new CategoriampDao().save(getCategoriamp());
        refresh();
        setEtapa(0);
        novo();
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getCategoriamp()==null){return;}
        if (getCategoriamp().getId()==0){return;}
        new CategoriampDao().delete(Categoriamp.class,getCategoriamp().getId());
        refresh();
        setEtapa(0);
        novo();
    }
}
