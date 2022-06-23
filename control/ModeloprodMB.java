package br.com.iris.control;


import br.com.iris.dao.ModeloprodDao;
import br.com.iris.model.Modeloprod;
import br.com.iris.model.Modelo;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "modeloprodMB")
@ViewScoped
public class ModeloprodMB implements Serializable{
    private List<Modeloprod> modeloprods;
    private Modeloprod modeloprod;
    
    Modelo modelo = new Modelo();
    
  
   
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

  
    

    public List<Modeloprod> getModeloprods() {
        if (modeloprods==null){
            refresh();
        }
        return modeloprods;
    }

    public void setModeloprods(List<Modeloprod> modeloprods) {
        this.modeloprods = modeloprods;
    }

    public void refresh(){
        setModeloprods(new ModeloprodDao().getAll(Modeloprod.class));
    }
    
    public Modeloprod getModeloprod() {
        return modeloprod;
    }

    public void setModeloprod(Modeloprod modeloprod) {
        this.modeloprod = modeloprod;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

     
    
    
    
    public void novo(){
        setModeloprod(new Modeloprod());
        etapa=1;
    }
    
    public void salvar(){
        if (getModeloprod()==null){return;}
        if (getModeloprod().getId()== 0){
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Modelo de produto é obrigatório"));
            return;
        }
        
        
        new ModeloprodDao().save(getModeloprod());
        
        
        refresh();
        setEtapa(0);
    }

    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getModeloprod()==null){return;}
        if (getModeloprod().getId()==0){return;}
        new ModeloprodDao().delete(Modeloprod.class,getModeloprod().getId());
        refresh();
        setEtapa(0);
    }
}
