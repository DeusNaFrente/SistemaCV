package br.com.iris.control;

import br.com.iris.dao.AcabamentoDao;
import br.com.iris.dao.EquipamentoDao;
import br.com.iris.dao.ItemAcabDao;
import br.com.iris.dao.ItemEquipamentoDao;
import br.com.iris.dao.ItemMatDao;
import br.com.iris.dao.ItemProcDao;
import br.com.iris.dao.MateriaprimaDao;
import br.com.iris.dao.ModeloDao;
import br.com.iris.dao.ProcessoDao;
import br.com.iris.model.Acabamento;
import br.com.iris.model.Equipamento;
import br.com.iris.model.ItemAcab;
import br.com.iris.model.ItemEquip;
import br.com.iris.model.ItemMat;
import br.com.iris.model.ItemProc;
import br.com.iris.model.Materiaprima;
import br.com.iris.model.Modelo;
import br.com.iris.model.Processo;
import br.com.iris.model.Produto;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "modeloMB")
@ViewScoped
public class ModeloMB implements Serializable {

    private List<Modelo> modelos;
    private Modelo modelo = new Modelo();

    private List<Processo> processos;
    private Processo processo = new Processo();

    private List<ItemProc> itemProc;
    private ItemProc itemproc = new ItemProc();

    private List<Materiaprima> matprima;
    private Materiaprima materiaprima = new Materiaprima();

    private List<ItemMat> itemM;
    private ItemMat itemmat = new ItemMat();
    
    private List<Equipamento> equipamentos;
    private Equipamento equipamento = new Equipamento();
    
    private List<ItemEquip> itemEq;
    private ItemEquip itemeq = new ItemEquip();

    private List<Acabamento> acabamentos;
    private Acabamento acabamento = new Acabamento();

    private List<ItemAcab> itemAcab;
    private ItemAcab itemac = new ItemAcab();

    
    @ManyToOne
    @JoinColumn(name = "idprod")
    private Produto product;

    public List<Modelo> getModelos() {
        if (modelos == null) {
            refresh();
        }
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    public void refresh() {
        setAcabamentos(new AcabamentoDao().getAll(Acabamento.class));
        setModelos(new ModeloDao().getAll(Modelo.class));
        setProcessos(new ProcessoDao().getAll(Processo.class));
        setMatprima(new MateriaprimaDao().getAll(Materiaprima.class));
        setEquipamentos(new EquipamentoDao().getAll(Equipamento.class));
        itemProc = new ArrayList<>();
        itemM = new ArrayList<>();
        itemEq = new ArrayList<>();
        itemAcab = new ArrayList<>();
    }

    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Produto getProduct() {
        return product;
    }

    public void setProduct(Produto product) {
        this.product = product;
    }

    public List<ItemProc> getItemProc() {
        if (itemProc == null) {
            refresh();
        }
        return itemProc;
    }

    public void setItemProc(List<ItemProc> itemProc) {
        this.itemProc = itemProc;
    }

    public ItemProc getItemproc() {
        return itemproc;
    }

    public void setItemproc(ItemProc itemproc) {
        this.itemproc = itemproc;
    }

    public List<Processo> getProcessos() {
        if (processos == null) {
            refresh();
        }
        return processos;
    }

    public void setProcessos(List<Processo> processos) {
        this.processos = processos;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public List<Materiaprima> getMatprima() {
        if (matprima == null) {
            refresh();
        }
        return matprima;
    }

    public void setMatprima(List<Materiaprima> matprima) {
        this.matprima = matprima;
    }

    public Materiaprima getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }

    public List<ItemMat> getItemM() {
        if (itemM == null) {
            refresh();
        }
        return itemM;
    }

    public void setItemM(List<ItemMat> itemM) {
        this.itemM = itemM;
    }

    public ItemMat getItemmat() {
        return itemmat;
    }

    public void setItemmat(ItemMat itemmat) {
        this.itemmat = itemmat;
    }

    public List<Equipamento> getEquipamentos() {if(equipamentos == null){refresh();}
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public List<ItemEquip> getItemEq() {if(itemEq == null){refresh();}
        return itemEq;
    }

    public void setItemEq(List<ItemEquip> itemEq) {
        this.itemEq = itemEq;
    }

    public ItemEquip getItemeq() {
        return itemeq;
    }

    public void setItemeq(ItemEquip itemeq) {
        this.itemeq = itemeq;
    }

    public List<Acabamento> getAcabamentos() {if(acabamentos == null){refresh();}
        return acabamentos;
    }

    public void setAcabamentos(List<Acabamento> acabamentos) {
        this.acabamentos = acabamentos;
    }

    public Acabamento getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(Acabamento acabamento) {
        this.acabamento = acabamento;
    }

    public List<ItemAcab> getItemAcab() {
        return itemAcab;
    }

    public void setItemAcab(List<ItemAcab> itemAcab) {if(itemAcab == null){refresh();}
        this.itemAcab = itemAcab;
    }

    public ItemAcab getItemac() {
        return itemac;
    }

    public void setItemac(ItemAcab itemac) {
        this.itemac = itemac;
    }
    
    
    

    public void novo() {
        setModelo(new Modelo());
        etapa = 1;
    }

    public void salvar() {
        if (getModelo() == null) {
            return;
        }

        if (getModelo().getDescr() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Modelo é obrigatório"));
            return;
        }

        new ModeloDao().save(getModelo());
        refresh();
        setEtapa(0);
        novo();
    }

    public void salvarModProd() {
        if (getModelo() == null) {
            return;
        }

        if (getModelo().getDescr() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Modelo é obrigatório"));
            return;
        }

        refresh();
        setEtapa(0);
        novo();
    }

    public void salvando() throws SQLException {

        List<ItemProc> itensProc = getModelo().getItensProc();
        List<ItemMat> itensM = getModelo().getItensM();
        List<ItemEquip> itensEq = getModelo().getItensEq();
        List<ItemAcab> itensAcab = getModelo().getItensAcab();

        if (getModelo() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione um modelo."));
            return;
        }

        if (getModelo().getDescr() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione um modelo."));
            return;
        }

        for (ItemProc v : itensProc) {

            if (v.getId() >= 0) {

                v.setModelo(getModelo());
                new ItemProcDao().save(v);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição dos Processos!", "Inclua os processos corretamente."));
                return;
            }

        }
        
         for (ItemEquip e : itensEq) {

            if (e.getId() >= 0) {

                e.setModelo(getModelo());
                new ItemEquipamentoDao().save(e);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de equipamentos!", "Inclua os equipamentos corretamente."));
                return;
            }

        }

        for (ItemMat m : itensM) {

            if (m.getId() >= 0) {

                m.setModelo(getModelo());
                new ItemMatDao().save(m);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Matéria Prima!", "Inclua os itens corretamente."));
                return;

            }

        }
        
        
        for (ItemAcab a : itensAcab) {

            if (a.getId() >= 0) {

                a.setModelo(getModelo());
                new ItemAcabDao().save(a);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição dos Acabamentos!", "Inclua os acabamentos corretamente."));
                return;
            }

        }

        setModelo(new ModeloDao().save(getModelo()));
        refresh();
        setEtapa(0);

    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() throws SQLException {
        if (getModelo() == null) {
            return;
        }
        if (getModelo().getId() == 0) {
            return;
        }
        
       // new ModeloDao().delete(getModelo());
        refresh();
        setEtapa(0);
        novo();
    }
    
   
    
    

    public void adicionarProc(Processo processo) throws SQLException {

        List<ItemProc> itensProc = getModelo().getItensProc();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensProc.size() && posicaoEncontrada < 0; pos++) {
            ItemProc itemTemp = itensProc.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getProcesso().equals(processo)) {
                posicaoEncontrada = pos;
            }
        }

        ItemProc itemp = new ItemProc();
        itemp.setProcesso(processo);

        if (posicaoEncontrada < 0) {

            if (getModelo().getDescr() == null || getModelo().getDescr().isEmpty()) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum Modelo foi aberto!", "Primeiro abra um modelo."));
                return;

            }

            itemp.setTempo(1);
            itensProc.add(itemp);

        } else if (itemp.getProcesso().equals(processo)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este item já foi incluído."));
            return;

        } else {

            ItemProc itemTemp = itemProc.get(posicaoEncontrada);
            itemp.setTempo(itemTemp.getTempo() + 1);
            itemProc.set(posicaoEncontrada, itemp);
        }

        salvando();

    }

    public void removerProcesso(ItemProc itemproc) throws SQLException {

        List<ItemProc> itensProc = getModelo().getItensProc();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensProc.size() && posicaoEncontrada < 0; pos++) {
            ItemProc itemTemp = itensProc.get(pos);
            if (itemTemp.getProcesso().equals(itemproc.getProcesso())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensProc.remove(posicaoEncontrada);

        }

    }

    public void removerItemProc(ItemProc itemproc) throws SQLException {

        List<ItemProc> iT = getModelo().getItensProc();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < iT.size() && posicaoEncontrada < 0; pos++) {
            ItemProc itemTemp = iT.get(pos);

            if (itemTemp.getProcesso().equals(itemproc.getProcesso())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            iT.remove(posicaoEncontrada);
            ItemProcDao dao = new ItemProcDao();
            dao.excluirItensProc(itemproc);

        }

    }

    public void adicionarMat(Materiaprima materiaprima) throws SQLException {

        List<ItemMat> itensM = getModelo().getItensM();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensM.size() && posicaoEncontrada < 0; pos++) {//corrigir pois abandona nessa linha
            ItemMat itemTemp = itensM.get(pos);
            if (itemTemp.getMateriaprima().equals(materiaprima)) {
                posicaoEncontrada = pos;
            }
        }

        ItemMat itemmat = new ItemMat();
        itemmat.setMateriaprima(materiaprima);

        if (posicaoEncontrada < 0) {

            if (getProduct() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum modelo Encontrado!", "Primeiro abra um produto."));
                return;
            }
            itensM.add(itemmat);

        } else if (itemmat.getMateriaprima().equals(materiaprima)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem já foi incluído."));
            return;

        } else {

            ItemMat itemTemp = itensM.get(posicaoEncontrada);
            itemmat.setValormat(itemTemp.getValormat() + 1);

            itemM.set(posicaoEncontrada, itemmat);

        }

        modelo.setValor(modelo.getValor() + materiaprima.getValorpago());
        salvando();
    }
    
    public void removerItemMat(ItemMat itemmat) throws SQLException {

        List<ItemMat> itensM = getModelo().getItensM();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensM.size() && posicaoEncontrada < 0; pos++) {
            ItemMat itemTemp = itensM.get(pos);
            if (itemTemp.getMateriaprima().equals(itemmat.getMateriaprima())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensM.remove(posicaoEncontrada);
            ItemMatDao dao = new ItemMatDao();
            dao.excluirItemMatModelo(itemmat);
            
            

        }
        
         modelo.setValor(modelo.getValor() - itemmat.getMateriaprima().getValorpago());
         salvando();
    }
    
    
    public void adicionarEq(Equipamento equipamento) throws SQLException {

        List<ItemEquip> itensEq = getModelo().getItensEq();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensEq.size() && posicaoEncontrada < 0; pos++) {
            ItemEquip itemTemp = itensEq.get(pos);
            if (itemTemp.getEquipamento().equals(equipamento)) {
                posicaoEncontrada = pos;
            }
        }

        ItemEquip itemeq = new ItemEquip();
        itemeq.setEquipamento(equipamento);

        if (posicaoEncontrada < 0) {

            if (getModelo().getEquipamento() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum modelo Encontrado!", "Primeiro abra um modelo."));
                return;
            }
            itensEq.add(itemeq);

        } else if (itemeq.getEquipamento().equals(equipamento)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem já foi incluído."));
            return;

        } else {

            ItemEquip itemTemp = itensEq.get(posicaoEncontrada);
            itemeq.setCustoproducao(itemTemp.getCustoproducao() + 1);
            itemEq.set(posicaoEncontrada, itemeq);

        }
        modelo.setValor(modelo.getValor() + equipamento.getCustoproducao());
        salvando();
    }
    
    public void removerItemEq(ItemEquip itemequip) throws SQLException {

        List<ItemEquip> itensEq = getModelo().getItensEq();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensEq.size() && posicaoEncontrada < 0; pos++) {
            ItemEquip itemTemp = itensEq.get(pos);
            if (itemTemp.getEquipamento().equals(itemequip.getEquipamento())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensEq.remove(posicaoEncontrada);
            ItemEquipamentoDao dao = new ItemEquipamentoDao();
            dao.excluirItemEquip(itemequip);
            
            

        }
         modelo.setValor(modelo.getValor() - itemequip.getEquipamento().getCustoproducao());
         salvando();
    }
    
    
    public void adicionarAcab(Acabamento acabamento) throws SQLException {

        List<ItemAcab> itensAcab = getModelo().getItensAcab();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensAcab.size() && posicaoEncontrada < 0; pos++) {
            ItemAcab itemTemp = itensAcab.get(pos);
            if (itemTemp.getAcabamento().equals(acabamento)) {
                posicaoEncontrada = pos;
            }
        }

        ItemAcab itema = new ItemAcab();
        itema.setAcabamento(acabamento);

        if (posicaoEncontrada < 0) {

            if (getModelo().getAcabamento() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhum modelo Encontrado!", "Primeiro abra um modelo."));
                return;
            }
            itensAcab.add(itema);

        } else if (itema.getAcabamento().equals(acabamento)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem já foi incluído."));
            return;

        } else {

            ItemAcab itemTemp = itensAcab.get(posicaoEncontrada);
            itema.setValor_parcial(itemTemp.getValor_parcial() + 1);
            itemAcab.set(posicaoEncontrada, itema);

        }
        modelo.setValor(modelo.getValor() + acabamento.getPrecof());
        salvando();
    }
    
    public void removerItemAcab(ItemAcab itemacab) throws SQLException {

        List<ItemAcab> itensAcab = getModelo().getItensAcab();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensAcab.size() && posicaoEncontrada < 0; pos++) {
            ItemAcab itemTemp = itensAcab.get(pos);
            if (itemTemp.getAcabamento().equals(itemacab.getAcabamento())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensAcab.remove(posicaoEncontrada);
            ItemAcabDao dao = new ItemAcabDao();
            dao.excluirItemAcab(itemacab);
            
            

        }
         modelo.setValor(modelo.getValor() - itemacab.getAcabamento().getPrecof());
         salvando();
    }
    
    
    

}
