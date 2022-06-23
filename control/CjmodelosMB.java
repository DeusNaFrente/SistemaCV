/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.CjmodelosDao;
import br.com.iris.dao.ItemModeloDao;
import br.com.iris.dao.ModeloDao;
import br.com.iris.model.Cjmodelos;
import br.com.iris.model.ItemModelo;
import br.com.iris.model.Modelo;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "cjmodelosMB")
@ViewScoped
public class CjmodelosMB implements Serializable {

    @ManagedProperty(value = "#{itemmodeloMB}")
    private ItemModeloMB itemModeloMB;
    public ItemModeloMB getItemModeloMB() {return itemModeloMB;}
    public void setItemModeloMB(ItemModeloMB itemmodeloMB) {this.itemModeloMB = itemModeloMB;}

    
    
    
    private List<Cjmodelos> listacjModelo;
    private Cjmodelos cjmodelos = new Cjmodelos();

    private List<Modelo> listaModelo;
    private Modelo modelo = new Modelo();

    private List<ItemModelo> itemModelo;
    private ItemModelo itemmodelo = new ItemModelo();
    private List<ItemModelo> itemcjmodelo;
    private int etapa = 0;

    public void refresh() {
        setListacjModelo(new CjmodelosDao().getAll(Cjmodelos.class));
        setListaModelo(new ModeloDao().getAll(Modelo.class));
        setItemModelo(new ItemModeloDao().getAll(ItemModelo.class));
        itemcjmodelo = new ArrayList<>();

    }

    public List<Cjmodelos> getListacjModelo() {
        if (listacjModelo == null) {
            refresh();
        }
        return listacjModelo;
    }

    public void setListacjModelo(List<Cjmodelos> listacjModelo) {
        this.listacjModelo = listacjModelo;
    }

    public Cjmodelos getCjmodelos() {
        return cjmodelos;
    }

    public void setCjmodelos(Cjmodelos cjmodelos) {
        this.cjmodelos = cjmodelos;
    }

    public List<Modelo> getListaModelo() {
        if (listaModelo == null) {
            refresh();
        }
        return listaModelo;
    }

    public void setListaModelo(List<Modelo> listaModelo) {
        this.listaModelo = listaModelo;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public List<ItemModelo> getItemModelo() {
        return itemModelo;
    }

    public void setItemModelo(List<ItemModelo> itemModelo) {
        this.itemModelo = itemModelo;
    }

    public ItemModelo getItemmodelo() {
        return itemmodelo;
    }

    public void setItemmodelo(ItemModelo itemmodelo) {
        this.itemmodelo = itemmodelo;
    }

    public List<ItemModelo> getItemcjmodelo() {
        return itemcjmodelo;
    }

    public void setItemcjmodelo(List<ItemModelo> itemcjmodelo) {
        this.itemcjmodelo = itemcjmodelo;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public void novo() {
        setCjmodelos(new Cjmodelos());
        etapa = 1;
    }

    public void adicionarMo(Modelo modelo) throws SQLException {

        List<ItemModelo> itensMo = getCjmodelos().getItensMo();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensMo.size() && posicaoEncontrada < 0; pos++) {
            ItemModelo itemTemp = itensMo.get(pos);
            if (itemTemp.getModelo().equals(modelo)) {
                posicaoEncontrada = pos;            
            }
        }

        ItemModelo itemmodel = new ItemModelo();
        itemmodel.setModelo(modelo);

        if (posicaoEncontrada < 0) {
            
            
            if (getCjmodelos().getNomedocj() == null || getCjmodelos().getNomedocj().isEmpty()) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro de conjunto!", "Crie ou abra um conjunto de modelos."));
                return;
            }
            
            itemmodel.setQtd(1);
            itemmodel.setValor(modelo.getValor());
            itensMo.add(itemmodel);

        } else if (itemmodel.getModelo().equals(modelo)) {
            ItemModelo itemTemp = itensMo.get(posicaoEncontrada);
            itemmodel.setQtd(itemTemp.getQtd() + 1);
            itensMo.set(posicaoEncontrada, itemmodel);
            itemmodel.setValor(modelo.getValor() * itemmodel.getQtd());

        }

        cjmodelos.setValor(cjmodelos.getValor() + modelo.getValor());
        refresh();
    }

    public void removerItemModelos(ItemModelo modelo) throws SQLException {

        List<ItemModelo> itensMo = getCjmodelos().getItensMo();

        int posicaoEncontrada = -1;

        for (int pos = 0; pos < itensMo.size() && posicaoEncontrada < 0; pos++) {
            ItemModelo itemTemp = itensMo.get(pos);

            if (itemTemp.getModelo().equals(modelo.getModelo())) {
                posicaoEncontrada = pos;
            }
        }

        if (posicaoEncontrada > -1) {

            modelo.setQtd(modelo.getQtd() - 1);

            if (modelo.getQtd() == 0) {
                itensMo.remove(posicaoEncontrada);//calculo errado em logequipe
                //  colab.setValor_parcial(colab.getValor_parcial() * colab.getQtd());
            }

            if (modelo.getId() > 0) {

                ItemModeloDao dao = new ItemModeloDao();

                dao.excluirModelo(modelo);

            }

            if (getCjmodelos().getItensMo() == null) {
                return;
            }

            cjmodelos.setValor(cjmodelos.getValor() - modelo.getValor());

        }

    }

    public void salvar() throws SQLException {

        List<ItemModelo> itensMo = getCjmodelos().getItensMo();

        if (getCjmodelos() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Selecione um agrupamento."));
            return;
        }

        if (getCjmodelos().getNomedocj().isEmpty()) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Forneça um nome para o conjunto."));
            return;
        }

        if (itensMo.size() <= 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Adicione algum modelo ao conjunto."));
            return;
        }
        
        setCjmodelos(new CjmodelosDao().save(getCjmodelos()));

        for (ItemModelo i : itensMo) {

            if (i.getId() >= 0) {

                i.setCjmodelos(getCjmodelos());
                new ItemModeloDao().save(i);
            } else {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na Composição deste Conjunto!", "Inclua itens neste conjunto."));
                return;
            }

        }

        //setCjmodelos(new CjmodelosDao().save(getCjmodelos()));
        refresh();
        setEtapa(0);
        novo();

    }
    
    
    
    public void excluirGeralCjModelo() throws SQLException{
       if (getCjmodelos() == null) {
            return;
        }
        if (getCjmodelos().getId() == 0) {
            return;
        }
        
        new CjmodelosDao().delete(getCjmodelos());
        refresh();
        setEtapa(0);
        novo();
    }

     public void editar() {
        setEtapa(1);
    }
     
    //***nao esta sendo usado
   // public void excluir() {
        
        
        
    //    if (getCjmodelos() == null) {
    //        return;
   //     }
      //  if (getCjmodelos().getId() == 0) {
     //       return;
     //   }
        
                
   //     new CjmodelosDao().delete(Cjmodelos.class, getCjmodelos().getId());
   //     refresh();
    //    setEtapa(0);
    //    novo();
   // }
    
   // ***

   
    
    
    
    

}
