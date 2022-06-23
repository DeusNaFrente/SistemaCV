package br.com.iris.control;

import br.com.iris.dao.ComissionadoDao;
import br.com.iris.dao.CustovariavelDao;
import br.com.iris.dao.EntregaDao;
import br.com.iris.dao.FornecedorDao;
import br.com.iris.dao.ItemComissionadoDao;
import br.com.iris.dao.ItcDao;
import br.com.iris.dao.ItemEntregaDao;
import br.com.iris.dao.ItemLogEquipeDao;
import br.com.iris.dao.ItemLogRetiradaDao;
import br.com.iris.dao.ItemOrcDao;
import br.com.iris.dao.ItemTransporteDao;
import br.com.iris.dao.LogRetiradaDao;
import br.com.iris.dao.LogequipeDao;
import br.com.iris.dao.OrcamentoDao;
import br.com.iris.dao.ProductDao;
import br.com.iris.dao.ProdutoDao;
import br.com.iris.dao.TransporteDao;
import br.com.iris.dao.VeiculoDao;
import br.com.iris.model.Comissionado;
import br.com.iris.model.Custovariavel;
import br.com.iris.model.Entrega;
import br.com.iris.model.Fornecedor;
import br.com.iris.model.ItemComissionado;
import br.com.iris.model.Itc;
import br.com.iris.model.ItemEntrega;
import br.com.iris.model.ItemLogEquipe;
import br.com.iris.model.ItemLogRetirada;
import br.com.iris.model.ItemOrc;
import br.com.iris.model.ItemTransporte;
import br.com.iris.model.Logretirada;
import br.com.iris.model.Logequipe;
import br.com.iris.model.Orcamento;
import br.com.iris.model.Product;
import br.com.iris.model.Produto;
import br.com.iris.model.Transporte;
import br.com.iris.model.Veiculo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "orcamentoMB")
@ViewScoped

public class OrcamentoMB implements Serializable {
    @ManagedProperty(value = "#{comissionadoMB}")
    private ComissionadoMB comissionadoMB;
    public ComissionadoMB getComissionadoMB() {return comissionadoMB;}
    public void setComissionadoMB(ComissionadoMB comissionadoMB) {this.comissionadoMB = comissionadoMB;}
    
    @ManagedProperty(value = "#{custovariavelMB}")
    private CustovariavelMB custovariavelMB;
    public CustovariavelMB getCustovariavelMB() {return custovariavelMB;}
    public void setCustovariavelMB(CustovariavelMB custovariavelMB) {this.custovariavelMB = custovariavelMB;}
    
    @ManagedProperty(value = "#{logequipeMB}")
    private LogequipeMB logequipeMB;
    public LogequipeMB getLogequipeMB() {return logequipeMB;}
    public void setLogequipeMB(LogequipeMB logequipeMB) {this.logequipeMB = logequipeMB;}
    
    @ManagedProperty(value = "#{logretiraMB}")
    private LogretiradaMB logretiradaMB;
    public LogretiradaMB getLogretiradaMB() {return logretiradaMB;}
    public void setLogretiradaMB(LogretiradaMB logretiradaMB) {this.logretiradaMB = logretiradaMB;}
    
    @ManagedProperty(value = "#{entregaMB}")
    private EntregaMB entregaMB;
    public EntregaMB getEntregaMB() {return entregaMB;}
    public void setEntregaMB(EntregaMB entregaMB) {this.entregaMB = entregaMB;}
    

    private List<Orcamento> orcamentos;
    private Orcamento orcamento = new Orcamento();
    private int etapa = 0;

    private ItemOrc itemorc = new ItemOrc();
    private List<ItemOrc> itemOrc;

    private List<Product> itemProduto;
    private Product product = new Product();

    private List<Custovariavel> arrayCustoVariavel;
    private Custovariavel custovariavel = new Custovariavel();

    private List<Fornecedor> arrayFornecedor;
    private Fornecedor fornecedor = new Fornecedor();

    private List<Veiculo> arrayVeiculo;

    private List<Comissionado> arrayComissionados;
    private Comissionado comissionado = new Comissionado();

    private List<ItemComissionado> itemCom;
    private ItemComissionado itemcomissionado = new ItemComissionado();

    private List<Itc> itemCustov;
    private Itc itemcustovariavel = new Itc();

    private List<Transporte> orcTransp;
    private Transporte transporte = new Transporte();

    private List<ItemTransporte> itemT;
    private ItemTransporte itemtransporte = new ItemTransporte();

    private List<Entrega> orcEntrega;
    private Entrega entrega = new Entrega();

    private List<ItemEntrega> itemE;
    private ItemEntrega itementrega = new ItemEntrega();

    private List<Logequipe> arrayLogequipe;
    private Logequipe logequipe = new Logequipe();

    private List<ItemLogEquipe> itemTime;
    private ItemLogEquipe itemlogequipe = new ItemLogEquipe();

    private List<ItemLogRetirada> itemRetira;
    private ItemLogRetirada itemlogretirada = new ItemLogRetirada();

    private List<Logretirada> arrayLogretirada;
    private Logretirada logretiradas = new Logretirada();

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Orcamento> getOrcamentos() {
        if (orcamentos == null) {
            refresh();
        }
        return orcamentos;
    }

    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }

    public void refresh() {
        setArrayFornecedor(new FornecedorDao().getAll(Fornecedor.class));
        setArrayCustoVariavel(new CustovariavelDao().getAll(Custovariavel.class));
        setOrcTransp(new TransporteDao().getAll(Transporte.class));
        setArrayLogequipe(new LogequipeDao().getAll(Logequipe.class));
        setOrcEntrega(new EntregaDao().getAll(Entrega.class));
        setOrcamentos(new OrcamentoDao().getAll(Orcamento.class));
        setItemProduto(new ProductDao().getAll(Product.class));
        setArrayVeiculo(new VeiculoDao().getAll(Veiculo.class));
        setArrayComissionados(new ComissionadoDao().getAll(Comissionado.class));
        setArrayLogretirada(new LogRetiradaDao().getAll(Logretirada.class));
        itemOrc = new ArrayList<>();
        itemCom = new ArrayList<>();
        itemCustov = new ArrayList<>();
        itemT = new ArrayList<>();
        itemE = new ArrayList<>();
        itemTime = new ArrayList<>();
        itemRetira = new ArrayList<>();

    }

    public Orcamento getOrcamento() {

        if (orcamento.getItensT().size() > 0) {
            orcamento.setLogtransporte(true);
        }

        if (orcamento.getItensT().isEmpty()) {
            orcamento.setLogtransporte(false);
        }

        if (orcamento.getItensE().size() > 0) {
            orcamento.setLogentrega(true);
        }

        if (orcamento.getItensE().isEmpty()) {
            orcamento.setLogentrega(false);
        }

        if (orcamento.getItensTime().size() > 0) {
            orcamento.setLogtime(true);
        }

        if (orcamento.getItensTime().isEmpty()) {
            orcamento.setLogtime(false);
        }

        if (orcamento.getItensRetira().size() > 0) {
            orcamento.setLogretirada(true);
        }

        if (orcamento.getItensRetira().isEmpty()) {
            orcamento.setLogretirada(false);
        }

        return orcamento;

    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

    public List<ItemOrc> getItemOrc() {
        if (itemOrc == null) {
            refresh();
        }
        return itemOrc;
    }

    public void setItemOrc(List<ItemOrc> itemOrc) {
        this.itemOrc = itemOrc;
    }

    public List<Product> getItemProduto() {if(itemProduto == null){refresh();}
        return itemProduto;// tabela de produtos não esta carregando
    }

    public void setItemProduto(List<Product> itemProduto) {
        this.itemProduto = itemProduto;
    }

    
    

    public ItemOrc getItemorc() {
        return itemorc;
    }

    public void setItemorc(ItemOrc itemorc) {
        this.itemorc = itemorc;
    }

    public List<Custovariavel> getArrayCustoVariavel() {
        if (arrayCustoVariavel == null) {
            refresh();
        }
        return arrayCustoVariavel;
    }

    public void setArrayCustoVariavel(List<Custovariavel> arrayCustoVariavel) {
        this.arrayCustoVariavel = arrayCustoVariavel;
    }

    public Custovariavel getCustovariavel() {
        return custovariavel;
    }

    public void setCustovariavel(Custovariavel custovariavel) {
        this.custovariavel = custovariavel;
    }

    public List<Fornecedor> getArrayFornecedor() {
        if (arrayFornecedor == null) {
            refresh();
        }
        return arrayFornecedor;
    }

    public void setArrayFornecedor(List<Fornecedor> arrayFornecedor) {
        this.arrayFornecedor = arrayFornecedor;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Veiculo> getArrayVeiculo() {
        if (arrayVeiculo == null) {
            refresh();
        }
        return arrayVeiculo;
    }

    public void setArrayVeiculo(List<Veiculo> arrayVeiculo) {
        this.arrayVeiculo = arrayVeiculo;
    }

    public List<Comissionado> getArrayComissionados() {
        if (arrayComissionados == null) {
            refresh();
        }
        return arrayComissionados;
    }

    public void setArrayComissionados(List<Comissionado> arrayComissionados) {
        this.arrayComissionados = arrayComissionados;
    }

    public Comissionado getComissionado() {
        return comissionado;
    }

    public void setComissionado(Comissionado comissionado) {
        this.comissionado = comissionado;
    }

    public List<ItemComissionado> getItemCom() {
        if (itemCom == null) {
            refresh();
        }
        return itemCom;
    }

    public void setItemCom(List<ItemComissionado> itemCom) {
        this.itemCom = itemCom;
    }

    public ItemComissionado getItemcomissionado() {
        return itemcomissionado;
    }

    public void setItemcomissionado(ItemComissionado itemcomissionado) {
        this.itemcomissionado = itemcomissionado;
    }

    public List<Itc> getItemCustov() {
        if (itemCustov == null) {
            refresh();
        }
        return itemCustov;
    }

    public void setItemCustov(List<Itc> itemCustov) {
        this.itemCustov = itemCustov;
    }

    public Itc getItemcustovariavel() {
        return itemcustovariavel;
    }

    public void setItemcustovariavel(Itc itemcustovariavel) {
        this.itemcustovariavel = itemcustovariavel;
    }

    public void novo() {
        setOrcamento(new Orcamento());
        etapa = 1;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Transporte> getOrcTransp() {
        return orcTransp;
    }

    public void setOrcTransp(List<Transporte> orcTransp) {
        this.orcTransp = orcTransp;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public List<ItemTransporte> getItemT() {
        if (itemT == null) {
            refresh();
        }

        return itemT;
    }

    public void setItemT(List<ItemTransporte> itemT) {
        this.itemT = itemT;
    }

    public ItemTransporte getItemtransporte() {
        return itemtransporte;
    }

    public void setItemtransporte(ItemTransporte itemtransporte) {
        this.itemtransporte = itemtransporte;
    }

    public List<ItemEntrega> getItemE() {
        if (itemE == null) {
            refresh();
        }
        return itemE;
    }

    public void setItemE(List<ItemEntrega> itemE) {
        this.itemE = itemE;
    }

    public ItemEntrega getItementrega() {
        return itementrega;
    }

    public void setItementrega(ItemEntrega itementrega) {
        this.itementrega = itementrega;
    }

    public List<Entrega> getOrcEntrega() {
        return orcEntrega;
    }

    public void setOrcEntrega(List<Entrega> orcEntrega) {
        this.orcEntrega = orcEntrega;
    }

    public Entrega getEntrega() {
        if (entrega == null) {
            refresh();
        }
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public List<Logequipe> getArrayLogequipe() {
        if (arrayLogequipe == null) {
            refresh();
        }
        return arrayLogequipe;
    }

    public void setArrayLogequipe(List<Logequipe> arrayLogequipe) {
        this.arrayLogequipe = arrayLogequipe;
    }

    public Logequipe getLogequipe() {
        return logequipe;
    }

    public void setLogequipe(Logequipe logequipe) {
        this.logequipe = logequipe;
    }

    public List<ItemLogEquipe> getItemTime() {
        if (itemTime == null) {
            refresh();
        }

        return itemTime;
    }

    public void setItemTime(List<ItemLogEquipe> itemTime) {
        this.itemTime = itemTime;
    }

    public ItemLogEquipe getItemlogequipe() {
        return itemlogequipe;
    }

    public void setItemlogequipe(ItemLogEquipe itemlogequipe) {
        this.itemlogequipe = itemlogequipe;
    }

    public List<ItemLogRetirada> getItemRetira() {
        if (itemRetira == null) {
            refresh();
        }
        return itemRetira;
    }

    public void setItemRetira(List<ItemLogRetirada> itemRetira) {
        this.itemRetira = itemRetira;
    }

    public ItemLogRetirada getItemlogretirada() {
        return itemlogretirada;
    }

    public void setItemlogretirada(ItemLogRetirada itemlogretirada) {
        this.itemlogretirada = itemlogretirada;
    }

    public List<Logretirada> getArrayLogretirada() {
        if (arrayLogretirada == null) {
            refresh();
        }
        return arrayLogretirada;
    }

    public void setArrayLogretirada(List<Logretirada> arrayLogretirada) {
        this.arrayLogretirada = arrayLogretirada;
    }

    public Logretirada getLogretiradas() {
        return logretiradas;
    }

    public void setLogretiradas(Logretirada logretiradas) {
        this.logretiradas = logretiradas;
    }

    public void salvarGeral() {

        if (getOrcamento() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Empresa não encontrada!", "Selecione uma empresa."));
            return;
        }

        if (getOrcamento().getId() == 0) {
            // PrimeFaces.current().executeScript("PF('dlgNovoL').hide()");
            return;

        }

        if (getOrcamento().getCliente().getEmpresa().isEmpty() && getOrcamento().getItensC().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique!", "Não há dados para salvar."));
            return;
        }

        setOrcamento(new OrcamentoDao().save(getOrcamento()));

    }

    public void salvar() throws SQLException {

        List<Itc> itensCustoVariavel = getOrcamento().getItensCustoVariavel();
        List<ItemComissionado> itensC = getOrcamento().getItensC();
        List<ItemOrc> itens = getOrcamento().getItens();
        List<ItemTransporte> itensT = getOrcamento().getItensT();
        List<ItemEntrega> itensE = getOrcamento().getItensE();
        List<ItemLogEquipe> itensTime = getOrcamento().getItensTime();
        List<ItemLogRetirada> itensRetira = getOrcamento().getItensRetira();

        if (getOrcamento() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione uma empresa."));
            return;
        }

        if (getOrcamento().getCliente() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione uma Empresa."));
            return;
        }

        if (getOrcamento().getVendedor() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione um Vendedor."));
            return;
        }

        if (getOrcamento().getCliente().getEmpresa().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique!", "Não há dados para salvar."));
            return;
        }
        // setOrcamento(new OrcamentoDao().save(getOrcamento()));
        for (Itc v : itensCustoVariavel) {

            if (v.getId() >= 0) {

                v.setOrcamento(getOrcamento());
                new ItcDao().save(v);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Custos Váriaveis!", "Inclua os custos."));
                return;
            }

        }

       
        for (ItemComissionado e : itensC) {

            if (e.getId() >= 0) {
               
                e.setOrcamento(getOrcamento());
                new ItemComissionadoDao().save(e);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Comissionados!", "Inclua comissões."));
                return;
            }

        }

        
        for (ItemOrc i : itens) {

            if (i.getQtd() >= 0) {

                i.setOrcamento(getOrcamento());
                new ItemOrcDao().save(i);

            } else {
                
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição dos itens!", "Verifique se adicionou produtos."));
                return;

             

            }
        }

        for (ItemTransporte it : itensT) {

            if (it.getId() >= 0) {

                it.setOrcamento(getOrcamento());
                new ItemTransporteDao().save(it);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Transporte!", "Inclua o tipo de Transporte."));
                return;
            }

        }

        for (ItemEntrega ite : itensE) {

            if (ite.getId() >= 0) {
          

                ite.setOrcamento(getOrcamento());
                new ItemEntregaDao().save(ite);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Entrega!", "Inclua o tipo de Transporte."));
                return;
            }

        }

        for (ItemLogEquipe itlg : itensTime) {

            if (itlg.getId() >= 0) {

                itlg.setOrcamento(getOrcamento());
                new ItemLogEquipeDao().save(itlg);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição do Time!", "Inclua os Times na compisição."));
                return;
            }

        }

        for (ItemLogRetirada itlr : itensRetira) {

            if (itlr.getId() >= 0) {

                itlr.setOrcamento(getOrcamento());
                new ItemLogRetiradaDao().save(itlr);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Retirada!", "Inclua os ítens de retirada na composição."));
                return;
            }

        }

        setOrcamento(new OrcamentoDao().save(getOrcamento()));
        refresh();
        setEtapa(0);
        novo();

    }

    public void salvando() throws SQLException {

        List<Itc> itensCustoVariavel = getOrcamento().getItensCustoVariavel();
        List<ItemComissionado> itensC = getOrcamento().getItensC();
        List<ItemOrc> itens = getOrcamento().getItens();
        List<ItemTransporte> itensT = getOrcamento().getItensT();
        List<ItemEntrega> itensE = getOrcamento().getItensE();
        List<ItemLogEquipe> itensTime = getOrcamento().getItensTime();
        List<ItemLogRetirada> itensRetira = getOrcamento().getItensRetira();

        if (getOrcamento() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione uma empresa."));
            return;
        }

        if (getOrcamento().getCliente() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione uma Empresa."));
            return;
        }

        if (getOrcamento().getVendedor() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione um Vendedor."));
            return;
        }

        if (getOrcamento().getCliente().getEmpresa().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique!", "Não há dados para salvar."));
            return;
        }

        for (Itc v : itensCustoVariavel) {

            if (v.getId() >= 0) {

                v.setOrcamento(getOrcamento());
                new ItcDao().save(v);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Custos Váriaveis!", "Inclua os custos."));
                return;
            }

        }

        // setOrcamento(new OrcamentoDao().save(getOrcamento()));
        for (ItemComissionado e : itensC) {

            if (e.getId() >= 0) {
                //insere o item do orçamento no obj orçamento

                e.setOrcamento(getOrcamento());
                new ItemComissionadoDao().save(e);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Comissionados!", "Inclua comissões."));
                return;
            }

        }

        //  setOrcamento(new OrcamentoDao().save(getOrcamento()));
        for (ItemOrc i : itens) {

            if (i.getQtd() <= 1) {

                i.setOrcamento(getOrcamento());
                new ItemOrcDao().save(i);

            } else {
                //  if (i.getProduto().getId() == (getProduto().getId())) {

                new ItemOrcDao().atualizarItens(i);

            }
        }

        for (ItemTransporte it : itensT) {

            if (it.getId() >= 0) {

                it.setOrcamento(getOrcamento());
                new ItemTransporteDao().save(it);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Transporte!", "Inclua o tipo de Transporte."));
                return;
            }

        }

        for (ItemEntrega ite : itensE) {

            if (ite.getId() >= 0) {
                //insere o item do orçamento no obj orçamento

                ite.setOrcamento(getOrcamento());
                new ItemEntregaDao().save(ite);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Entrega!", "Inclua o tipo de Transporte."));
                return;
            }

        }

        for (ItemLogEquipe itlg : itensTime) {

            if (itlg.getId() >= 0) {

                itlg.setOrcamento(getOrcamento());
                new ItemLogEquipeDao().save(itlg);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição do Time!", "Inclua os Times na compisição."));
                return;
            }

        }

        for (ItemLogRetirada itlr : itensRetira) {

            if (itlr.getId() >= 0) {

                itlr.setOrcamento(getOrcamento());
                new ItemLogRetiradaDao().save(itlr);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição de Retirada!", "Inclua os ítens de retirada na composição."));
                return;
            }

        }

        setOrcamento(new OrcamentoDao().save(getOrcamento()));
        refresh();
        setEtapa(0);

    }

    public void modificar() {
        if (getOrcamento() == null) {
            return;
        }

        if (getOrcamento().getNomeorc() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo nome de orçamento é obrigatório"));
            return;
        }

        new OrcamentoDao().save(getOrcamento());
        refresh();
        setEtapa(0);
        novo();

    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getOrcamento() == null) {
            return;
        }
        if (getOrcamento().getId() == 0) {
            return;
        }
        new OrcamentoDao().delete(Orcamento.class, getOrcamento().getId());
        refresh();
        setEtapa(0);
        novo();
    }

    public void adicionarProduto(Product product) {

        List<ItemOrc> itens = getOrcamento().getItens();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {//percorrer a lista de itens
            ItemOrc itemTemp = itens.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getProduct().equals(product)) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        ItemOrc itemorc = new ItemOrc();
        itemorc.setProduct(product);

        if (posicaoEncontrada < 0) {

            if (getOrcamento().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma empresa Encontrada!", "Primeiro abra uma empresa."));
                return;
            }

            itemorc.setQtd(1);
            itens.add(itemorc);

        } else if (itemorc.getProduct().equals(product)) {

            ItemOrc itemTemp = itens.get(posicaoEncontrada);//posição encontrada anteriormente

            itemorc.setQtd(itemTemp.getQtd() + 1);//pega o valor anterior e add mais um
            itemorc.setValor_parcial((int) product.getPrecofinal() * itemorc.getQtd());
            //itemorc.setValor_parcial(produto.getPrecofinal() * itemorc.getQtd()); a linha
            itens.set(posicaoEncontrada, itemorc);//posiçao encontrada e o que eu quero add

        }

        orcamento.setValor(orcamento.getValor() + product.getPrecofinal());

    }

    public void removerItemorc(ItemOrc itemorc) throws SQLException {

        List<ItemOrc> iorc = getOrcamento().getItens();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < iorc.size() && posicaoEncontrada < 0; pos++) {
            ItemOrc itemTemp = iorc.get(pos);

            if (itemTemp.getProduct().equals(itemorc.getProduct())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            iorc.remove(posicaoEncontrada);
            if (getOrcamento().getItensC() == null) {
                return;
            }
            ItemOrcDao dao = new ItemOrcDao();
            dao.excluirItensOrcamento(itemorc);
            orcamento.setValor(orcamento.getValor() - itemorc.getValor_parcial());
            salvarGeral();
        }

    }

    public void removerItem(ItemOrc itemorc) throws SQLException {

        List<ItemOrc> iorc = getOrcamento().getItens();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < iorc.size() && posicaoEncontrada < 0; pos++) {
            ItemOrc itemTemp = iorc.get(pos);

            if (itemTemp.getProduct().equals(itemorc.getProduct())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {

            itemorc.setQtd(itemorc.getQtd() - 1);
            orcamento.setValor(orcamento.getValor() - itemorc.getProduct().getPrecofinal());

            if (itemorc.getQtd() == 0) {
                iorc.remove(posicaoEncontrada);

            }

            if (itemorc.getId() > 0) {

                ItemOrcDao dao = new ItemOrcDao();

                dao.excluirItensOrcamento(itemorc);

            }

        }

    }

    public void addItem() {
        getOrcamento().getItens().add(itemorc);

    }

    public void adicionarComissionado(Comissionado comissionado) {//faz parte de itemcomissionado

        if (getOrcamento().getValor() == 0) {

            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Orçamento sem valor definido!!", "Conclua este Orçamento."));
            return;

        }

        List<ItemComissionado> itensC = getOrcamento().getItensC();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensC.size() && posicaoEncontrada < 0; pos++) {//percorrer a lista de itens
            ItemComissionado itemTemp = itensC.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getComissionado().equals(comissionado)) {
                posicaoEncontrada = pos;
            }
        }

        ItemComissionado itemcomissionado = new ItemComissionado();
        itemcomissionado.setComissionado(comissionado);

        if (posicaoEncontrada < 0) {

            if (getOrcamento().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma empresa Encontrada!", "Primeiro abra uma empresa."));
                return;
            }
            itensC.add(itemcomissionado);

        } else if (itemcomissionado.getComissionado().equals(comissionado)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este comissionado já foi incluído."));
            return;

        } else {

            ItemComissionado itemTemp = itensC.get(posicaoEncontrada);//posição encontrada anteriormente
            itemcomissionado.setValor(itemTemp.getValor() + 1);//pega o valor anterior e add mais um
            //modeloitemitem.setValor_parcial(produto.getPreco() * item.getQtd());

            itemCom.set(posicaoEncontrada, itemcomissionado);//posiçao encontrada e o que eu quero add

        }

        orcamento.setValor(orcamento.getValor() + itemcomissionado.getComissionado().getValor());
        refresh();
    }

    public void removerItemComissionado(ItemComissionado comissionado) throws SQLException {

        List<ItemComissionado> itensComis = getOrcamento().getItensC();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensComis.size() && posicaoEncontrada < 0; pos++) {
            ItemComissionado itemTemp = itensComis.get(pos);

            if (itemTemp.getComissionado().equals(comissionado.getComissionado())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            itensComis.remove(posicaoEncontrada);
            ItemComissionadoDao dao = new ItemComissionadoDao();
            dao.excluirComissionado(comissionado);
            salvarGeral();
        }

        orcamento.setValor(orcamento.getValor() - comissionado.getComissionado().getValor());

    }

    //este bloco n esta em uso, ele apenas remove o item da tela(funciona, mas é teste)***************************
    public void removerComissionado(ItemComissionado item) {
        List<ItemComissionado> itensC = getOrcamento().getItensC();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensC.size() && posicaoEncontrada < 0; pos++) {
            ItemComissionado itemTemp = itensC.get(pos);

            if (itemTemp.getComissionado().equals(item.getComissionado())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensC.remove(posicaoEncontrada);
            //   if(getComissionado().getId() == 0){return;}
            //  if(getComissionado().getNome()== null){return;}

        }

    }

//Fim do bloco que n esta em uso **********************************************************************
    public void adicionarCustoVariavel(Custovariavel custovariavel) {//faz parte de itemcomissionado

        List<Itc> itensCustoVariavel = getOrcamento().getItensCustoVariavel();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensCustoVariavel.size() && posicaoEncontrada < 0; pos++) {
            Itc itemTemp = itensCustoVariavel.get(pos);
            if (itemTemp.getCustovariavel().equals(custovariavel)) {
                posicaoEncontrada = pos;
            }
        }

        Itc itc = new Itc();
        itc.setCustovariavel(custovariavel);

        if (posicaoEncontrada < 0) {

            if (getOrcamento().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma empresa Encontrada!", "Primeiro abra uma empresa."));
                return;
            }
            itensCustoVariavel.add(itc);

        } else if (itc.getCustovariavel().equals(custovariavel)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem de custo já foi incluído."));
            return;

        } else {

            Itc itemTemp = itensCustoVariavel.get(posicaoEncontrada);//posição encontrada anteriormente
            itc.setValor(itemTemp.getValor() + 1);//pega o valor anterior e add mais um

            itemCustov.set(posicaoEncontrada, itc);//posiçao encontrada e o que eu quero add

        }

        orcamento.setValor(orcamento.getValor() + custovariavel.getValor());

    }

    public void removerItemCustoVariavel(Itc itemcustovariavel) throws SQLException {

        List<Itc> itensCv = getOrcamento().getItensCustoVariavel();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensCv.size() && posicaoEncontrada < 0; pos++) {
            Itc itemTemp = itensCv.get(pos);

            if (itemTemp.getCustovariavel().equals(itemcustovariavel.getCustovariavel())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            itensCv.remove(posicaoEncontrada);
            // if(getCustovariavel().getId() == 0 ){return;}
            ItcDao dao = new ItcDao();
            dao.excluirCustoVariavel(itemcustovariavel);
            salvarGeral();

        }

        orcamento.setValor(orcamento.getValor() - itemcustovariavel.getCustovariavel().getValor());
    }

    public void removerCustoVariavel(Itc itemcustovariavel) throws SQLException {

        List<Itc> itensCv = getOrcamento().getItensCustoVariavel();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensCv.size() && posicaoEncontrada < 0; pos++) {
            Itc itemTemp = itensCv.get(pos);

            if (itemTemp.getCustovariavel().equals(itemcustovariavel.getCustovariavel())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensCv.remove(posicaoEncontrada);

        }

    }

    public void adicionarTransporte(Transporte transporte) {//faz parte de itemcomissionado

        List<ItemTransporte> itensT = getOrcamento().getItensT();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensT.size() && posicaoEncontrada < 0; pos++) {//percorrer a lista de itens
            ItemTransporte itemTemp = itensT.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getTransporte().equals(transporte)) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        ItemTransporte itemt = new ItemTransporte();
        itemt.setTransporte(transporte);

        if (posicaoEncontrada < 0) {

            if (getOrcamento().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma empresa Encontrada!", "Primeiro abra uma empresa."));
                return;
            }
            itensT.add(itemt);

        } else if (itemt.getTransporte().equals(transporte)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem de custo já foi incluído."));
            return;

        } else {

            ItemTransporte itemTemp = itensT.get(posicaoEncontrada);
            itemt.setValort(itemTemp.getValort() + 1);

            itemT.set(posicaoEncontrada, itemt);

        }

        orcamento.setValor(orcamento.getValor() + transporte.getCustotransporte());
        orcamento.setValor(orcamento.getValor() + transporte.getCustocorreio());
    }

    public void removerItemTransporte(ItemTransporte itemtransporte) throws SQLException {

        List<ItemTransporte> itensT = getOrcamento().getItensT();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensT.size() && posicaoEncontrada < 0; pos++) {
            ItemTransporte itemTemp = itensT.get(pos);
            if (itemTemp.getTransporte().equals(itemtransporte.getTransporte())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensT.remove(posicaoEncontrada);
            ItemTransporteDao dao = new ItemTransporteDao();
            dao.excluirItemTransporte(itemtransporte);
            salvarGeral();//coloquei para da refresh no checkbox de logística

        }

        orcamento.setValor(orcamento.getValor() - itemtransporte.getTransporte().getCustotransporte());
        orcamento.setValor(orcamento.getValor() - itemtransporte.getTransporte().getCustocorreio());
    }

    public void removerTransporte(ItemTransporte itemtransporte) throws SQLException {

        List<ItemTransporte> itensT = getOrcamento().getItensT();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensT.size() && posicaoEncontrada < 0; pos++) {
            ItemTransporte itemTemp = itensT.get(pos);

            if (itemTemp.getTransporte().equals(itemtransporte.getTransporte())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensT.remove(posicaoEncontrada);

        }

        orcamento.setValor(orcamento.getValor() - itemtransporte.getTransporte().getCustocorreio());
        orcamento.setValor(orcamento.getValor() - itemtransporte.getTransporte().getCustotransporte());

    }

    public void adicionarEntrega(Entrega entrega) {//faz parte de itemcomissionado

        List<ItemEntrega> itensE = getOrcamento().getItensE();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensE.size() && posicaoEncontrada < 0; pos++) {
            ItemEntrega itemTemp = itensE.get(pos);
            if (itemTemp.getEntrega().equals(entrega)) {
                posicaoEncontrada = pos;
            }
        }

        ItemEntrega iteme = new ItemEntrega();
        iteme.setEntrega(entrega);

        if (posicaoEncontrada < 0) {

            if (getOrcamento().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma empresa Encontrada!", "Primeiro abra uma empresa."));
                return;
            }
            itensE.add(iteme);

        } else if (iteme.getEntrega().equals(entrega)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem de custo já foi incluído."));
            return;

        } else {

            ItemEntrega itemTemp = itensE.get(posicaoEncontrada);
            iteme.setValorentrega(itemTemp.getValorentrega() + 1);

            itemE.set(posicaoEncontrada, iteme);

        }

        orcamento.setValor(orcamento.getValor() + entrega.getDespesa());

    }

    public void removerEntrega(ItemEntrega itementrega) throws SQLException {

        List<ItemEntrega> itensE = getOrcamento().getItensE();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensE.size() && posicaoEncontrada < 0; pos++) {
            ItemEntrega itemTemp = itensE.get(pos);

            if (itemTemp.getEntrega().equals(itementrega.getEntrega())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensE.remove(posicaoEncontrada);

        }

        orcamento.setValor(orcamento.getValor() - itementrega.getEntrega().getDespesa());

    }

    public void removerItemEntrega(ItemEntrega itementrega) throws SQLException {

        List<ItemEntrega> itensE = getOrcamento().getItensE();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensE.size() && posicaoEncontrada < 0; pos++) {//percorrer a lista de itens
            ItemEntrega itemTemp = itensE.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getEntrega().equals(itementrega.getEntrega())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            itensE.remove(posicaoEncontrada);
            ItemEntregaDao dao = new ItemEntregaDao();
            dao.excluirItemEntrega(itementrega);
            salvarGeral();

        }

        orcamento.setValor(orcamento.getValor() - itementrega.getEntrega().getDespesa());

    }

    public void adicionarTime(Logequipe logequipe) throws SQLException {

        List<ItemLogEquipe> itensTime = getOrcamento().getItensTime();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensTime.size() && posicaoEncontrada < 0; pos++) {
            ItemLogEquipe itemTemp = itensTime.get(pos);
            if (itemTemp.getLogequipe().equals(logequipe)) {
                posicaoEncontrada = pos;
            }
        }

        ItemLogEquipe itemloge = new ItemLogEquipe();
        itemloge.setLogequipe(logequipe);

        if (posicaoEncontrada < 0) {

            if (getOrcamento().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma empresa Encontrada!", "Primeiro abra uma empresa."));
                return;
            }
            itensTime.add(itemloge);
            

        } else if (itemloge.getLogequipe().equals(logequipe)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem já foi incluído."));
            return;

        } else {

            ItemLogEquipe itemTemp = itensTime.get(posicaoEncontrada);
            itemloge.setValorequipe(itemTemp.getValorequipe() + 1);

            itemTime.set(posicaoEncontrada, itemloge);

        }

        orcamento.setValor(orcamento.getValor() + logequipe.getValor());
        salvando();
    }

    

    public void removerTime(ItemLogEquipe itemlogequipe) throws SQLException {

        List<ItemLogEquipe> itensTime = getOrcamento().getItensTime();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensTime.size() && posicaoEncontrada < 0; pos++) {
            ItemLogEquipe itemTemp = itensTime.get(pos);
            if (itemTemp.getLogequipe().equals(itemlogequipe.getLogequipe())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensTime.remove(posicaoEncontrada);

        }

        orcamento.setValor(orcamento.getValor() - itemlogequipe.getLogequipe().getValor());
    }

    public void adicionarRetirada(Logretirada logretiradas) {

        List<ItemLogRetirada> itensRetira = getOrcamento().getItensRetira();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensRetira.size() && posicaoEncontrada < 0; pos++) {//percorrer a lista de itens
            ItemLogRetirada itemTemp = itensRetira.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getLogretirada().equals(logretiradas)) {
                posicaoEncontrada = pos;
            }
        }

        ItemLogRetirada itemr = new ItemLogRetirada();
        itemr.setLogretirada(logretiradas);

        if (posicaoEncontrada < 0) {

            if (getOrcamento().getCliente() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nenhuma empresa Encontrada!", "Primeiro abra uma empresa."));
                return;

            }

            itensRetira.add(itemr);

        } else if (itemr.getLogretirada().equals(logretiradas)) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro ao adicionar!", "Este ítem já foi incluído."));
            return;
        } else {

            itemRetira.set(posicaoEncontrada, itemr);

        }

        System.out.println("Tudo Certo!");
    }

    public void removerRetirada(ItemLogRetirada itemlogretirada) throws SQLException {

        List<ItemLogRetirada> itensRetira = getOrcamento().getItensRetira();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensRetira.size() && posicaoEncontrada < 0; pos++) {
            ItemLogRetirada itemTemp = itensRetira.get(pos);
            if (itemTemp.getLogretirada().equals(itemlogretirada.getLogretirada())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensRetira.remove(posicaoEncontrada);

        }

    }

    public void removerItemRetirada(ItemLogRetirada itemlogretirada) throws SQLException {

        List<ItemLogRetirada> itensRetira = getOrcamento().getItensRetira();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensRetira.size() && posicaoEncontrada < 0; pos++) {
            ItemLogRetirada itemTemp = itensRetira.get(pos);
            if (itemTemp.getLogretirada().equals(itemlogretirada.getLogretirada())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensRetira.remove(posicaoEncontrada);
            ItemLogRetiradaDao dao = new ItemLogRetiradaDao();
            dao.excluirItemRetirada(itemlogretirada);
            salvarGeral();

        }

    }
    
    
    public void removerItemLogequipe(ItemLogEquipe itemlogequipe) throws SQLException {

        List<ItemLogEquipe> itensTime = getOrcamento().getItensTime();
        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensTime.size() && posicaoEncontrada < 0; pos++) {
            ItemLogEquipe itemTemp = itensTime.get(pos);
            if (itemTemp.getLogequipe().equals(itemlogequipe.getLogequipe())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {
            itensTime.remove(posicaoEncontrada);
            ItemLogEquipeDao dao = new ItemLogEquipeDao();
            dao.excluirItemEquipe(itemlogequipe);
            salvarGeral();

        }

        orcamento.setValor(orcamento.getValor() - itemlogequipe.getLogequipe().getValor());

    }
    
    
    public void excluirGeralComissionado() throws SQLException{
        getComissionadoMB().excluir();
        refresh();
        novo();
    }
    
    public void excluirGeralCusto() throws SQLException{
        getCustovariavelMB().excluir();
        refresh();
        novo();
    }
    
    
    public void excluirGeralEquipe() throws SQLException{
        getLogequipeMB().excluir();
        refresh();
        
    }
    
    public void excluirGeralRetira() throws SQLException{
        getLogretiradaMB().excluir();
        refresh();
        novo();
    }
    
    public void excluirGeralEntrega() throws SQLException{
        getEntregaMB().excluir();
        refresh();
        novo();
    }

}
