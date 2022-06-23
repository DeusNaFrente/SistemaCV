/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.dao;

import br.com.iris.ConexaoBanco.dataBase;
import br.com.iris.model.Itemcjm;
import br.com.iris.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Iris
 */
public class ItemcjmDao extends DaoImpl<Itemcjm,Integer>{
    
    
    public List<Itemcjm> getByProduct(Product product) {
           List<Itemcjm>rs = null;

        try {
            Query qry = getEntityManager().createQuery("select i from Itemcjm i where i.product= :product");
            qry.setParameter("product", product);

            rs = qry.getResultList();

        } catch (Exception e) {
        } finally {
            getEntityManager().close();
        }

        return rs;

    } 
    
    
    public void excluirCj(Itemcjm i) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM itemcjm ");
        sql.append("WHERE idcjmodelo = ? and idprod = ? and id = ? ");

        Connection conexao = null;
        try {
            conexao = dataBase.conectar();

            PreparedStatement comando = conexao.prepareStatement(sql.toString());
            comando.setInt(1, i.getCjmodelos().getId());
            comando.setInt(2, i.getProduct().getId());
            comando.setInt(3, i.getId());

            comando.executeUpdate();
            comando.close();
        } catch (Exception e) {
        } finally {
            conexao.close();
        }

    }
    
    public void atualizarItensMo(Itemcjm f) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE itemcjm ");
        sql.append("SET qtd =  ? ");
        sql.append("WHERE idcjmodelo = ? and idprod = ? ");
        
        
        Connection conexao =null;
        try{
            conexao = dataBase.conectar();

        PreparedStatement comando = conexao.prepareStatement(sql.toString());
        comando.setInt(1, f.getQtd());
        comando.setInt(2, f.getCjmodelos().getId());//atualizar o conjunto
        comando.setInt(3, f.getProduct().getId()); //nao esta pegando o id o itemcj
        comando.executeUpdate();
        comando.close();
        }catch(Exception e){
        }finally{
            conexao.close();
        }

    }
    
    
}
