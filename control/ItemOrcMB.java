/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.ItemOrcDao;
import br.com.iris.model.ItemOrc;
import br.com.iris.model.Produto;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Iris
 */

@ManagedBean(name = "itemorcMB")
@ViewScoped
public class ItemOrcMB implements Serializable {
    
    List<ItemOrc>orcamentoItem;
    Produto produto = new Produto();

    public List<ItemOrc> getOrcamentoItem() {
        return orcamentoItem;
    }

    public void setOrcamentoItem(List<ItemOrc> orcamentoItem) {
        this.orcamentoItem = orcamentoItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    public void refresh(){
        setOrcamentoItem(new ItemOrcDao().getAll(ItemOrc.class));
    }
    
    
    
}
