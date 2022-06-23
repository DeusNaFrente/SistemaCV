/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.IrisitemDao;
import br.com.iris.dao.IrisprodDao;
import br.com.iris.dao.IrisvendasDao;
import br.com.iris.model.Irisitem;
import br.com.iris.model.Irisprod;
import br.com.iris.model.Irisvendas;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Iris
 */
@ManagedBean(name = "irisitemMB")
@ViewScoped
public class IrisitemMB implements Serializable {

    private Irisitem irisI = new Irisitem();
    private List<Irisitem> itensTabela;

    private Irisvendas irisV = new Irisvendas();
    private List<Irisvendas> irisVitens;
    private Irisprod irisp = new Irisprod();
    private List<Irisprod> irisPitens;

    private int etapa = 0;
    
    public void refresh() {
        setItensTabela(new IrisitemDao().getAll(Irisitem.class));
        setIrisVitens(new IrisvendasDao().getAll(Irisvendas.class));
        setIrisPitens(new IrisprodDao().getAll(Irisprod.class));
    }

    public Irisitem getIrisI() {
        return irisI;
    }

    public void setIrisI(Irisitem irisI) {
        this.irisI = irisI;
    }

    public List<Irisitem> getItensTabela() {
        if (itensTabela == null) {
            refresh();
        }
        return itensTabela;
    }

    public void setItensTabela(List<Irisitem> itensTabela) {
        this.itensTabela = itensTabela;
    }

    public Irisvendas getIrisV() {
        return irisV;
    }

    public void setIrisV(Irisvendas irisV) {
        this.irisV = irisV;
    }

    public List<Irisvendas> getIrisVitens() {
         if (irisVitens == null) {
            refresh();
        }
        return irisVitens;
    }

    public void setIrisVitens(List<Irisvendas> irisVitens) {
        this.irisVitens = irisVitens;
    }

    public Irisprod getIrisp() {
        return irisp;
    }

    public void setIrisp(Irisprod irisp) {
        this.irisp = irisp;
    }

    public List<Irisprod> getIrisPitens() {
         if (irisPitens == null) {
            refresh();
        }
        return irisPitens;
    }

    public void setIrisPitens(List<Irisprod> irisPitens) {
        this.irisPitens = irisPitens;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

}
