package br.com.iris.control;



import br.com.iris.dao.IrisfornecDao;
import br.com.iris.model.Irisfornec;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;



@ManagedBean(name = "irisfornecMB")
@ViewScoped
public class IrisfornecMB implements Serializable{
    private List<Irisfornec> itensTabela;
    private Irisfornec irisfornec = new Irisfornec();

    private int etapa = 0;
    

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
    
      public List<Irisfornec> getItensTabela() {
        if (itensTabela == null) {
            refresh();
        }
        return itensTabela;
    }

    public void refresh() {
        setItensTabela(new IrisfornecDao().getAll(Irisfornec.class));
    }
    
    

    public void setItensTabela(List<Irisfornec> itensTabela) {
        this.itensTabela = itensTabela;
    }

    public Irisfornec getIrisfornec() {
        return irisfornec;
    }

    public void setIrisfornec(Irisfornec irisfornec) {
        this.irisfornec = irisfornec;
    }

   
    
    
    
    
}
