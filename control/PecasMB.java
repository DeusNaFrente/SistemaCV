/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.PecasDao;
import br.com.iris.model.Pecas;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "pecasMB")
@ViewScoped
public class PecasMB implements Serializable {
    
    private List<Pecas> listaPecas;
    private Pecas pecas = new Pecas();
    private int etapa = 0;
    
    public void refresh(){
    setListaPecas(new PecasDao().getAll(Pecas.class));
    }

    public List<Pecas> getListaPecas() {if(listaPecas == null ){refresh();}
        return listaPecas;
    }

    public void setListaPecas(List<Pecas> listaPecas) {
        this.listaPecas = listaPecas;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }
    
    public void novo(){
    setPecas(new Pecas());
    etapa = 1;
    
    }
    
    public void editar(){
    setEtapa(1);
    
    }
    
    public void salvar(){
        
        if(getPecas() == null){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Entre com o nome desta peça."));
            return;
        }
        
        if(getPecas().getNome() == null){
        PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Campo Vazio!", "Entre com o nome desta peça."));
            return;
        }
        
        setPecas(new PecasDao().save(getPecas()));
        refresh();
        setEtapa(0);
        novo();
        
    
    }
    

}
    
    
    

