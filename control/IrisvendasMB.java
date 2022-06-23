/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.IrisfuncDao;
import br.com.iris.dao.IrisitemDao;
import br.com.iris.dao.IrisprodDao;
import br.com.iris.dao.IrisvendasDao;
import br.com.iris.model.Irisfunc;
import br.com.iris.model.Irisitem;
import br.com.iris.model.Irisprod;
import br.com.iris.model.Irisvendas;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Iris
 */
@ManagedBean(name = "irisvendasMB")
@ViewScoped
public class IrisvendasMB implements Serializable {

    
    private List<Irisitem> itens;
    private List<Irisitem> itensFiltrados;
    private Irisitem it = new Irisitem();

    private Irisprod produto = new Irisprod();
    private List<Irisprod> produtos;
    private List<Irisprod> produtosFiltrados;
   
    private Irisvendas vendaCadastro = new Irisvendas();
    private List<Irisvendas>listarVendas;
    
    private Irisfunc irisf = new Irisfunc();
    private List<Irisfunc> irisfuncionario;

    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public void refresh() {
        setListarVendas(new IrisvendasDao().getAll(Irisvendas.class));
        setIrisfuncionario(new IrisfuncDao().getAll(Irisfunc.class));
        setProdutos(new IrisprodDao().getAll(Irisprod.class));
        //setItens(new IrisitemDao().getAll(Irisitem.class));
        //List<Irisitem>it=new IrisitemDao().getItens(vendaCadastro);
        itens=new ArrayList<>();
        //if (it!=null){
        //    itens=it;
        //}
    }

    public Irisprod getProduto() {
        return produto;
    }

    public void setProduto(Irisprod produto) {
        this.produto = produto;
    }

    public List<Irisitem> getItens() {
        if (itens == null) {
            refresh();
        }
        return itens;
    }

    public void setItens(List<Irisitem> itens) {
        this.itens = itens;
    }

    public List<Irisitem> getItensFiltrados() {
        if (itensFiltrados == null) {
            refresh();
        }
        return itensFiltrados;
    }

    public void setItensFiltrados(List<Irisitem> itensFiltrados) {
        this.itensFiltrados = itensFiltrados;
    }

    public List<Irisprod> getProdutos() {
        if (produtos == null) {
            refresh();
        }
        return produtos;
    }

    public void setProdutos(List<Irisprod> produtos) {
        this.produtos = produtos;
    }

    public List<Irisprod> getProdutosFiltrados() {
        if (produtosFiltrados == null) {
            refresh();
        }
        return produtosFiltrados;
    }

    public void setProdutosFiltrados(List<Irisprod> produtosFiltrados) {
        this.produtosFiltrados = produtosFiltrados;
    }

    public Irisvendas getVendaCadastro() {
        if (vendaCadastro == null) { //esta rotina foi feita para criar o calculo a ser apresentado na tela
            vendaCadastro = new Irisvendas();
            vendaCadastro.setValortotal(0);
        }
        return vendaCadastro;
    }

    public void setVendaCadastro(Irisvendas vendaCadastro) {
        this.vendaCadastro = vendaCadastro;
    }

    public void carregarProdutos() {

        setProdutos(new IrisprodDao().getAll(Irisprod.class));

    }

    public Irisitem getIt() {
        return it;
    }

    public void setIt(Irisitem it) {
        this.it = it;
    }

    public Irisfunc getIrisf() {
        return irisf;
    }

    public void setIrisf(Irisfunc irisf) {
        this.irisf = irisf;
    }

    public List<Irisfunc> getIrisfuncionario() {
        return irisfuncionario;
    }

    public void setIrisfuncionario(List<Irisfunc> irisfuncionario) {
        this.irisfuncionario = irisfuncionario;
    }

    public List<Irisvendas> getListarVendas() {
        if (listarVendas == null) {
            refresh();
        }
        return listarVendas;
    }

    public void setListarVendas(List<Irisvendas> listarVendas) {
        this.listarVendas = listarVendas;
    }

 
    
    
    
    public void adicionar(Irisprod produto) {
        
        //este bloco comentado adiciona produto sem verificar se existe igual
        //Irisitem item = new Irisitem();
        //item.setIrisProduto(produto);
       //itens.add(item);
       //*******************************************************************
       
       
       
        
        //se for acima de -1 e maior que zero continua a busca
        int posicaoEncontrada = -1;//criei a variavel para verificar a posição encontrada coloquei -1 porque a busca começa em 0

        for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {//percorrer a lista de itens
            Irisitem itemTemp = itens.get(pos);//jogar a posição atual dentro do itemTemp
            if (itemTemp.getIrisProduto().equals(produto)) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

         Irisitem item = new Irisitem();
         item.setIrisProduto(produto);

         if (posicaoEncontrada < 0) {
            item.setQtd(1);
            item.setValor_parcial(produto.getPreco());
            itens.add(item);
         } else {

            Irisitem itemTemp = itens.get(posicaoEncontrada);//posição encontrada anteriormente
            item.setQtd(itemTemp.getQtd() + 1);//pega o valor anterior e add mais um
            item.setValor_parcial(produto.getPreco() * item.getQtd());
            
            itens.set(posicaoEncontrada, item);//posiçao encontrada e o que eu quero add

         }

        vendaCadastro.setValortotal(vendaCadastro.getValortotal() + produto.getPreco());
        
    }
    
    
    public void salvar() {
        if (getItens() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Não há itens para salvar"));
            return;
        }
      
      //**********Este bloco salva o cadastro de vendas e limpa a tabela
     
      vendaCadastro = new IrisvendasDao().save(getVendaCadastro());
      
        //vendaCadastro = new Irisvendas(); //pq vc instanciou um novo obj aqui?
      //  vendaCadastro.setValortotal(vendaCadastro.getValortotal());
        
     //*******************************************************************               
    
     
         
     for(Irisitem item : itens){
         
        if (item.getId() == 0) {
            
            item.setIrisVendas(getVendaCadastro());
            
                new IrisitemDao().save(item);
            }
     
     }
     
     
        vendaCadastro = new Irisvendas(); 
        refresh();
        setEtapa(0);
    }
    
    

    public void remover(Irisitem item) {
        
         int posicaoEncontrada = -1;

        for (int pos = 0; pos < itens.size() && posicaoEncontrada < 0; pos++) {
            Irisitem itemTemp = itens.get(pos);

            if (itemTemp.getIrisProduto().equals(item.getIrisProduto())) {
                posicaoEncontrada = pos;//só sai de -1 qdo cai aqui            
            }
        }

        if (posicaoEncontrada > -1) {
            itens.remove(posicaoEncontrada);
            vendaCadastro.setValortotal(vendaCadastro.getValortotal() - item.getValor_parcial());
            

        }
        
         //esta linha funciona perfeita - itens.remove(item);
         

    }

    public void carregarDadosVenda() {
        vendaCadastro.setHora(new Date());
        vendaCadastro.setData(new Date());
        //vendaCadastro.setData(new Date());
        //vendaCadastro.setFunc(new Irisfunc());
        // vendaCadastro.getFunc();
        //vendaCadastro.getFunc().setId(irisf.getId());
        //vendaCadastro.getFunc().setNome(irisf.getNome());

        //vendaCadastro.setFuncionario(irisfuncionario);
    }

    //setIrisfuncionario(new IrisfuncDao().getAll(Irisfunc.class));
    

}
