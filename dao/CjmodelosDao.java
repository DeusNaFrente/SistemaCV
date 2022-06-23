/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.dao;

import br.com.iris.model.Cjmodelos;


/**
 *
 * @author Iris
 */
public class CjmodelosDao extends DaoImpl<Cjmodelos,Integer>{
    
     public void delete(Cjmodelos cj){
        new ItemModeloDao().excluirM(cj);
        
        
        this.delete(Cjmodelos.class, cj.getId());
    }
    
     
    
    
}
