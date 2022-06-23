/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;
import br.com.iris.dao.EntradaopDao;
import br.com.iris.dao.ItemopDao;
import br.com.iris.model.Entradaop;
import br.com.iris.model.Itemop;
import br.com.iris.model.Orcamento;
import java.sql.SQLException;

/**
 *
 * @author Iris
 */
@ManagedBean(name = "entraopMB")
@ViewScoped
public class EntradaopMB implements Serializable {

    private List<Entradaop> entradasop;
    private Entradaop entradaop = new Entradaop();

    private List<Itemop> itemopList;
    private Itemop itemop = new Itemop();

    private int etapa = 0;

    private Orcamento orcs = new Orcamento();

    public void refresh() {

        setEntradasop(new EntradaopDao().getAll(Entradaop.class));
        setItemopList(new ItemopDao().getAll(Itemop.class));

    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public void novo() {
        setEntradaop(new Entradaop());
        etapa = 1;
    }

    public Entradaop getEntradaop() {
        return entradaop;
    }

    public void setEntradaop(Entradaop entradaop) {
        this.entradaop = entradaop;
    }

    public List<Entradaop> getEntradasop() {
        if (entradasop == null) {
            refresh();
        }
        return entradasop;
    }

    public void setEntradasop(List<Entradaop> entradasop) {
        this.entradasop = entradasop;
    }

    public List<Itemop> getItemopList() {
        if (itemopList == null) {
            refresh();
        }
        return itemopList;
    }

    public void setItemopList(List<Itemop> itemopList) {
        this.itemopList = itemopList;
    }

    public Itemop getItemop() {
        return itemop;
    }

    public void setItemop(Itemop itemop) {
        this.itemop = itemop;
    }

    public Orcamento getOrcs() {
        return orcs;
    }

    public void setOrcs(Orcamento orcs) {
        this.orcs = orcs;
    }

    public void salvar() {
        if (getEntradaop() == null) {
            return;
        }

        if (getEntradaop().getDia() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Data é obrigatório."));
            return;
        }

        if (getEntradaop().getDescr().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Descrição é obrigatório"));
            return;
        }

        if (getEntradaop().getValor() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Valor é obrigatório"));
            return;
        }

        new EntradaopDao().save(getEntradaop());
        refresh();
        setEtapa(0);
        novo();
    }

    public void save() throws SQLException {

        List<Itemop> itensOp = getEntradaop().getItensOp();

        if (getEntradaop() == null) {
            return;
        }

        if (getEntradaop().getCliente() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Abra ou crie uma empresa."));
            return;
        }

        if (getEntradaop().getDia() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Data é obrigatório."));
            return;
        }

        if (getEntradaop().getDescr().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Descrição é obrigatório"));
            return;
        }

        //  if (getEntradaop().getValor() == 0) {
        //    PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Valor é obrigatório"));
        //  return;
        //}
        if (getEntradaop().getItensOp().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Insira os orçamentos"));
            return;
        }

        if (getEntradaop().getId() == 0) {

            setEntradaop(new EntradaopDao().save(getEntradaop()));

            for (Itemop it : itensOp) {

                if (it.getId() == 0) {

                    it.setEntradaop(getEntradaop());
                    new ItemopDao().save(it);

                } else if (itensOp.size() >= 0) {

                    if (it.getOrcamento().getNomeorc().equals(it.getOrcamento().getNomeorc())) {
                        it.setQtd(it.getQtd());
                        new ItemopDao().atualizarItensOp(it);
                        return;

                    } else {
                        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição!", "Inclua os modelos para compor o Produto."));
                        return;
                    }
                }

            }

        } else {

            for (Itemop i : itensOp) {

                Itemop itemoper = new Itemop();
                itemoper.setOrcamento(orcs);

                if (i.getOrcamento().getId() == (i.getOrcamento().getId()) && i.getQtd() >= 1 && i.getId() > 0) {

                    i.setQtd(i.getQtd());
                    i.setEntradaop(getEntradaop());

                    new ItemopDao().atualizarItensOp(i);

                } else if (i.getId() == 0 && i.getQtd() == 1) {

                    i.setEntradaop(getEntradaop());
                    new ItemopDao().save(i);
                    setEntradaop(new EntradaopDao().save(getEntradaop()));
                    refresh();
                    novo();
                    return;

                } else {

                    i.setQtd(i.getQtd());
                    i.setEntradaop(getEntradaop());

                    new ItemopDao().atualizarItensOp(i);

                }
            }

        }

        setEntradaop(new EntradaopDao().save(getEntradaop()));
        refresh();
        setEtapa(0);
        novo();

    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getEntradaop() == null) {
            return;
        }
        if (getEntradaop().getId() == 0) {
            return;
        }
        new EntradaopDao().delete(Entradaop.class, getEntradaop().getId());
        refresh();
        setEtapa(0);
        novo();
    }

    public void adicionarOrc(Orcamento orcamento) throws SQLException {

        List<Itemop> itensOp = getEntradaop().getItensOp();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensOp.size() && posicaoEncontrada < 0; pos++) {
            Itemop itemTemp = itensOp.get(pos);
            if (itemTemp.getOrcamento().equals(orcamento)) {
                posicaoEncontrada = pos;
            }
        }

        Itemop itemope = new Itemop();
        itemope.setOrcamento(orcamento);

        if (posicaoEncontrada < 0) {

            if (getEntradaop().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "O campo esta vazio!", "Crie ou abra uma empresa."));
                return;
            }

            itemope.setQtd(1);
            itemope.setValor(orcamento.getValor());
            itensOp.add(itemope);

        } else if (itemope.getOrcamento().getNomeorc().equals(orcamento.getNomeorc())) {
            Itemop itemTemp = itensOp.get(posicaoEncontrada);
            itemope.setQtd(itemTemp.getQtd() + 1);

            itensOp.set(posicaoEncontrada, itemope);
            itemope.setValor(orcamento.getValor() * itemope.getQtd());

        }
        entradaop.setValor(entradaop.getValor() + orcamento.getValor());

        refresh();

        //  saveItem();
    }

    public void removerOrc(Itemop itemop) throws SQLException {

        List<Itemop> itensOp = getEntradaop().getItensOp();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensOp.size() && posicaoEncontrada < 0; pos++) {
            Itemop itemTemp = itensOp.get(pos);

            if (itemTemp.getOrcamento().equals(itemop.getOrcamento())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {

            itemop.setQtd(itemop.getQtd() - 1);
            entradaop.setValor(entradaop.getValor() - itemop.getValor());
            
            if (itemop.getQtd() == 0) {
                itensOp.remove(posicaoEncontrada);
                

                if (itemop.getId() > 0) {

                    ItemopDao dao = new ItemopDao();

                    dao.excluirOp(itemop);
                    entradaop.setValor(entradaop.getValor() - itemop.getValor());

                    if (getEntradaop().getItensOp().isEmpty()) {
                        excluir();
                        refresh();
                        novo();
                        
                    }
                    
                }

            }

            if (getEntradaop().getItensOp() == null) {
                return;
            }

            entradaop.setValor(entradaop.getValor() - itemop.getValor());

        }

    }

    public void saveItem() throws SQLException {

        List<Itemop> itensOp = getEntradaop().getItensOp();

        for (Itemop y : itensOp) {

            if (y.getQtd() > 1) {

                y.setQtd(y.getQtd());
                y.setEntradaop(getEntradaop());
                new ItemopDao().atualizarItensOp(y);

            }

        }

    }

}
