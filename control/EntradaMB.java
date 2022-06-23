/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.model.Entrada;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import br.com.iris.dao.EntradaDao;

/**
 *
 * @author Iris
 */

@ManagedBean(name = "entradaMB")
@ViewScoped
public class EntradaMB implements Serializable{
    
    private List<Entrada>entradas;
    private Entrada entrada = new Entrada();
    private int etapa = 0;

    public List<Entrada> getEntradas() {if(entradas == null){refresh();}
        return entradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        this.entradas = entradas;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }
    
    public void refresh(){
    
    setEntradas(new EntradaDao().getAll(Entrada.class)); 
    
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
    
    
    public void novo(){
    setEntrada(new Entrada());
    etapa = 1;
    }
    
    
    public void salvar(){
        if (getEntrada()==null){return;}
        
        if (getEntrada().getDia() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Data é obrigatório."));
            return;
        }
        
         if (getEntrada().getDescr().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Descrição é obrigatório"));
            return;
        }
         
         if (getEntrada().getValor() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Valor é obrigatório"));
            return;
        }
       
        new EntradaDao().save(getEntrada());
        refresh();
        setEtapa(0);
        novo();
    }
    
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getEntrada()==null){return;}
        if (getEntrada().getId()==0){return;}
        new EntradaDao().delete(Entrada.class,getEntrada().getId());
        refresh();
        setEtapa(0);
    }
    
}
