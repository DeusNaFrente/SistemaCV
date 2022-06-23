package br.com.iris.control;

import br.com.iris.dao.ColaboradorDao;
import br.com.iris.dao.ItemLogColaboradorDao;
import br.com.iris.dao.LogequipeDao;
import br.com.iris.model.Colaborador;
import br.com.iris.model.ItemLogColaborador;
import br.com.iris.model.Logequipe;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Iris
 */
@ManagedBean(name = "logequipeMB")
@ViewScoped
public class LogequipeMB implements Serializable {

    @ManagedProperty(value = "#{colaboradorMB}")
    private ColaboradorMB colaboradorMB;

    public ColaboradorMB getColaboradorMB() {
        return colaboradorMB;
    }

    public void setColaboradorMB(ColaboradorMB colaboradorMB) {
        this.colaboradorMB = colaboradorMB;
    }

    private List<Logequipe> listaEquipe;
    private Logequipe logequipe = new Logequipe();

    private List<Colaborador> listaColaborador;
    private Colaborador colaborador = new Colaborador();

    private List<ItemLogColaborador> itemColaborador;
    private ItemLogColaborador itemLogColaborador = new ItemLogColaborador();
    private List<ItemLogColaborador> itemlogcolaborador;

    public void carregar() {

        //criar uma select q mostra os colaboradores da equipe
    }

    public void refresh() {
        setListaEquipe(new LogequipeDao().getAll(Logequipe.class));
        setListaColaborador(new ColaboradorDao().getAll(Colaborador.class));
        setItemlogcolaborador(new ItemLogColaboradorDao().getAll(ItemLogColaborador.class)); //esta linha atualiza a Tabela Time dentro do dialog
        itemColaborador = new ArrayList<>();//zerar a tabela (Agregar ao trabalho)

    }

    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Logequipe> getListaEquipe() {
        if (listaEquipe == null) {
            refresh();
        }
        return listaEquipe;
    }

    public void setListaEquipe(List<Logequipe> listaEquipe) {
        this.listaEquipe = listaEquipe;
    }

    public Logequipe getLogequipe() {
        return logequipe;
    }

    public void setLogequipe(Logequipe logequipe) {
        this.logequipe = logequipe;
    }

    public List<Colaborador> getListaColaborador() {
        if (listaColaborador == null) {
            refresh();
        }
        return listaColaborador;
    }

    public void setListaColaborador(List<Colaborador> listaColaborador) {
        this.listaColaborador = listaColaborador;
    }

    public List<ItemLogColaborador> getItemlogcolaborador() {
        if (itemlogcolaborador == null) {
            refresh();
        }
        return itemlogcolaborador;
    }

    public void setItemlogcolaborador(List<ItemLogColaborador> itemlogcolaborador) {
        this.itemlogcolaborador = itemlogcolaborador;
    }

    public List<ItemLogColaborador> getItemColaborador() {
        if (itemColaborador == null) {
            refresh();
        }
        return itemColaborador;
    }

    public void setItemColaborador(List<ItemLogColaborador> itemColaborador) {
        this.itemColaborador = itemColaborador;
    }

    public ItemLogColaborador getItemLogColaborador() {
        return itemLogColaborador;
    }

    public void setItemLogColaborador(ItemLogColaborador itemLogColaborador) {
        this.itemLogColaborador = itemLogColaborador;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

//*****************************MÉTODOS**************************************
    public void listarColaboradores() { //preenche a tabela Colaboradores deste Time

        try {

            LogequipeDao fdao = new LogequipeDao();
            itemColaborador = fdao.listarColaborador();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void novo() {
        setLogequipe(new Logequipe());
        etapa = 1;
    }

    public void adicionar(Colaborador colab) throws SQLException {

        List<ItemLogColaborador> itens = getLogequipe().getItens();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
            ItemLogColaborador itemTemp = itens.get(pos);
            if (itemTemp.getColaborador().equals(colab)) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        ItemLogColaborador itemcol = new ItemLogColaborador();
        itemcol.setColaborador(colab);

        if (posicaoEncontrada < 0) {

            if (getLogequipe().getNome() == null || getLogequipe().getNome().isEmpty()) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione uma Equipe."));
                return;
            }

            itemcol.setQtd(1);
            itemcol.setValor_parcial(colab.getValorhsutil());
            itens.add(itemcol);

        } else if (itemcol.getColaborador().equals(colab)) {
            ItemLogColaborador itemTemp = itens.get(posicaoEncontrada);
            itemcol.setQtd(itemTemp.getQtd() + 1);
            itens.set(posicaoEncontrada, itemcol);
            itemcol.setValor_parcial(colab.getValorhsutil() * itemcol.getQtd());

        }

        logequipe.setValor(logequipe.getValor() + colab.getValorhsutil());
        refresh();
    }
    //***************************************Remover Colaborador da Tabela Agregar Trabalho *******************

    public void remover(ItemLogColaborador item) {

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itemColaborador.size() && posicaoEncontrada < 0; pos++) {
            ItemLogColaborador itemTemp = itemColaborador.get(pos);

            if (itemTemp.getColaborador().equals(item.getColaborador())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            itemColaborador.remove(posicaoEncontrada);

            logequipe.setValor(logequipe.getValor() - item.getValor_parcial());

        }

    }

//***************************************Fim********************************************************************
//******************************Remover Colaborador da tabela de Time*******************************************
    public void removerItemColaborador(ItemLogColaborador colab) throws SQLException {

        List<ItemLogColaborador> itensColab = getLogequipe().getItens();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensColab.size() && posicaoEncontrada < 0; pos++) {
            ItemLogColaborador itemTemp = itensColab.get(pos);

            if (itemTemp.getColaborador().equals(colab.getColaborador())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {

            colab.setQtd(colab.getQtd() - 1);

            if (colab.getQtd() == 0) {
                itensColab.remove(posicaoEncontrada);//calculo errado em logequipe
                //  colab.setValor_parcial(colab.getValor_parcial() * colab.getQtd());
            }

            if (colab.getId() > 0) {

                ItemLogColaboradorDao dao = new ItemLogColaboradorDao();

                dao.excluirColaborador(colab);

            }

            if (getLogequipe().getItens() == null) {
                return;
            }

            logequipe.setValor(logequipe.getValor() - colab.getValor_parcial());

        }

    }

    public void removerColaborador(ItemLogColaborador time) throws SQLException {

        List<ItemLogColaborador> itens = getLogequipe().getItens();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
            ItemLogColaborador itemTemp = itens.get(pos);

            if (itemTemp.getColaborador().equals(time.getColaborador())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            itens.remove(posicaoEncontrada);

            logequipe.setValor(logequipe.getValor() - time.getValor_parcial());

        }

    }

//******************************Fim**************************************************
    //***************************************Excluir a Equipe Toda *******************
    public void excluir() {
        if (getLogequipe() == null) {
            return;
        }
        if (getLogequipe().getId() == 0) {
            return;
        }
        new LogequipeDao().delete(getLogequipe());
        refresh();
        setEtapa(0);
        novo();
    }

    //***************************************Excluir Fim *******************
    public void salvar() throws SQLException {

        List<ItemLogColaborador> itens = getLogequipe().getItens();

        if (getLogequipe() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione uma Equipe."));
            return;
        }

        if (getLogequipe().getNome().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Forneça um nome de equipe."));
            return;
        }

        if (itens.size() <= 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Adicione algum colaborador."));
            return;
        }

        setLogequipe(new LogequipeDao().save(getLogequipe()));

        for (ItemLogColaborador i : itens) {

            if (i.getId() >= 0) {
          

                i.setLogequipe(getLogequipe());
                new ItemLogColaboradorDao().save(i);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Equipe!", "Inclua itens nesta equipe."));
                return;
            }

        
        }

        setLogequipe(new LogequipeDao().save(getLogequipe()));
        refresh();
        setEtapa(0);
        novo();

    }

    public void salvando() throws SQLException {

         List<ItemLogColaborador> itens = getLogequipe().getItens();

        if (getLogequipe() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione uma Equipe."));
            return;
        }

        if (getLogequipe().getNome().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Forneça um nome de equipe."));
            return;
        }

        if (itens.size() <= 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Adicione algum colaborador."));
            return;
        }

        setLogequipe(new LogequipeDao().save(getLogequipe()));

        for (ItemLogColaborador h : itens) {

             if (h.getQtd() <= 1) {

                h.setLogequipe(getLogequipe());
                new ItemLogColaboradorDao().save(h);

            } else {
                h.setLogequipe(getLogequipe());
                new ItemLogColaboradorDao().atualizarItens(h);

            }
        }

        setLogequipe(new LogequipeDao().save(getLogequipe()));
        refresh();
        setEtapa(0);
       

    }

    public void editar() {
        setEtapa(1);
    }

    //***********************************************************FIM 
}
