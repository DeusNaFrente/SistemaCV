package br.com.iris.control;

import br.com.iris.dao.FornecedorDao;
import br.com.iris.model.Fornecedor;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "fornecedorMB")
@ViewScoped
public class FornecedorMB implements Serializable{
    private List<Fornecedor> fornecedores;
    private Fornecedor fornecedor = new Fornecedor();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Fornecedor> getFornecedores() {
        if (fornecedores==null){
            refresh();
        }
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public void refresh(){
        setFornecedores(new FornecedorDao().getAll(Fornecedor.class));
    }
    
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    public void novo(){
        setFornecedor(new Fornecedor());
        etapa=1;
    }
    
    public void salvar(){
        if (getFornecedor()== null){return;}
        if (getFornecedor().getEmpresa()==null){
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","Nome do gestor é obrigatório"));
            return;
        }
        new FornecedorDao().save(getFornecedor());
        refresh();
        setEtapa(0);
        novo();
    }

    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getFornecedor()==null){return;}
        if (getFornecedor().getId()==0){return;}
        new FornecedorDao().delete(Fornecedor.class,getFornecedor().getId());
        refresh();
        setEtapa(0);
        novo();
    }
}
