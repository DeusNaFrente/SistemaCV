/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.dao;

import br.com.iris.model.Irisitem;
import br.com.iris.model.Irisvendas;
import java.util.List;
import javax.persistence.Query;


/**
 *
 * @author Iris
 */
public class IrisitemDao extends DaoImpl<Irisitem,Integer> {
    public List<Irisitem> getItens(Irisvendas carrinho){        
        if (carrinho.getId()==0){return null;}
        List<Irisitem>itens=null;
        try{
            Query qry=getEntityManager().createQuery("select o from Irisitem o where o.irisVendas=:venda");
            qry.setParameter("venda", carrinho);
            itens=qry.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            getEntityManager().close();
        }
        return itens;
        
    }
    
}
