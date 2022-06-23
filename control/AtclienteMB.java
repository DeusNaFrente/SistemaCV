package br.com.iris.control;

import br.com.iris.dao.AtclienteDao;
import br.com.iris.model.Atcliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "atclienteMB")
@ViewScoped
public class AtclienteMB implements Serializable{
    private List<Atcliente> atclientes;
    private Atcliente atcliente = new Atcliente();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Atcliente> getAtclientes() {
        if (atclientes==null){
            refresh();
        }
        return atclientes;
    }

    public void setAtclientes(List<Atcliente> atclientes) {
        this.atclientes = atclientes;
    }

    public void refresh(){
        setAtclientes(new AtclienteDao().getAll(Atcliente.class));
    }
    
    public Atcliente getAtcliente() {
        return atcliente;
    }

    public void setAtcliente(Atcliente atcliente) {
        this.atcliente = atcliente;
    }
    
    public void novo(){
        setAtcliente(new Atcliente());
        etapa=1;
    }
    
    public void salvar(){
        if (getAtcliente()==null){return;}
        
       if (getAtcliente().getEmpresa()== null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Empresa é obrigatório"));
            return;
        }
        
      
        new AtclienteDao().save(getAtcliente());
        refresh();
        setEtapa(0);
        novo();
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getAtcliente()==null){return;}
        if (getAtcliente().getId()==0){return;}
        new AtclienteDao().delete(Atcliente.class,getAtcliente().getId());
        refresh();
        setEtapa(0);
    }
}
