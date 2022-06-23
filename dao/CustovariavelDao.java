/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.dao;

import br.com.iris.model.Custovariavel;

/**
 *
 * @author Iris
 */

public class CustovariavelDao extends DaoImpl<Custovariavel,Integer>{
    
    
    public void delete(Custovariavel c){
        new ItcDao().excluirCusto(c);
        
        
        this.delete(Custovariavel.class, c.getId());
    }
    
    
}
