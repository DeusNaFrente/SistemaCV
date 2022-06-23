package br.com.iris.control;

import br.com.iris.dao.IrisfornecDao;
import br.com.iris.dao.IrisprodDao;
import br.com.iris.model.Irisfornec;
import br.com.iris.model.Irisprod;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "irisprodMB")
@ViewScoped
public class IrisprodMB implements Serializable {

    private List<Irisprod> itensTabela;
    private Irisprod irisp = new Irisprod();
    
    private Irisfornec irisf = new Irisfornec();

    private List<Irisfornec> irisfor;

    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Irisprod> getItensTabela() {
        if (itensTabela == null) {
            refresh();
        }
        return itensTabela;
    }

    public void refresh() {
        setItensTabela(new IrisprodDao().getAll(Irisprod.class));
        setIrisfor(new IrisfornecDao().getAll(Irisfornec.class));
    }

        
    public void setItensTabela(List<Irisprod> itensTabela) {
        this.itensTabela = itensTabela;
    }

    public Irisprod getIrisp() {
        return irisp;
    }

    public void setIrisp(Irisprod irisp) {
        this.irisp = irisp;
    }

 

    public List<Irisfornec> getIrisfor() {
         if (irisfor == null) {
            refresh();
        }
        return irisfor;
    }

    public void setIrisfor(List<Irisfornec> irisfor) {
        this.irisfor = irisfor;
    }

    public Irisfornec getIrisf() {
        return irisf;
    }

    public void setIrisf(Irisfornec irisf) {
        this.irisf = irisf;
    }
    
    
    
    
    public void novo(){
        setIrisp(new Irisprod());
        etapa=1;
    }

  public void salvar() {
        if (getIrisp() == null) {return;}
        
        if (getIrisp().getDescricao() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Descrição é obrigatório"));
            return;
        }
        
        new IrisprodDao().save(getIrisp());
        refresh();
        setEtapa(0);
        novo();
    }
  
  public void excluir(){
        if (getIrisp()==null){return;}
        if (getIrisp().getId()==0){return;}
        new IrisprodDao().delete(Irisprod.class,getIrisp().getId());
        refresh();
        setEtapa(0);
    }
  
     public void editar(){
        setEtapa(1);
    } 
  
}
