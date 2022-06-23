/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.CustoprodDao;
import br.com.iris.model.Custoprod;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "custoprodMB")
@ViewScoped
public class CustoprodMB implements Serializable {

    private List<Custoprod> custos;
    private Custoprod custo = new Custoprod();
    private int etapa = 0;
    
    public void refresh() {
        setCustos(new CustoprodDao().getAll(Custoprod.class));
    }

    public List<Custoprod> getCustos() {if (custos == null){refresh();}
        return custos;
    }

    public void setCustos(List<Custoprod> custos) {
        this.custos = custos;
    }

    public Custoprod getCusto() {
        return custo;
    }

    public void setCusto(Custoprod custo) {
        this.custo = custo;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
    
    public void novo() {
        setCusto(new Custoprod());
        etapa = 1;
    }
    
    public void editar() {
        setEtapa(1);
    }
    
    public void excluir() {
        if (getCusto() == null) {
            return;
        }
        if (getCusto().getId() == 0) {
            return;
        }
        new CustoprodDao().delete(Custoprod.class,
                getCusto().getId());
        refresh();
        setEtapa(0);
        novo();
    }
    
    public void salvar() {
        if (getCusto() == null) {
            return;
        }

        if (getCusto().getTinta()== null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Primeiro campo esta em branco."));
            return;
        }
        new CustoprodDao().save(getCusto());
        refresh();
        setEtapa(0);
        novo();

    }

}
