package br.com.iris.dao;


import br.com.iris.model.Comissionado;



public class ComissionadoDao extends DaoImpl<Comissionado,Integer>{
    
        
    public void delete(Comissionado c){
        new ItemComissionadoDao().excluirComissionado(c);
        
        
        this.delete(Comissionado.class, c.getId());
    } 
    
}
