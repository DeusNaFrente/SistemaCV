package br.com.iris.control;

import br.com.iris.dao.CondpgtoDao;
import br.com.iris.model.Condpgto;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "condpgtoMB")
@ViewScoped
public class CondpgtoMB implements Serializable{
    private List<Condpgto> condpgtos;
    private Condpgto condpgto;
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Condpgto> getCondpgtos() {
        if (condpgtos==null){
            refresh();
        }
        return condpgtos;
    }

    public void setCondpgtos(List<Condpgto> condpgtos) {
        this.condpgtos = condpgtos;
    }

    public void refresh(){
        setCondpgtos(new CondpgtoDao().getAll(Condpgto.class));
    }
    
    public Condpgto getCondpgto() {
        return condpgto;
    }

    public void setCondpgto(Condpgto condpgto) {
        this.condpgto = condpgto;
    }
    
    public void novo(){
        setCondpgto(new Condpgto());
        etapa=1;
    }
    
    public void salvar(){
        if (getCondpgto()==null){return;}
        if (getCondpgto().getCondpgt()==null){
          PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Condição de pgto é obrigatório"));
            return;
        }
        
        new CondpgtoDao().save(getCondpgto());
        refresh();
        setEtapa(0);
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getCondpgto()==null){return;}
        if (getCondpgto().getId()==0){return;}
        new CondpgtoDao().delete(Condpgto.class,getCondpgto().getId());
        refresh();
        setEtapa(0);
    }
}
