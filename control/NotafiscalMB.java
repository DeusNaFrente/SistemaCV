
package br.com.iris.control;

import br.com.iris.dao.NotafiscalDao;
import br.com.iris.model.Notafiscal;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "nfMB")
@ViewScoped
public class NotafiscalMB implements Serializable {

    private List<Notafiscal> arrayfiscal;
    private Notafiscal notafiscal = new Notafiscal();

    private int etapa = 0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Notafiscal> getArrayfiscal() {
        if (arrayfiscal == null) {
            refresh();
        }
        return arrayfiscal;
    }

    public void setArrayfiscal(List<Notafiscal> arrayfiscal) {
        this.arrayfiscal = arrayfiscal;
    }

    public Notafiscal getNotafiscal() {
        return notafiscal;
    }

    public void setNotafiscal(Notafiscal notafiscal) {
        this.notafiscal = notafiscal;
    }

    public void refresh() {

        setArrayfiscal(new NotafiscalDao().getAll(Notafiscal.class));

    }

    public void novo() {
        setNotafiscal(new Notafiscal());
        etapa = 1;

    }

    public void salvar() {

        if (getNotafiscal() == null) {
            return;
        }

        if (getNotafiscal().getDescricao() == null) {

            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Nota não criada!", "O campo Descrição é obrigatório"));
            return;
        }

        new NotafiscalDao().save(getNotafiscal());
        refresh();
        setEtapa(0);
        novo();

    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {

        if (getNotafiscal() == null) {
            return;
        }
        if (getNotafiscal().getId() == 0) {
            return;
        }
        new NotafiscalDao().delete(Notafiscal.class, getNotafiscal().getId());
        refresh();
        setEtapa(0);

    }

}
