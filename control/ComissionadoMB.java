package br.com.iris.control;

import br.com.iris.dao.ComissionadoDao;
import br.com.iris.model.Comissionado;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "comissionadoMB")
@ViewScoped
public class ComissionadoMB implements Serializable {

    private List<Comissionado> comissionados;
    private Comissionado comissionado = new Comissionado();
    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Comissionado> getComissionados() {
        if (comissionados == null) {
            refresh();
        }
        return comissionados;
    }

    public void setComissionados(List<Comissionado> comissionados) {
        this.comissionados = comissionados;
    }

    public void refresh() {
        setComissionados(new ComissionadoDao().getAll(Comissionado.class));
    }

    public Comissionado getComissionado() {
        return comissionado;
    }

    public void setComissionado(Comissionado comissionado) {
        this.comissionado = comissionado;
    }

    public void novo() {
        setComissionado(new Comissionado());
        etapa = 1;
    }

    public void salvar() {
        if (getComissionado() == null) {
            return;
        }

        if (getComissionado().getNome() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo comissionado é obrigatório"));
            return;
        }

        
        new ComissionadoDao().save(getComissionado());
        refresh();
        setEtapa(0);
        novo();
    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getComissionado() == null) {
            return;
        }
        if (getComissionado().getId() == 0) {
            return;
        }
        new ComissionadoDao().delete(getComissionado());
        refresh();
        setEtapa(0);
        novo();
    }
    
}