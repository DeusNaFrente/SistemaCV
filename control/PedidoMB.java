package br.com.iris.control;

import br.com.iris.dao.PedidoDao;
import br.com.iris.model.Pedido;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "pedidoMB")
@ViewScoped
public class PedidoMB implements Serializable{
    private List<Pedido> pedidos;
    private Pedido pedido;
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Pedido> getPedidos() {
        if (pedidos==null){
            refresh();
        }
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void refresh(){
        setPedidos(new PedidoDao().getAll(Pedido.class));
    }
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
    public void novo(){
        setPedido(new Pedido());
        etapa=1;
    }
    
    public void salvar(){
        if (getPedido()==null){return;}
        if (getPedido().getCliente()==null){
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Cliente é obrigatório"));
            return;
        }
        if (getPedido().getData()==null){
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Data é obrigatória"));
            return;
        }
        new PedidoDao().save(getPedido());
        refresh();
        setEtapa(0);
    }

    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getPedido()==null){return;}
        if (getPedido().getId()==0){return;}
        new PedidoDao().delete(Pedido.class,getPedido().getId());
        refresh();
        setEtapa(0);
    }
}
