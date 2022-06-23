/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;


import br.com.iris.dao.ItcDao;
import br.com.iris.model.Itc;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "itemcvMB")
@ViewScoped
public class ItcMB implements Serializable {

    public void refresh() {

        setArrayItemCustoVariavel(new ItcDao().getAll(Itc.class));

    }

    //**************************************Get/Set - Etapa
    private int etapa = 0;
    
    public void editar() {
        setEtapa(1);
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }


    
    private List<Itc> arrayItemCustoVariavel;
    private Itc stringItemCustoVariavel = new Itc();

    public List<Itc> getArrayItemCustoVariavel() {
        if(arrayItemCustoVariavel == null){refresh();}
        return arrayItemCustoVariavel;
    }

    public void setArrayItemCustoVariavel(List<Itc> arrayItemCustoVariavel) {
        this.arrayItemCustoVariavel = arrayItemCustoVariavel;
    }

    public Itc getStringItemCustoVariavel() {
        return stringItemCustoVariavel;
    }

    public void setStringItemCustoVariavel(Itc stringItemCustoVariavel) {
        this.stringItemCustoVariavel = stringItemCustoVariavel;
    }

    

    

//****************************************FIM************************************************************ 
//****************************************MÉTODOS********************************************************     
    
    
    
    public void novo() {
        setStringItemCustoVariavel(new Itc());
        etapa = 1;
    }
    
    
    public void excluirItemColaborador() {

        if (getStringItemCustoVariavel() == null) {
            return;
        }
        if (getStringItemCustoVariavel().getId() == 0) {
            return;
        }

        new ItcDao().delete(Itc.class, getStringItemCustoVariavel().getId()); 

        refresh();
        setEtapa(0);
        novo();
    }
    
    
  public void salvar(){
        if (getStringItemCustoVariavel()==null){return;}
      
        new ItcDao().save(getStringItemCustoVariavel());
        refresh();
        setEtapa(0);
    }
  
  public void excluir(){
        if (getStringItemCustoVariavel()==null){return;}
        if (getStringItemCustoVariavel().getId()==0){return;}
        new ItcDao().delete(Itc.class,getStringItemCustoVariavel().getId());
        refresh();
        setEtapa(0);
    }
    

//***************************************Fim **********************************************************        
}
