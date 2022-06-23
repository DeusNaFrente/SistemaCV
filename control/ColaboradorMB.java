package br.com.iris.control;

import br.com.iris.dao.ColaboradorDao;
import br.com.iris.model.Colaborador;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;


@ManagedBean(name = "colaboradorMB")
@ViewScoped
public class ColaboradorMB implements Serializable{
    private List<Colaborador> colaboradores;
    private Colaborador colaborador = new Colaborador();
    private int etapa=0;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Colaborador> getColaboradores() {
        if (colaboradores==null){
            refresh();
        }
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void refresh(){
        setColaboradores(new ColaboradorDao().getAll(Colaborador.class));
    }
    
    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }
    
    public void novo(){
        setColaborador(new Colaborador());
        etapa=1;
    }
    
    public void salvar(){
        if (getColaborador()==null){return;}
        if (getColaborador().getColaborador()==null || "".equals(getColaborador().getColaborador().trim())){
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!","O campo colaborador é obrigatório"));
            return;
        }
        
        new ColaboradorDao().save(getColaborador());
        refresh();
        setEtapa(0);
        setColaborador(new Colaborador());
    }

    public void editar(){
        setEtapa(1);
    } 
    
    public void excluir(){
        if (getColaborador()==null){return;}
        if (getColaborador().getId()==0){return;}
        new ColaboradorDao().delete(Colaborador.class,getColaborador().getId());
        refresh();
        setEtapa(0);
    }
    
    public void preencher(){
       
        colaborador.setColaborador("Arnold Esuasnega");
        colaborador.setCpf(123);
        colaborador.setRg(321);
        colaborador.setBairro("Casa Verde");
        colaborador.setBanco("Itau");
        colaborador.setAgencia(321);
        colaborador.setBonus(11);
        colaborador.setCarteiratrabalho("456");
        colaborador.setCategoria("B");
        colaborador.setCelular("+55(11)32360873");
        colaborador.setCep("02038-010");
        colaborador.setNaturalidade("Brazuca!!!");
        colaborador.setCnh(1111);
        colaborador.setConta(789);
        colaborador.setContsindical(112);
        colaborador.setCpffavorecido(546);
        colaborador.setEndereco("Rua 25 de março");
        colaborador.setEstado("São Paulo");
        colaborador.setEstadocivil("Solteiro");
        colaborador.setFavorecido("Arnold Schwarzenegger");
        colaborador.setNaturalidade("Austriaco");
        colaborador.setNumerobanco(557);
        colaborador.setObs("Nda");
        colaborador.setOutrosbenef(1111);
        colaborador.setPis("448");
        colaborador.setProfissao("Ator");
        colaborador.setSalariobase(2222);
        
        colaborador.setTelefone("+55(11)32360873");
        
        colaborador.setVr(555);
        colaborador.setVt(888);
        
       
        
    }
    
}
