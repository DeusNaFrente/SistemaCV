package br.com.iris.control;


import br.com.iris.dao.LogRetiradaDao;
import br.com.iris.model.Logretirada;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "logretiraMB")
@ViewScoped
public class LogretiradaMB implements Serializable {

    private List<Logretirada> arrayLogretira;
    private Logretirada logretirada = new Logretirada();
    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    

    public void refresh() {
        setArrayLogretira(new LogRetiradaDao().getAll(Logretirada.class));
    }

    public List<Logretirada> getArrayLogretira() {if(arrayLogretira == null){refresh();}
        return arrayLogretira;
    }

    public void setArrayLogretira(List<Logretirada> arrayLogretira) {
        this.arrayLogretira = arrayLogretira;
    }

    public Logretirada getLogretirada() {
        return logretirada;
    }

    public void setLogretirada(Logretirada logretirada) {
        this.logretirada = logretirada;
    }

    

    public void novo() {
        setLogretirada(new Logretirada());
        etapa = 1;
    }

    public void salvar() {
        if (getLogretirada() == null) {
            return;
        }

        if (getLogretirada().getDia() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Data é obrigatório"));
            return;
        }

        
        new LogRetiradaDao().save(getLogretirada());
        refresh();
        setEtapa(0);
        novo();
    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getLogretirada() == null) {
            return;
        }
        if (getLogretirada().getId() == 0) {
            return;
        }
        //new LogRetiradaDao().delete(LogRetirada.class, getLogretirada().getId());
        new LogRetiradaDao().delete(getLogretirada());
        refresh();
        setEtapa(0);
        novo();
    }
    
}