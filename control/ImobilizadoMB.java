//este aqui faz o trampo de conversar com a tabela mysql
package br.com.iris.control;

import br.com.iris.dao.ImobilizadoDao;
import br.com.iris.model.Imobilizado;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "imobilizadoMB")
@ViewScoped
public class ImobilizadoMB implements Serializable {

    private List<Imobilizado> imobilizados;
    private Imobilizado imobilizado = new Imobilizado();
    private int etapa = 0;
    private double valor;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Imobilizado> getImobilizados() {
        if (imobilizados == null) {
            refresh();
        }
        return imobilizados;
    }

    public void setImobilizados(List<Imobilizado> imobilizados) {
        this.imobilizados = imobilizados;
    }

    public void refresh() {
        setImobilizados(new ImobilizadoDao().getAll(Imobilizado.class));
    }

    public Imobilizado getImobilizado() {
        return imobilizado;
    }

    public void setImobilizado(Imobilizado imobilizado) {
        this.imobilizado = imobilizado;
    }

    public void novo() {
        setImobilizado(new Imobilizado());
        etapa = 1;
    }

    public void salvar() {
        if (getImobilizado() == null) {return;}

        if (getImobilizado().getNome() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Ítem é obrigatório"));
            return;
        }
        if (getImobilizado().getQtde() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo quantidade é obrigatório"));
            return;
        }


        if (getImobilizado().getFornecedor() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo fornecedor é obrigatório"));
            return;
        }

        if (getImobilizado().getNf() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo nota fiscal é obrigatório"));
            return;
        }

        if (getImobilizado().getDatacompra() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo data da compra é obrigatório"));
            return;
        }

        if (getImobilizado().getGarantia() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo garantia é obrigatório"));
            return;
        }

        if (getImobilizado().getValorcompra() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo valor da compra é obrigatório"));
            return;
        }
        
        

        if (getImobilizado().getClassificacao() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo classificação é obrigatório"));
            return;
        }

        new ImobilizadoDao().save(getImobilizado());
        refresh();

        setEtapa(0);
        novo();

    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getImobilizado() == null) {
            return;
        }
        if (getImobilizado().getId() == 0) {
            return;
        }
        new ImobilizadoDao().delete(Imobilizado.class, getImobilizado().getId());
        refresh();
        setEtapa(0);
    }
}
