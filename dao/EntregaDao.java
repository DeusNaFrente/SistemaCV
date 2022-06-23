
package br.com.iris.dao;
import br.com.iris.model.Entrega;




public class EntregaDao extends DaoImpl<Entrega,Integer>{
    
    public void delete(Entrega en){
        new ItemEntregaDao().excluirEntrega(en);
        
        
        this.delete(Entrega.class, en.getId());
    }
    
}
