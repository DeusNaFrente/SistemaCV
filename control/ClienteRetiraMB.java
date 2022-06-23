/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.iris.control;

import br.com.iris.dao.ClienteRetiraDao;
import br.com.iris.model.Cliente;
import br.com.iris.model.ClienteRetira;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Iris
 */
@ManagedBean(name = "clienteretiraMB")
@ViewScoped
public class ClienteRetiraMB implements Serializable{

   private List<ClienteRetira> arrayCretira;
   private ClienteRetira clienteretira = new ClienteRetira();
   private Cliente clientes = new Cliente();
   private int etapa = 0;
   

    public List<ClienteRetira> getArrayCretira() {
        if(arrayCretira == null){refresh();}
        return arrayCretira;
    }

    public void setArrayCretira(List<ClienteRetira> arrayCretira) {
        this.arrayCretira = arrayCretira;
    }

    public Cliente getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = clientes;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public ClienteRetira getClienteretira() {
        return clienteretira;
    }

    public void setClienteretira(ClienteRetira clienteretira) {
        this.clienteretira = clienteretira;
    }
    
    
   
   
    public void refresh(){
        setArrayCretira(new ClienteRetiraDao().getAll(ClienteRetira.class));
    }
   
   public void novo(){
        setClienteretira(new ClienteRetira());
        etapa=1;
    }
   
   public void editar(){
        setEtapa(1);
    } 
   
    public void excluir(){
        if (getClienteretira()==null){return;}
        if (getClienteretira().getId()==0){return;}
        new ClienteRetiraDao().delete(ClienteRetira.class,getClienteretira().getId());
        refresh();
        setEtapa(0);
    }
    
    public void salvar(){
        if (getClienteretira()==null){return;}
        
       if (getClienteretira().getCliente().getEmpresa()== null || "".equals(getClienteretira().getCliente().getEmpresa())) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Empresa é obrigatório"));
            return;
        }
      
     
        
      
        new ClienteRetiraDao().save(getClienteretira());
        refresh();
        setEtapa(0);
        novo();
    }

    
}
