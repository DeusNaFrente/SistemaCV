/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.dao;

import br.com.iris.ConexaoBanco.dataBase;
import br.com.iris.model.ItemAcab;
import br.com.iris.model.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Iris
 */
public class ItemAcabDao extends DaoImpl<ItemAcab, Integer> {

    public List<ItemAcab> getByModelo(Modelo modelo) {
        List<ItemAcab> rs = null;

        try {
            Query qry = getEntityManager().createQuery("select i from ItemAcab i where i.modelo= :modelo");
            qry.setParameter("modelo", modelo);

            rs = qry.getResultList();

        } catch (Exception e) {
        } finally {
            getEntityManager().close();
        }

        return rs;

    }

    public void excluirItemAcab(ItemAcab i) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM itemacab ");
        sql.append("WHERE idmodelo = ? and idacab = ? and id = ? ");

        Connection conexao = null;
        try {
            conexao = dataBase.conectar();

            PreparedStatement comando = conexao.prepareStatement(sql.toString());
            comando.setInt(1, i.getModelo().getId());
            comando.setInt(2, i.getAcabamento().getId());
            comando.setInt(3, i.getId());

            comando.executeUpdate();
            comando.close();
        } catch (Exception e) {
        } finally {
            conexao.close();
        }

    }
}
