//este aqui faz o trampo de conversar com a tabela mysql
package br.com.iris.control;

import br.com.iris.dao.EquipamentoDao;
import br.com.iris.model.Equipamento;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.PrimeFaces;

@ManagedBean(name = "equipamentoMB")
@ViewScoped
public class EquipamentoMB implements Serializable {

    private List<Equipamento> equipamentos;
    private Equipamento equipamento = new Equipamento();
    private int etapa = 0;
    private double valor;

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public List<Equipamento> getEquipamentos() {
        if (equipamentos == null) {
            refresh();
        }
        return equipamentos;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }

    public void refresh() {
        setEquipamentos(new EquipamentoDao().getAll(Equipamento.class));
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void novo() {
        setEquipamento(new Equipamento());
        etapa = 1;
    }

    public void salvar() {
        if (getEquipamento() == null) {
            return;
        }

        if (getEquipamento().getNequip() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Nome do equipamento é obrigatório"));
            return;
        }
        if (getEquipamento().getFornecedor() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "Nome do fornecedor é obrigatório"));
            return;
        }

        if (getEquipamento().getNf() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo nóta é obrigatório"));
            return;
        }

        if (getEquipamento().getGarantia() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo garantia é obrigatório"));
            return;
        }

        if (getEquipamento().getValcompra() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O valor da compra é obrigatório"));
            return;
        }

        if (getEquipamento().getVidautil() == 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo vida útil é obrigatório"));
            return;
        }

        /*if (getEquipamento().getSaldoresidual() == null) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo saldo residual é obrigatório"));
            return;
        }*/

        if (getEquipamento().getPercentualdep()== 0) {
            PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo percentual de depreciação é obrigatório"));
            return;
        }

        if ("Plotter".equals(getEquipamento().getTipoequip())) {

            if (getEquipamento().getProdutividade() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo produtividade é obrigatório"));
                return;
            }

            if (getEquipamento().getCustoproducao() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo custo de produção é obrigatório"));
                return;
            }

            if (getEquipamento().getMedida() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo medida é obrigatório"));
                return;
            }

            if (getEquipamento().getTurno() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo turno é obrigatório"));
                return;
            }

            if (getEquipamento().getAltura() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo altura é obrigatório"));
                return;

            }

            if (getEquipamento().getLargura() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo largura é obrigatório"));
                return;

            }
            if (getEquipamento().getModelo() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo modelo é obrigatório"));
                return;
            }

        } else {

            if (getEquipamento().getModelocnc() == null) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo modelo é obrigatório"));
                return;
            }

            if (getEquipamento().getValhcorte() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Valor Hora Corte  é obrigatório"));
                return;
            }

            if (getEquipamento().getValhvinco() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Valor Hora Vinco é obrigatório"));
                return;
            }

            if (getEquipamento().getValhrebaixo() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Valor Hora Rebaixo é obrigatório"));
                return;
            }

            if (getEquipamento().getValhgravacao() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo Valor Hora Gravação é obrigatório"));
                return;
            }

            if (getEquipamento().getCncaltura() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo em CNC Altura é obrigatório"));
                return;
            }

            if (getEquipamento().getCnclargura() == 0) {
                PrimeFaces.current().dialog().showMessageDynamic(new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados inválidos!", "O campo em CNC Largura é obrigatório"));
                return;
            }
        }

        new EquipamentoDao().save(getEquipamento());
        refresh();
        setEtapa(0);
        novo();

    }

    public void editar() {
        setEtapa(1);
    }

    public void excluir() {
        if (getEquipamento() == null) {
            return;
        }
        if (getEquipamento().getId() == 0) {
            return;
        }
        new EquipamentoDao().delete(Equipamento.class,
                getEquipamento().getId());
        refresh();
        setEtapa(0);
    }
}
