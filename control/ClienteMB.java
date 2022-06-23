package br.com.iris.control;

import br.com.iris.dao.ClienteDao;
import br.com.iris.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;



//***********************************Este Bean é o Bean de EMPRESA ****************************//

@ManagedBean(name = "clienteMB")
@ViewScoped
public class ClienteMB implements Serializable{
    private List<Cliente> clientes;
    private Cliente cliente = new Cliente();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Cliente> getClientes() {
        if (clientes==null){
            refresh();
        }
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void refresh(){
        setClientes(new ClienteDao().getAll(Cliente.class));
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void novo(){
        setCliente(new Cliente());
        etapa=1;
    }
    
    public void salvar(){
        if (getCliente()==null){return;}
        
       if (getCliente().getEmpresa()== null || "".equals(getCliente().getEmpresa())) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Empresa é obrigatório"));
            return;
        }
       
       if (getCliente().getCnpj() == null || "".equals(getCliente().getCnpj())) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo CNPJ é obrigatório"));
            return;
        }
       
       if (getCliente().getContato()== null || "".equals(getCliente().getContato())) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Contato é obrigatório"));
            return;
        }
       
       if (getCliente().getTelefone()== null || "".equals(getCliente().getTelefone())) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Telefone é obrigatório"));
            return;
        }
       
       if (getCliente().getEmail()== null || "".equals(getCliente().getEmail())) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo E-mail é obrigatório"));
            return;
        }
       
       
       
       
        
      
        new ClienteDao().save(getCliente());
        refresh();
        setEtapa(0);
        novo();
    }
        
    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getCliente()==null){return;}
        if (getCliente().getId()==0){return;}
        new ClienteDao().delete(Cliente.class,getCliente().getId());
        refresh();
        setEtapa(0);
    }
}
