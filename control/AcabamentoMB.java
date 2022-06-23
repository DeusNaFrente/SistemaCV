package br.com.iris.control;

import br.com.iris.dao.AcabamentoDao;
import br.com.iris.model.Acabamento;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "acabamentoMB")
@ViewScoped
public class AcabamentoMB implements Serializable {

    private List<Acabamento> acabamentos;
    private Acabamento acabamento = new Acabamento();
    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Acabamento> getAcabamentos() {
        if (acabamentos == null) {
            refresh();
        }
        return acabamentos;
    }

    public void setAcabamentos(List<Acabamento> acabamentos) {
        this.acabamentos = acabamentos;
    }

    public void refresh() {
        setAcabamentos(new AcabamentoDao().getAll(Acabamento.class));
    }

    public Acabamento getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(Acabamento acabamento) {
        this.acabamento = acabamento;
    }

    public void novo() {
        setAcabamento(new Acabamento());
        etapa = 1;
    }

    public void salvar() {
        if (getAcabamento() == null) {
            return;
        }

        if (getAcabamento().getNome() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo nome do Acabamento é obrigatório"));
            return;
        }

        new AcabamentoDao().save(getAcabamento());
        refresh();
        setEtapa(0);
        novo();
    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getAcabamento() == null) {
            return;
        }
        if (getAcabamento().getId() == 0) {
            return;
        }
        new AcabamentoDao().delete(Acabamento.class, getAcabamento().getId());
        refresh();
        setEtapa(0);
    }
    
}