/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.ItemModeloDao;
import br.com.iris.model.ItemModelo;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "itemmodeloMB")
@ViewScoped
public class ItemModeloMB implements Serializabe {
    
    private List<ItemModelo> itModelo;
    private ItemModelo itemmodelo = new ItemModelo();
    
     private int etapa = 0;
    
    public void editar() {
        setEtapa(1);
    }
    
    public void refresh() {
        setItModelo(new ItemModeloDao().getAll(ItemModelo.class));
    }

    public List<ItemModelo> getItModelo() {if(itModelo == null){refresh();}
        return itModelo;
    }

    public void setItModelo(List<ItemModelo> itModelo) {
        this.itModelo = itModelo;
    }

    

    public ItemModelo getItemmodelo() {
        return itemmodelo;
    }

    public void setItemmodelo(ItemModelo itemmodelo) {
        this.itemmodelo = itemmodelo;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
    
    public void excluir() {

        if (getItemmodelo() == null) {
            return;
        }
        if (getItemmodelo().getId() == 0) {
            return;
        }

        
        new ItemModeloDao().delete(ItemModelo.class, getItemmodelo().getId()); 

        refresh();
        setEtapa(0);
        novo();
    }
    
     public void novo() {
        setItemmodelo(new ItemModelo());
        etapa = 1;
    }
    
    
}
