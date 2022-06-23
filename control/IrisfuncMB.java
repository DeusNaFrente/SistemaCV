/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.IrisfuncDao;
import br.com.iris.model.Irisfunc;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Iris
 */

@ManagedBean(name = "irisfuncMB")
@ViewScoped
public class IrisfuncMB implements Serializable {
    
    private Irisfunc irisf = new Irisfunc();
    private List<Irisfunc>itensTabela;
    
      private int etapa = 0;

    public Irisfunc getIrisf() {
        return irisf;
    }

    public void setIrisf(Irisfunc irisf) {
        this.irisf = irisf;
    }

    public List<Irisfunc> getItensTabela() {
         if (itensTabela == null) {
            refresh();
        }
        return itensTabela;
    }
    
    
    public void setItensTabela(List<Irisfunc> itensTabela) {
        this.itensTabela = itensTabela;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
    
    
    
     public void refresh() {
        setItensTabela(new IrisfuncDao().getAll(Irisfunc.class));
        
    }

    
    public void novo(){
        setIrisf(new Irisfunc());
        etapa=1;
    }

  public void salvar() {
        if (getIrisf() == null) {return;}
        
        if (getIrisf().getNome() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Nome é obrigatório"));
            return;
        }
        
        new IrisfuncDao().save(getIrisf());
        refresh();
        setEtapa(0);
        novo();
    }
  
  public void excluir(){
        if (getIrisf()==null){return;}
        if (getIrisf().getId()==0){return;}
        new IrisfuncDao().delete(Irisfunc.class,getIrisf().getId());
        refresh();
        setEtapa(0);
    }
  
     public void editar(){
        setEtapa(1);
    } 
    
}
