/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.ItemPecasDao;
import br.com.iris.dao.ItemTecnicoDao;
import br.com.iris.dao.ManutencaoDao;
import br.com.iris.dao.PecasDao;
import br.com.iris.dao.TecnicoDao;
import br.com.iris.model.ItemPecas;
import br.com.iris.model.ItemTecnico;
import br.com.iris.model.Manutencao;
import br.com.iris.model.Pecas;
import br.com.iris.model.Tecnico;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "manutencaoMB")
@ViewScoped
public class ManutencaoMB implements Serializable {
    @ManagedProperty(value = "#{tecnicoMB}")
    private TecnicoMB tecnicoMB;
    public TecnicoMB getTecnicoMB() {return tecnicoMB;}
    public void setTecnicoMB(TecnicoMB tecnicoMB) {this.tecnicoMB = tecnicoMB;}
    

    private List<Manutencao> mans;
    private Manutencao manutencao = new Manutencao();
    private int etapa = 0;

    private List<Pecas> pecas;
    private Pecas peca = new Pecas();

    private List<ItemPecas> itemPc;
    private ItemPecas itempecas = new ItemPecas();

    private List<Tecnico> tecnicos;
    private Tecnico tecnico = new Tecnico();

    private List<ItemTecnico> itemTc;
    private ItemTecnico itemtecnico = new ItemTecnico();

    public List<Tecnico> getTecnicos() {
        if (tecnicos == null) {
            refresh();
        }
        return tecnicos;
    }

    public void setTecnicos(List<Tecnico> tecnicos) {
        this.tecnicos = tecnicos;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public List<ItemTecnico> getItemTc() {
        if (itemTc == null) {
            refresh();
        }
        return itemTc;
    }

    public void setItemTc(List<ItemTecnico> itemTc) {
        this.itemTc = itemTc;
    }

    public ItemTecnico getItemtecnico() {
        return itemtecnico;
    }

    public void setItemtecnico(ItemTecnico itemtecnico) {
        this.itemtecnico = itemtecnico;
    }

    public List<Manutencao> getMans() {
        if (mans == null) {
            refresh();
        }
        return mans;
    }

    public void setMans(List<Manutencao> mans) {
        this.mans = mans;
    }

    public Manutencao getManutencao() {
        return manutencao;
    }

    public void setManutencao(Manutencao manutencao) {
        this.manutencao = manutencao;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public ItemPecas getItempecas() {
        return itempecas;
    }

    public void setItempecas(ItemPecas itempecas) {
        this.itempecas = itempecas;
    }

    public List<ItemPecas> getItemPc() {
        if (itemPc == null) {
            refresh();
        }
        return itemPc;
    }

    public void setItemPc(List<ItemPecas> itemPc) {
        this.itemPc = itemPc;
    }

    public List<Pecas> getPecas() {
        if (pecas == null) {
            refresh();
        }
        return pecas;
    }

    public void setPecas(List<Pecas> pecas) {
        this.pecas = pecas;
    }

    public Pecas getPeca() {
        return peca;
    }

    public void setPeca(Pecas peca) {
        this.peca = peca;
    }

    public void refresh() {
        setMans(new ManutencaoDao().getAll(Manutencao.class));
        setPecas(new PecasDao().getAll(Pecas.class));
        setTecnicos(new TecnicoDao().getAll(Tecnico.class));
        itemPc = new ArrayList<>();
        itemTc = new ArrayList<>();

    }

    public void novo() {
        setManutencao(new Manutencao());
        etapa = 1;
    }

    public void editar() {
        setEtapa(1);
    }

    public void salvar() {

        List<ItemPecas> itensPecas = getManutencao().getItensPecas();
        List<ItemTecnico> itensTec = getManutencao().getItensTec();

        if (getManutencao().getServexec() == null || getManutencao().getDatavisita() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Entre com os dados de serviço e a data da visita técnica."));
            return;
        }
        
                  
         if (getManutencao().getItensTec().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Adicione técnicos."));
            return;
        }
         
       
        

        for (ItemPecas v : itensPecas) {

            if (v.getId() >= 0) {

                v.setManutencao(getManutencao());
                new ItemPecasDao().save(v);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de peças!", "Inclua os peças corretamente."));
                return;
            }

        }

        for (ItemTecnico w : itensTec) {

            if (w.getId() >= 0) {

                w.setManutencao(getManutencao());
                new ItemTecnicoDao().save(w);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de custo!", "Inclua os técnicos corretamente."));
                return;
            }

        }

        setManutencao(new ManutencaoDao().save(getManutencao()));
        refresh();
        setEtapa(0);
        novo();

    }

    public void excluir() {
        
        List<ItemTecnico> itensTec = getManutencao().getItensTec();

        if (getManutencao() == null) {
            return;
        }
        if (getManutencao().getId() == 0) {
            return;
        }
        
      
        
        new ManutencaoDao().delete(Manutencao.class, getManutencao().getId());
        refresh();
        setEtapa(0);
        novo();

    }

    public void adicionarPeca(Pecas pecas) throws SQLException {

        List<ItemPecas> itensPecas = getManutencao().getItensPecas();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensPecas.size() && posicaoEncontrada < 0; pos++) {
            ItemPecas itemTemp = itensPecas.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getPecas().equals(pecas)) {
                posicaoEncontrada = pos;
            }
        }

        ItemPecas itemp = new ItemPecas();
        itemp.setPecas(pecas);

        if (posicaoEncontrada < 0) {

            if (getManutencao() == null || getManutencao().getServexec().isEmpty()) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nome de serviço não encontrado!", "Entre com o nome do serviço."));
                return;

            }

            itemp.setQtd(1);
            itemp.setValor(pecas.getValor());
            itensPecas.add(itemp);

        } else {

            ItemPecas itemTemp = itensPecas.get(posicaoEncontrada);
            itemp.setQtd(itemTemp.getQtd() + 1);
            itemp.setValor(pecas.getValor() * itemp.getQtd());
            itensPecas.set(posicaoEncontrada, itemp);

        }

        manutencao.setValortotal(manutencao.getValortotal() + pecas.getValor());
        //salvando();
    }

    public void salvando() throws SQLException {

        List<ItemPecas> itensPecas = getManutencao().getItensPecas();
        List<ItemTecnico> itensTec = getManutencao().getItensTec();

        if (getManutencao() == null || getManutencao().getServexec().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Abra a manutenção."));
            return;
        }

        if (getManutencao() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Digite um serviço."));
            return;
        }
        
         setManutencao(new ManutencaoDao().save(getManutencao()));

        for (ItemPecas v : itensPecas) {

            if (v.getId() >= 0) {

                v.setManutencao(getManutencao());
                new ItemPecasDao().save(v);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de peças!", "Inclua os peças corretamente."));
                return;
            }

        }

        for (ItemTecnico w : itensTec) {

            if (w.getId() >= 0) {

                w.setManutencao(getManutencao());
                new ItemTecnicoDao().save(w);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de custo!", "Inclua os técnicos corretamente."));
                return;
            }

        }

        setManutencao(new ManutencaoDao().save(getManutencao()));
        refresh();
        setEtapa(0);

    }

    public void removerItemPeca(ItemPecas itempecas) throws SQLException {

        List<ItemPecas> itensPecas = getManutencao().getItensPecas();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensPecas.size() && posicaoEncontrada < 0; pos++) {
            ItemPecas itemTemp = itensPecas.get(pos);

            if (itemTemp.getPecas().equals(itempecas.getPecas())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {

            itempecas.setQtd(itempecas.getQtd() - 1);
            manutencao.setValortotal(manutencao.getValortotal() - itempecas.getPecas().getValor());

            if (itempecas.getQtd() == 0) {
                itensPecas.remove(posicaoEncontrada);

            }

            if (itempecas.getId() > 0) {

                ItemPecasDao dao = new ItemPecasDao();

                dao.excluirItemPecas(itempecas);

            }

        }

    }

    public void adicionarTecnico(Tecnico tecnico) throws SQLException {

        List<ItemTecnico> itensTec = getManutencao().getItensTec();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensTec.size() && posicaoEncontrada < 0; pos++) {
            ItemTecnico itemTemp = itensTec.get(pos);
            if (itemTemp.getTecnico().equals(tecnico)) {
                posicaoEncontrada = pos;
            }
        }

        ItemTecnico itemp = new ItemTecnico();
        itemp.setTecnico(tecnico);

        if (posicaoEncontrada < 0) {

            if (getManutencao() == null || getManutencao().getServexec().isEmpty()) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nome de serviço não encontrado!", "Entre com o nome do serviço."));
                return;

            }

            itemp.setQtd(1);
            itemp.setValortec(tecnico.getValortecnico());
            itensTec.add(itemp);

        } else {

            ItemTecnico itemTemp = itensTec.get(posicaoEncontrada);
            itemp.setQtd(itemTemp.getQtd() + 1);
            itemp.setValortec(tecnico.getValortecnico() * itemp.getQtd());
            itensTec.set(posicaoEncontrada, itemp);

        }

        manutencao.setValortotal(manutencao.getValortotal() + tecnico.getValortecnico());
        //salvando();
    }

    public void removerItemTecnico(ItemTecnico itemtecnico) throws SQLException {

        List<ItemTecnico> itensTec = getManutencao().getItensTec();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensTec.size() && posicaoEncontrada < 0; pos++) {
            ItemTecnico itemTemp = itensTec.get(pos);

            if (itemTemp.getTecnico().equals(itemtecnico.getTecnico())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {

            itemtecnico.setQtd(itemtecnico.getQtd() - 1);
            itemtecnico.setValortec(itemtecnico.getValortec() - itemtecnico.getTecnico().getValortecnico());
            //itempecas.setValor(itempecas.getValor());

            if (itemtecnico.getQtd() == 0) {
                itensTec.remove(posicaoEncontrada);
                manutencao.setValortotal(manutencao.getValortotal() - itemtecnico.getTecnico().getValortecnico());

            }

            if (itemtecnico.getId() > 0) {

                ItemTecnicoDao dao = new ItemTecnicoDao();
                dao.excluirItemTecnico(itemtecnico);

            }
            

        }

    }
    
    
    public void excluirTecnico() throws SQLException {
               
        if (getTecnico() == null) {
            return;
        }
        if (getTecnico().getId() == 0) {
            return;
        }
        
        new TecnicoDao().delete(Tecnico.class, getTecnico().getId());
        refresh();
        setEtapa(0);
        novo();
        
        
    }
    
        
    
    public void excluirGeralTecnico() throws SQLException{
        getTecnicoMB().excluir();
        refresh();
        novo();
    }
}
