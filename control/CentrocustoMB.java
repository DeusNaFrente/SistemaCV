package br.com.iris.control;

import br.com.iris.dao.CentrocustoDao;
import br.com.iris.model.Centrocusto;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "centrocustoMB")
@ViewScoped
public class CentrocustoMB implements Serializable{
    private List<Centrocusto> centrocustos;
    private Centrocusto centrocusto = new Centrocusto();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Centrocusto> getCentrocustos() {
        if (centrocustos==null){
            refresh();
        }
        return centrocustos;
    }

    public void setCentrocustos(List<Centrocusto> centrocustos) {
        this.centrocustos = centrocustos;
    }

    public void refresh(){
        setCentrocustos(new CentrocustoDao().getAll(Centrocusto.class));
    }
    
    public Centrocusto getCentrocusto() {
        return centrocusto;
    }

    public void setCentrocusto(Centrocusto centrocusto) {
        this.centrocusto = centrocusto;
    }
    
    public void novo(){
        setCentrocusto(new Centrocusto());
        etapa=1;
    }
    
    public void salvar(){
        if (getCentrocusto()==null){return;}
        if (getCentrocusto().getCentrocusto()==null){
          PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","O registro de nome tarefa é obrigatório"));
            return;
        }
        
        
     
        
        new CentrocustoDao().save(getCentrocusto());
        refresh();
        setEtapa(0);
        novo();
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getCentrocusto()==null){return;}
        if (getCentrocusto().getId()==0){return;}
        new CentrocustoDao().delete(Centrocusto.class,getCentrocusto().getId());
        refresh();
        setEtapa(0);
    }
}
