package br.com.iris.dao;

import br.com.iris.ConexaoBanco.dataBase;
import br.com.iris.model.Custovariavel;
import br.com.iris.model.Itc;
import br.com.iris.model.Orcamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;

public class ItcDao extends DaoImpl<Itc, Integer> {

    public List<Itc> getByOrcamento(Orcamento orcamento) {
        List<Itc> rs = null;

        try {
            Query qry = getEntityManager().createQuery("select i from Itc i where i.orcamento= :orcamento");
            qry.setParameter("orcamento", orcamento);

            rs = qry.getResultList();

        } catch (Exception e) {
        } finally {
            getEntityManager().close();
        }

        return rs;

    }

    public void excluirCustoVariavel(Itc i) throws SQLException {
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM itenscv ");
        sql.append("WHERE idcv = ? and idorc = ? and id = ? ");

        Connection conexao = null;
        try {

            conexao = dataBase.conectar();

            PreparedStatement comando = conexao.prepareStatement(sql.toString());
            comando.setInt(1, i.getCustovariavel().getId());
            comando.setInt(2, i.getOrcamento().getId());
            comando.setInt(3, i.getId());

            comando.executeUpdate();
            comando.close();
        } catch (Exception e) {
        } finally {
            conexao.close();
        }

    }
    
    
    public void excluirCusto(Custovariavel c) {
        try {
            Query qry = getEntityManager().createQuery("delete from Itc i where i.custovariavel=:custovariavel");
            qry.setParameter("custovariavel", c);
            getEntityManager().getTransaction().begin();
            qry.executeUpdate();
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
        } finally {
            getEntityManager().close();
        }

    }
    
}
