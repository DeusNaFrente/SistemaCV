/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.ItemLogColaboradorDao;
import br.com.iris.model.ItemLogColaborador;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "itemlogcolaboradorMB")
@ViewScoped

public class ItemLogColaboradorMB implements Serializable {

    public void refresh() {

        setArrayItemColaborador(new ItemLogColaboradorDao().getAll(ItemLogColaborador.class));

    }

    //**************************************Get/Set - Etapa
    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

//****************************************FIM*********************************************************************
//****************************************lISTA DE COLABORADOR - get/set******************************************
    private List<ItemLogColaborador> ArrayItemColaborador;
    private ItemLogColaborador stringItemColaborador = new ItemLogColaborador();

    public List<ItemLogColaborador> getArrayItemColaborador() {
        if(ArrayItemColaborador == null){refresh();}
        return ArrayItemColaborador;
    }

    public void setArrayItemColaborador(List<ItemLogColaborador> ArrayItemColaborador) {
        this.ArrayItemColaborador = ArrayItemColaborador;
    }

    public ItemLogColaborador getStringItemColaborador() {
        return stringItemColaborador;
    }

    public void setStringItemColaborador(ItemLogColaborador stringItemColaborador) {
        this.stringItemColaborador = stringItemColaborador;
    }

    

//****************************************FIM************************************************************ 
//****************************************MÉTODOS********************************************************     
    
    
    
    public void novo() {
        setStringItemColaborador(new ItemLogColaborador());
        etapa = 1;
    }
    
    public void editar() {
        setEtapa(1);
    }

    public void excluirItemColaborador() {

        if (getStringItemColaborador() == null) {
            return;
        }
        if (getStringItemColaborador().getId() == 0) {
            return;
        }

        new ItemLogColaboradorDao().delete(ItemLogColaborador.class, getStringItemColaborador().getId()); 

        refresh();
        setEtapa(0);
        novo();
    }
    
    
  
    

//***************************************Fim **********************************************************        
}
