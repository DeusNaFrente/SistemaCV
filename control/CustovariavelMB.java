/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.CustovariavelDao;
import br.com.iris.dao.FornecedorDao;
import br.com.iris.model.Custovariavel;
import br.com.iris.model.Fornecedor;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

 
@ManagedBean(name = "custovariavelMB")
@ViewScoped
public class CustovariavelMB implements Serializable {
    
    private List<Custovariavel> custoV;
    private Custovariavel custovariavel = new Custovariavel();
    private List<Fornecedor> arrayFornecedor;
    
     private int etapa = 0;
    
    
     public void refresh(){
        setCustoV(new CustovariavelDao().getAll(Custovariavel.class));
        setArrayFornecedor(new FornecedorDao().getAll(Fornecedor.class));
    }

    public List<Custovariavel> getCustoV() {
         if(custoV == null){refresh();}
         return custoV;
    }

    public void setCustoV(List<Custovariavel> custoV) {
        this.custoV = custoV;
    }
     
   
    public Custovariavel getCustovariavel() {
        return custovariavel;
    }

    public void setCustovariavel(Custovariavel custovariavel) {
        this.custovariavel = custovariavel;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Fornecedor> getArrayFornecedor() {
        if(arrayFornecedor==null){refresh();}
        return arrayFornecedor;
    }

    public void setArrayFornecedor(List<Fornecedor> arrayFornecedor) {
        this.arrayFornecedor = arrayFornecedor;
    }
    
    
    
    
    public void salvar() {
        if (getCustovariavel() == null) {
            return;
        }

        if (getCustovariavel().getDescricao() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Nome do contato é obrigatório"));
            return;
        }
        
     
        
        new CustovariavelDao().save(getCustovariavel());
        refresh();
        setEtapa(0);
        novo();

    }
    
     public void novo() {
        setCustovariavel(new Custovariavel());
        etapa = 1;
    }
     
     public void editar() {
        setEtapa(1);
    }
     
     
     public void excluir() {
        if (getCustovariavel() == null) {
            return;
        }
        if (getCustovariavel().getId() == 0) {
            return;
        }
         new CustovariavelDao().delete(getCustovariavel());
        setEtapa(0);
        refresh();
        novo();
    }
    
    
}
