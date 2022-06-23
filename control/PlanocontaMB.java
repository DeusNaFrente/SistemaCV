package br.com.iris.control;

import br.com.iris.dao.PlanocontaDao;
import br.com.iris.model.Planoconta;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "planocontaMB")
@ViewScoped
public class PlanocontaMB implements Serializable{
    private List<Planoconta> planocontas;
    private Planoconta planoconta = new Planoconta();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Planoconta> getPlanocontas() {
        if (planocontas==null){
            refresh();
        }
        return planocontas;
    }

    public void setCentrocustos(List<Planoconta> planocontas) {
        this.planocontas = planocontas;
    }

    public void refresh(){
        setCentrocustos(new PlanocontaDao().getAll(Planoconta.class));
    }
    
    public Planoconta getPlanoconta() {
        return planoconta;
    }

    public void setPlanoconta(Planoconta planoconta) {
        this.planoconta = planoconta;
    }
    
    public void novo(){
        setPlanoconta(new Planoconta());
        etapa=1;
    }
    
    public void salvar(){
        if (getPlanoconta()==null){return;}
        if (getPlanoconta().getPlanodecontas()==null){
          PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","O registro plano de contas é obrigatório"));
            return;
        }
        
        if (getPlanoconta().getTipo()==null){
          PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","O registro plano de contas é obrigatório"));
            return;
        }
     
        
        new PlanocontaDao().save(getPlanoconta());
        refresh();
        setEtapa(0);
        novo();
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getPlanoconta()==null){return;}
        if (getPlanoconta().getId()==0){return;}
        new PlanocontaDao().delete(Planoconta.class,getPlanoconta().getId());
        refresh();
        setEtapa(0);
    }
}
