package br.com.iris.control;

import br.com.iris.dao.MateriaprimaDao;
import br.com.iris.model.Materiaprima;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "materiaprimaMB")
@ViewScoped
public class MateriaprimaMB implements Serializable{
    private List<Materiaprima> materiasprimas;
    private Materiaprima materiaprima = new Materiaprima();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Materiaprima> getMateriasprimas() {
        if (materiasprimas==null){
            refresh();
        }
        return materiasprimas;
    }

    public void setMateriasprimas(List<Materiaprima> materiasprimas) {
        this.materiasprimas = materiasprimas;
    }

    public void refresh(){
        setMateriasprimas(new MateriaprimaDao().getAll(Materiaprima.class));
    }
    
    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }
    
    public void novo(){
        setMateriaprima(new Materiaprima());
        etapa=1;
    }
    
    public void salvar(){
        if (getMateriaprima()==null){return;}
        if (getMateriaprima().getMateriaprima()==null){
          PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Matéria prima é obrigatório"));
            return;
        }
        
        
     
        
        new MateriaprimaDao().save(getMateriaprima());
        refresh();
        setEtapa(0);
        novo();
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getMateriaprima()==null){return;}
        if (getMateriaprima().getId()==0){return;}
        new MateriaprimaDao().delete(Materiaprima.class,getMateriaprima().getId());
        refresh();
        setEtapa(0);
    }
}
