//este aqui faz o trampo de conversar com a tabela mysql
package br.com.iris.control;

import br.com.iris.dao.ContatoDao;
import br.com.iris.model.Contato;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.Path;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "contatoMB")
@ViewScoped
public class ContatoMB implements Serializable {

    private List<Contato> contatos;
    private Contato contato = new Contato();
    private int etapa = 0;
    

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Contato> getContatos() {
        if (contatos == null) {
            refresh();
        }
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void refresh() {
        setContatos(new ContatoDao().getAll(Contato.class));
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public void novo() {
        setContato(new Contato());
        etapa = 1;
    }

    public void salvar() {
        if (getContato() == null) {
            return;
        }

        if (getContato().getContato() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Nome do contato é obrigatório"));
            return;
        }
        new ContatoDao().save(getContato());
        refresh();
        setEtapa(0);
        novo();

    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getContato() == null) {
            return;
        }
        if (getContato().getId() == 0) {
            return;
        }
        new ContatoDao().delete(Contato.class,
                getContato().getId());
        refresh();
        setEtapa(0);
        novo();
    }
}
