package br.com.iris.control;

import br.com.iris.dao.EpiDao;
import br.com.iris.model.Epi;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "epiMB")
@ViewScoped
public class EpiMB implements Serializable{
    private List<Epi> epis;
    private Epi epi = new Epi();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Epi> getEpis() {
        if (epis==null){
            refresh();
        }
        return epis;
    }

    public void setEpis(List<Epi> epis) {
        this.epis = epis;
    }

    public void refresh(){
        setEpis(new EpiDao().getAll(Epi.class));
    }
    
    public Epi getEpi() {
        return epi;
    }

    public void setEpi(Epi epi) {
        this.epi = epi;
    }
    
    public void novo(){
        setEpi(new Epi());
        etapa=1;
    }
    
    public void salvar(){
        if (getEpi()==null){return;}
        
       if (getEpi().getCargo()== null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Empresa é obrigatório"));
            return;
        }
        
      
        new EpiDao().save(getEpi());
        refresh();
        setEtapa(0);
        novo();
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getEpi()==null){return;}
        if (getEpi().getId()==0){return;}
        new EpiDao().delete(Epi.class,getEpi().getId());
        refresh();
        setEtapa(0);
    }
}
