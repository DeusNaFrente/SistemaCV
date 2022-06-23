
package br.com.iris.control;

import br.com.iris.dao.EntregaDao;
import br.com.iris.dao.VeiculoDao;
import br.com.iris.model.Entrega;
import br.com.iris.model.Veiculo;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "entregaMB")
@ViewScoped
public class EntregaMB implements Serializable{
    
    private List<Entrega>entregaArray;
    private Entrega entrega = new Entrega();
    
    private List<Veiculo> arrayVeiculo;
    private Veiculo veiculo = new Veiculo();
    
     private int etapa = 0;

       public void refresh(){
        setEntregaArray(new EntregaDao().getAll(Entrega.class));
        setArrayVeiculo(new VeiculoDao().getAll(Veiculo.class));
    }
    
     
     public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
     
     

    public List<Entrega> getEntregaArray() {
        if(entregaArray == null){refresh();}
        return entregaArray;
    }

    public void setEntregaArray(List<Entrega> entregaArray) {
        this.entregaArray = entregaArray;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public List<Veiculo> getArrayVeiculo() {
        if(arrayVeiculo == null){refresh();}
        return arrayVeiculo;
    }

    public void setArrayVeiculo(List<Veiculo> arrayVeiculo) {
        this.arrayVeiculo = arrayVeiculo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
     public void novo(){
        setEntrega(new Entrega());
        etapa=1;
    }
    
    public void editar(){
        setEtapa(1);
    } 
    
    public void salvar(){
        if (getEntrega()== null){return;}
         new EntregaDao().save(getEntrega());
        refresh();
        setEtapa(0);
        novo();
        
    }
    
    public void excluir(){
        if (getEntrega()==null){return;}
        if (getEntrega().getId()==0){return;}
        new EntregaDao().delete(getEntrega());
        refresh();
        setEtapa(0);
        novo();
    }
    
}
